package org.dns.jdoh.spi;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import sun.net.spi.nameservice.NameService;

public class JDOHNameService implements NameService {
	
	private static final String GoogleDOH = "doh.google";
	private static final String CloudflareDOH = "doh.cloudflare";
	private static final String JdohProxyHttp = "doh.http.proxy";
	private static final String JdohProxyHttps = "doh.https.proxy";
	private static final String JdohProxySocks5 = "doh.socks5.proxy";
	private static final String RecordType = "doh.type";

	
	private boolean PreferV6 = false;
	
	
	private static Map<String, LinkedList<InetAddress>> cache = new HashMap<>();
	
	
	
	public JDOHNameService() {
		LinkedList<InetAddress> googelDns = new LinkedList<>();
		addAddressToList(new byte[] {(byte) 172, (byte) 217, (byte)19, (byte)46 }, googelDns);
//		googelDns.add(InetAddress.getByAddress(new byte[] {(byte) 172, (byte) 217, 19, 46}));
//				(("172.217.19.46");
		cache.put("dns.google.com", googelDns);
		
		LinkedList<InetAddress> cloudflareDns = new LinkedList<>();
		addAddressToList(new byte[] {(byte) 104, (byte) 16, (byte)111, (byte)25}, cloudflareDns);
		cache.put("cloudflare-dns.com", cloudflareDns);
		
	}
	
	private void addAddressToList(byte[] ipAddress , LinkedList<InetAddress> list){
		try {
			list.add(InetAddress.getByAddress(ipAddress));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}


	@Override
	public String getHostByAddr(byte[] hostAdress) throws UnknownHostException {
		System.out.println(Arrays.toString(hostAdress));
		return null;
	}


	@Override
	public InetAddress[] lookupAllHostAddr(String hostname) throws UnknownHostException {
		InetAddress[] addresses;
		if(cache.containsKey(hostname)) {
			LinkedList<InetAddress> list = cache.get(hostname);
			
			addresses =  new InetAddress[list.size()];
			for (int i = 0; i < addresses.length; i++) {
				addresses[i] = list.get(i);
			}
		}else {
			addresses = new InetAddress[0];
		}
		return addresses;
	}


	
}
