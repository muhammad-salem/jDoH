package org.dns.jdoh.api;

public class Question {
	
	/**
	 * FQDN with trailing dot
	 */
	String name;
	
	/**
	 *  1 -> A - Standard DNS RR type
	 * 16 -> TXT - Standard DNS RR type
	 * 99 -> SPF - Standard DNS RR type
	 */
	String type;
}

