package org.dns.jdoh.google;

import org.dns.jdoh.api.Request;
import org.dns.jdoh.api.RequestMessage;
import org.dns.jdoh.api.Type;

public class GoogleDns implements RequestMessage{
	
	private static String Google_DNS_URL = "https://dns.google.com/resolve?";
	private static String[] Request_Header = new String[] {}; 

	Request request;
	
	public GoogleDns(String hostname) {
		this.request = new Request(hostname);
	}
	
	public GoogleDns(String hostname, Type type) {
		this.request = new Request(hostname);
		this.request.type = type.toString();
	}

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getUrl() {
		return Google_DNS_URL;
	}

	@Override
	public String[] getHeaders() {
		return Request_Header;
	}
	
	

}
