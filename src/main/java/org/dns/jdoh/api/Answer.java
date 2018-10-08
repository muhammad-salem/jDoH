package org.dns.jdoh.api;

public class Answer {

	/**
	 * Always matches name in the Question section
	 */
	String name;

	/**
	 * A - Standard DNS RR type
	 * 
	 * The type of DNS record. These are defined here:
	 * https://www.iana.org/assignments/dns-parameters/dns-parameters.xhtml#dns-parameters-4
	 */
	int type;

	/**
	 * Record's time-to-live in seconds
	 * 
	 * The number of seconds the answer can be stored in cache before it is
	 * considered stale.
	 */
	int TTL;

	/**
	 * Data for A - IP address as text
	 * 
	 * The value of the DNS record for the given name and type. The data will be
	 * in text for standardized record types and in hex for unknown types.
	 */
	String data;
}
