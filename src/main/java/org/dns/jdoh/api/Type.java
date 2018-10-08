package org.dns.jdoh.api;

/**
 * RR type can be represented as a number in [1, 65535] or a canonical string
 * (case-insensitive, such as A or aaaa). You can use 255 for 'ANY' queries but
 * be aware that this is not a replacement for sending queries for both A and
 * AAAA or MX records. Authoritative name servers need not return all records
 * for such queries; some do not respond, and others (such as cloudflare.com)
 * return only HINFO.
 * 
 * @author salem
 *
 */
public enum Type {
	/**
	 * IPv4
	 */
	A(1),

	/**
	 * IPv6
	 */
	AAAA(28),

	/**
	 * You can use 255 for 'ANY' queries but be aware that this is not a
	 * replacement for sending queries for both A and AAAA or MX records.
	 */
	ANY(255);

	int code;
	private Type(int code) {
		this.code = code;
	}

	public static Type Resolve(int code) {
		switch (code) {
			case 1 :
				return Type.A;
			case 28 :
				return Type.AAAA;
			case 255 :
			default :
				return Type.ANY;
		}
	}

	@Override
	public String toString() {
		return name();
	}

}
