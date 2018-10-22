package org.dns.jdoh.api;

public interface RequestMessage {

	String getUrl();
    String getHostName();
    byte[]  getHostIP();
    int    getPort();
	String[] getHeaders();
	boolean closeConnection();




}
