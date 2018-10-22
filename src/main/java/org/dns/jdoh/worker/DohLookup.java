package org.dns.jdoh.worker;


import java.net.InetAddress;
import java.net.UnknownHostException;

public interface DohLookup {

    InetAddress[] lookupAllHostAddr(String s) throws UnknownHostException;
    String getHostByAddr(byte[] bytes) throws UnknownHostException;

}
