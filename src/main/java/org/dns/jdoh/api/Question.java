package org.dns.jdoh.api;

public class Question {
	
	/**
	 * FQDN with trailing dot
	 * The record name requested
	 */
	String name;
	
	/**
	 * The type of DNS record requested. These are defined here: https://www.iana.org/assignments/dns-parameters/dns-parameters.xhtml#dns-parameters-4
	 * 
	 *  1 -> A - Standard DNS RR type
	 * 16 -> TXT - Standard DNS RR type
	 * 99 -> SPF - Standard DNS RR type
	 */
	String type;
}

