package org.dns.jdoh.spi;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.AccessController;

import sun.net.spi.nameservice.NameService;
import sun.security.action.GetPropertyAction;

public class JDOHNameService implements NameService {
	
	private static final String GoogleDOH = "doh.google";
	private static final String CloudflareDOH = "doh.cloudflare";
	private static final String JdohProxyHttp = "doh.http.proxy";
	private static final String JdohProxyHttps = "doh.https.proxy";
	private static final String JdohProxySocks5 = "doh.socks5.proxy";
	private static final String RecordType = "doh.type";

	
	private boolean PreferV6 = false;
	
	
	
	static Object loadImpl(String implName) {
        Object impl = null;

        /*
         * Property "impl.prefix" will be prepended to the classname
         * of the implementation object we instantiate, to which we
         * delegate the real work (like native methods).  This
         * property can vary across implementations of the java.
         * classes.  The default is an empty String "".
         */
        String prefix = AccessController.doPrivileged(
                      new GetPropertyAction("impl.prefix", ""));
        try {
            impl = Class.forName("java.net." + prefix + implName).newInstance();
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: java.net." + prefix +
                               implName + ":\ncheck impl.prefix property " +
                               "in your properties file.");
        } catch (InstantiationException e) {
            System.err.println("Could not instantiate: java.net." + prefix +
                               implName + ":\ncheck impl.prefix property " +
                               "in your properties file.");
        } catch (IllegalAccessException e) {
            System.err.println("Cannot access class: java.net." + prefix +
                               implName + ":\ncheck impl.prefix property " +
                               "in your properties file.");
        }

        if (impl == null) {
            try {
                impl = Class.forName(implName).newInstance();
            } catch (Exception e) {
                throw new Error("System property impl.prefix incorrect");
            }
        }

        return  impl;
    }
	
	
	public JDOHNameService() {
		
	}


	@Override
	public String getHostByAddr(byte[] hostAdress) throws UnknownHostException {
		
		return null;
	}


	@Override
	public InetAddress[] lookupAllHostAddr(String hostname) throws UnknownHostException {
		
		return null;
	}


	
}
