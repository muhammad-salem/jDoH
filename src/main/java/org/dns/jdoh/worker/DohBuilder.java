package org.dns.jdoh.worker;

import com.google.gson.Gson;
import org.dns.jdoh.api.*;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DohBuilder implements DohLookup {



    private Map<String, LinkedList<InetAddress>> dnsCache;
    private RequestMessage requestMessage;
    DohBuilder(Map<String, LinkedList<InetAddress>> cache, RequestMessage message) {
        this.dnsCache = cache;
        this.requestMessage = message;
        this.loadDnsCache();
        this.connectSSlToRequestServer();
    }
    DohBuilder(Map<String, LinkedList<InetAddress>> cache) {
        this.dnsCache = cache;
    }

    private void setRequestMessage(RequestMessage requestMessage) {
        this.requestMessage = requestMessage;
        this.connectSSlToRequestServer();
    }

    public RequestMessage getRequestMessage() {
        return requestMessage;
    }

    private void loadDnsCache() {
        addToCache(requestMessage.getHostName(), createAddressListFor(requestMessage.getHostIP()));
    }

    private void addAddressToList(byte[] ipAddress , LinkedList<InetAddress> list){
		try {
			list.add(InetAddress.getByAddress(ipAddress));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

    private LinkedList<InetAddress> createAddressListFor(byte[] ipAddress){
        LinkedList<InetAddress> list = new LinkedList<>();
        try {
            list.add(InetAddress.getByAddress(ipAddress));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return  list;
    }

    private LinkedList<InetAddress> createAddressListFor(byte[]... ipAddress){
        LinkedList<InetAddress> list = new LinkedList<>();
        try {
            for (byte[] bs: ipAddress){
                list.add(InetAddress.getByAddress(bs));
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return  list;
    }

    public void addToCache(String hostname, LinkedList<InetAddress> list) {
        if (dnsCache.containsKey(hostname)){
            dnsCache.get(hostname).addAll(list);
        } else {
            dnsCache.put(hostname, list);
        }
    }

    public static byte[] getIpAsArrayOfByte(String ipAddress) {

        StringTokenizer st = new StringTokenizer(ipAddress, ".");
        byte[] ip = new byte[4];
        int i = 0;

        while (st.hasMoreTokens()) {
//            ip[i++] = Byte.parseByte(st.nextToken());
            ip[i++] = (byte) Integer.parseInt(st.nextToken());
        }

        return ip;
    }

    private InetAddress[] toArray(LinkedList<InetAddress> list) {
        return list.toArray(new InetAddress[list.size()]);
    }

    private String searchHostIP(byte[] bytes) {
        String hostname = "";
        for (LinkedList<InetAddress> list: dnsCache.values()){
            for (InetAddress ip : list) {
                if (ip.getAddress() == bytes) {
                    hostname = ip.getHostName();
                    break;
                }
            }
        }
        return hostname;
    }



    private SSLSocket socket;
    void setSocket(SSLSocket socket) {
        this.socket = socket;
    }

    protected void connectSSlToRequestServer() {
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        try {
            this.socket = (SSLSocket) factory.createSocket(requestMessage.getHostName(), requestMessage.getPort());
            this.socket.startHandshake();
        }catch ( UnknownHostException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Request createRequest(String hostname) {
        return new Request(hostname);
    }

    public Request createRequest(String hostname, Type type) {
        Request request = new Request(hostname);
        request.type = type.toString();
        return request;
    }

    public String builldRequest(Request request) {
        StringBuilder builder = new StringBuilder();
        builder.append("GET ");
        builder.append(requestMessage.getUrl());
        builder.append(request.toString());
        builder.append(" HTTP/1.1\r\n");
        builder.append("Host: ");
        builder.append(requestMessage.getHostName());
        builder.append("\r\n");
        for (String header: requestMessage.getHeaders()){
            builder.append(header);
            builder.append("\r\n");
        }
        builder.append("\r\n");
        return builder.toString();
    }

    public void searchFor(String hostname){
        Request request = createRequest(hostname);
        String get = builldRequest(request);
        if (socket == null || !socket.isConnected() || socket.isClosed()){
            connectSSlToRequestServer();
        }
        try {
            socket.getOutputStream().write(get.getBytes());
            socket.getOutputStream().flush();
//            System.out.print(get);
        }catch (IOException e){
            System.err.println("error write to socket: " + e.getMessage());
        }

        byte[] bytes = new byte[1024];
        try {
            int length = socket.getInputStream().read(bytes);
//            System.out.println("response length: "+ length);
//            System.out.println("response" + new String(bytes, 0, length));
            StringReader reader = new StringReader(new String(bytes, 0, length));
            Scanner scanner = new Scanner(reader);
            String line = scanner.nextLine();
            if (line.contains("200")){
//                System.out.println(line);
                while (scanner.hasNext()) {
                    line = scanner.nextLine();
//                    System.out.println(line);
                    if (line.equals("")) break;
                }
            }
            line = scanner.nextLine();
            if (line.charAt(0) != '{')
                line = scanner.nextLine();
            Response response = gson.fromJson(line, Response.class);
            byte[][] ips = new byte [response.Answer.length][4];
            for (int i=0; i < response.Answer.length; i++) {
//                System.out.println("json: "+ i + ' ' + response.Answer[i].data);
                ips[i] = getIpAsArrayOfByte(response.Answer[i].data);
            }
            addToCache(hostname, createAddressListFor(ips));

            // check if server support Connection: Keep Alive
            if (requestMessage.closeConnection()){
                socket.close();
            }
            return;
//            byte[] ip = getIpAsArrayOfByte(response.Answer)
//            System.out.println("json: " + response.Answer[0].data);

        }catch (IOException e){
            System.err.println("error write to socket: " + e.getMessage());
        }
        addToCache(hostname, new LinkedList<InetAddress>());
    }

    private Gson gson = new Gson();

    public DohLookup build() {
        return this;
    }

    @Override
    public InetAddress[] lookupAllHostAddr(final String hostname) {
        if (dnsCache.containsKey(hostname)) {
            LinkedList<InetAddress> list = dnsCache.get(hostname);
            if (list.size() > -1) return toArray(list);
        }
        searchFor(hostname);
        return lookupAllHostAddr(hostname);
    }

    @Override
    public String getHostByAddr(byte[] bytes) {
        return searchHostIP(bytes);
//        if (host.length() > 0){
//            return host;
//        } else {
//
//        }
    }


}
