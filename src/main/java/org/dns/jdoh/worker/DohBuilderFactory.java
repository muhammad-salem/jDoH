package org.dns.jdoh.worker;


import org.dns.jdoh.dnsserver.RequestSupplier;

import java.net.InetAddress;
import java.util.*;

public class DohBuilderFactory {

    private static final Map<String, LinkedList<InetAddress>> DNS_CACHE = new HashMap<>();

    public static DohBuilderFactory newInstance() {
        return new DohBuilderFactory(new DohBuilder(DNS_CACHE));
    }

    private DohBuilder builder;
    private DohBuilderFactory(DohBuilder builder){
        this.builder = builder;
    }

    public DohBuilder getBuilder() {
        return builder;
    }

    public void useCloudflarDOH() {
        this.builder.setRequestMessage(RequestSupplier.CLOUDFLARE);
    }

    public void useGoogleDOH() {
        this.builder.setRequestMessage(RequestSupplier.GOOGLE);
    }


}
