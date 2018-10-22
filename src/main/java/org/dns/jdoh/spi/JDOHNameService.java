package org.dns.jdoh.spi;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.dns.jdoh.worker.DohBuilder;
import org.dns.jdoh.worker.DohBuilderFactory;
import org.dns.jdoh.worker.DohLookup;
import sun.net.spi.nameservice.NameService;

public class JDOHNameService implements NameService {
	
	private static final String GoogleDOH			= "doh.google";
	private static final String CloudflareDOH		= "doh.cloudflare";
	private static final String JdohProxyHttp		= "doh.http.proxy";
	private static final String JdohProxyHttps		= "doh.https.proxy";
	private static final String JdohProxySocks5		= "doh.socks5.proxy";
	private static final String RecordType			= "doh.type";



	
	private boolean PreferV6 = false;
	
	
	private static Map<String, LinkedList<InetAddress>> cache = new HashMap<>();
	
	
	DohLookup lookup;
	public JDOHNameService() {

		DohBuilderFactory factory = DohBuilderFactory.newInstance();
		factory.useCloudflarDOH();
		DohBuilder builder = factory.getBuilder();
		this.lookup = builder.build();

//		LinkedList<InetAddress> cloudflareDns = new LinkedList<>();
//		addAddressToList(CloudflareIP.getBytes(), cloudflareDns);
//		cache.put(CloudflareHOST, cloudflareDns);

//		LinkedList<InetAddress> googelDns = new LinkedList<>();
//		addAddressToList(new byte[] {(byte) 172, (byte) 217, (byte)19, (byte)46 }, googelDns);
////		googelDns.add(InetAddress.getByAddress(new byte[] {(byte) 172, (byte) 217, 19, 46}));
////				(("172.217.19.46");
//		cache.put("dns.google.com", googelDns);

		
	}
	
//	private void addAddressToList(byte[] ipAddress , LinkedList<InetAddress> list){
//		try {
//			list.add(InetAddress.getByAddress(ipAddress));
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
//	}


	@Override
	public String getHostByAddr(byte[] hostAdress) throws UnknownHostException {
		return lookup.getHostByAddr(hostAdress);
	}


	@Override
	public InetAddress[] lookupAllHostAddr(String hostname) throws UnknownHostException {
		return lookup.lookupAllHostAddr(hostname);
	}


	
}
