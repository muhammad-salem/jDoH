package org.dns.jdoh.api;

public enum DnsRcode {

	NoError(0,	"NoError", "No Error"),
	FormErr(1,	"FormErr",	"Format Error"),
	ServFail(2,	"ServFail",	"Server Failure"),
	NXDomain(3,	"NXDomain",	"Non-Existent Domain"),
	NotImp(4,	"NotImp",	"Not Implemented"),
	Refused(5,	"Refused",	"Query Refused"),
	YXDomain(6,	"YXDomain",	"Name Exists when it should not"),
	YXRRSet(7,	"YXRRSet",	"RR Set Exists when it should not"),
	NXRRSet(8,	"NXRRSet",	"RR Set that should exist does not"),
	NotAuth(9,	"NotAuth",	"Server Not Authoritative for zone"),
//	NotAuth(9,	"NotAuth",	"Not Authorized"),
	NotZone(10,	"NotZone",	"Name not contained in zone"),
	Unassigned1(11,	"Unassigned", "Unassigned 11-15"),	
	BADVERS(16,	"BADVERS",	"Bad OPT Version"),
	BADSIG(16,	"BADSIG",	"TSIG Signature Failure"),
	BADKEY(17,	"BADKEY",	"Key not recognized"),
	BADTIME(18,	"BADTIME",	"Signature out of time window"),
	BADMODE(19,	"BADMODE",	"Bad TKEY Mode"),
	BADNAME(20,	"BADNAME",	"Duplicate key name"),
	BADALG(21,	"BADALG",	"Algorithm not supported"),
	BADTRUNC(22,	"BADTRUNC",	"Bad Truncation"),
	BADCOOKIE(23,	"BADCOOKIE",	"Bad/missing Server Cookie"),
	Unassigned2(24,	"Unassigned", "Unassigned 24-3840"),	
	Reserved1(3841,	"Reserved", "Reserved for Private Use 3841-4095"),
	Unassigned3(4096,	"Unassigned", "Unassigned 4096-65534"),
	Reserved2(65535,	"Reserved", "Reserved can be allocated by Standards Action	65535");
	
	
	
	int rcode;
	String name;
	String description;
	
	private DnsRcode(int rcode, String name, String description) {
		this.rcode = rcode;
		this.name = name;
		this.description = description;
	}
	
	
}
