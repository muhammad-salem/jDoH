package org.dns.jdoh.api;

public interface RequestMessage {
	
	Request getRequest();
	String getUrl();
	String[] getHeaders();

}
