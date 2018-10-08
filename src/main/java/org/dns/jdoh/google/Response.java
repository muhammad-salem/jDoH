package org.dns.jdoh.google;

public class Response {
	/**
	 *  0 -> NOERROR - Standard DNS response code (32 bit integer).
	 *  2 -> SERVFAIL - Standard DNS response code (32 bit integer).
	 */
	int Status;
	
	/**
	 * Whether the response is truncated
	 */
	boolean TC;
	
	/**
	 * Always true for Google Public DNS
	 */
	boolean RD;
	
	/**
	 * Always true for Google Public DNS
	 */
	boolean RA;
	
	/**
	 * Whether all response data was validated with DNSSEC
	 */
	boolean AD;
	
	/**
	 * Whether the client asked to disable DNSSEC
	 */
	boolean CD;
	
	
	Question Question[];
	Answer Answer[];
	String Additional[];
	
	/**
	 * IP address / scope prefix-length
	 */
	String edns_client_subnet;
	String Comment;
}

