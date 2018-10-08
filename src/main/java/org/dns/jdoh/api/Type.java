package org.dns.jdoh.api;

/**
 * RR type can be represented as a number in [1, 65535] or a canonical string
 * (case-insensitive, such as A or aaaa). You can use 255 for 'ANY' queries but
 * be aware that this is not a replacement for sending queries for both A and
 * AAAA or MX records. Authoritative name servers need not return all records
 * for such queries; some do not respond, and others (such as cloudflare.com)
 * return only HINFO.
 * 
 * 
 * 0 0x0000 RRTYPE zero is used as a special indicator for the SIG RR [RFC2931],
 * [RFC4034] and in other circumstances and must never be allocated for ordinary
 * use. 
 * 
 * 1-127 0x0000-0x007F Expert Review (see mailing list information in
 * [RFC6895]) or Standards Action data TYPEs 
 * 
 * 128-255 0x0080-0x00FF Expert Review (see mailing list information in [RFC6895]) or Standards Action Q TYPEs, Meta
 * TYPEs 
 * 
 * 256-61439 0x0100-0xEFFF Expert Review (see mailing list information in
 * [RFC6895]) or Standards Action data RRTYPEs 
 * 
 * 61440-65279 0xF000-0xFEFF
 * Reserved for future use (IETF Review required to define use) 
 * 
 * 65280-65534 0xFF00-0xFFFE Reserved for Private Use 65535 0xFFFF Reserved (Standards
 * Action)
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
