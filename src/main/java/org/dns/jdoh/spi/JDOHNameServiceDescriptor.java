package org.dns.jdoh.spi;

import sun.net.spi.nameservice.NameService;
import sun.net.spi.nameservice.NameServiceDescriptor;;

public class JDOHNameServiceDescriptor implements NameServiceDescriptor {

	private static JDOHNameService jdohNameService = new JDOHNameService();
	
	
	@Override
	public NameService createNameService() throws Exception {
		return jdohNameService;
	}

	@Override
	public String getProviderName() {
		return "jdoh";
	}

	@Override
	public String getType() {
		return "dns";
	}

}
