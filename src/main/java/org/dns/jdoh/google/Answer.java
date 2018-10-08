package org.dns.jdoh.google;

public class Answer {
	
	/**
	 *  Always matches name in the Question section
	 */
	String name;
	
	/**
	 * A - Standard DNS RR type
	 */
	int type;
	
	/**
	 * Record's time-to-live in seconds
	 */
	int TTL;
	
	/**
	 * Data for A - IP address as text
	 */
	String data;
}
