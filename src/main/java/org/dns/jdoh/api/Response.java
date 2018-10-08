package org.dns.jdoh.api;

public class Response {
	/**
	 * The Response Code of the DNS Query. These are defined here:
	 * https://www.iana.org/assignments/dns-parameters/dns-parameters.xhtml#dns-parameters-6
	 * 
	 * 0 -> NOERROR - Standard DNS response code (32 bit integer). 
	 * 2 -> SERVFAIL - Standard DNS response code (32 bit integer).
	 */
	int Status;

	/**
	 *  *Whether the response is truncated
	 * If true, it means the truncated bit was set. This happens when the DNS answer is larger than a single UDP or TCP packet. TC will almost always be false with Cloudflare DNS over HTTPS because Cloudflare supports the maximum response size.
	 *
	 */
	boolean TC;

	/**
	 * Always true for Google Public DNS
	 * If true, it means the Recursive Desired bit was set. This is always set to true for Cloudflare DNS over HTTPS.
	 */
	boolean RD;

	/**
	 * Always true for Google Public DNS
	 * If true, it means the Recursion Available bit was set. This is always set to true for Cloudflare DNS over HTTPS.
	 */
	boolean RA;

	/**
	 * Whether all response data was validated with DNSSEC
	 * If true, it means that every record in the answer was verified with DNSSEC.
	 */
	boolean AD;

	/**
	 * Whether the client asked to disable DNSSEC
	 * If true, the client asked to disable DNSSEC validation. In this case, Cloudflare will still fetch the DNSSEC-related records, but it will not attempt to validate the records.
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
