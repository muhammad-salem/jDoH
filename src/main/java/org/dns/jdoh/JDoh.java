package org.dns.jdoh;

import org.dns.jdoh.worker.DohBuilder;
import org.dns.jdoh.worker.DohBuilderFactory;
import org.dns.jdoh.worker.DohLookup;

import java.io.IOException;
import java.net.UnknownHostException;

public class JDoh {

	public static void main(String[] args) throws IOException {
		DohBuilderFactory factory = DohBuilderFactory.newInstance();
		factory.useCloudflarDOH();
//		factory.useGoogleDOH();
		DohBuilder builder = factory.getBuilder();
		DohLookup lookup = builder.build();

		System.out.println( lookup.lookupAllHostAddr("google.com").length);
		System.out.println( lookup.lookupAllHostAddr("facebook.com").length);
	}

}
