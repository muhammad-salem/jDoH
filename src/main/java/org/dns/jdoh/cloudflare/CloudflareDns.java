package org.dns.jdoh.cloudflare;

import org.dns.jdoh.api.Request;
import org.dns.jdoh.api.RequestMessage;
import org.dns.jdoh.api.Type;

public class CloudflareDns implements RequestMessage{
	
	private static String Cloudflare_DNS_URL = "https://cloudflare-dns.com/dns-query?";
	private static String[] Request_Header = new String[] {"accept: application/dns-json"}; 
	
	Request request;
	
	public CloudflareDns(String hostname) {
		this.request = new Request(hostname);
	}
	
	public CloudflareDns(String hostname, Type type) {
		this.request = new Request(hostname);
		this.request.type = type.toString();
	}

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getUrl() {
		return Cloudflare_DNS_URL;
	}

	@Override
	public String[] getHeaders() {
		return Request_Header;
	}
	
	

}
