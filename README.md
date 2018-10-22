# jDoH java client
DNS-Over-HTTPS

features
--------
 - resolve dns for java apps service
    > System.setProperty("sun.net.spi.nameservice.provider.1", "dns,jdoh");
 - use SSLSocket fot connection 
 - use JDK+7
 - add custon dns servers, Google and Cloudflare
    > factory.useCloudflarDOH();
      factory.useGoogleDOH();
 

TO:DO
-----

 - connect to dns server direct or over proxy (http/s, socks4/5, forward port/ssh)
 - convert udp dns request to doh, get the respose back to udp
 - support host file linux/window
 
 ex:
 ===
 ```java
    DohBuilderFactory factory = DohBuilderFactory.newInstance();
    factory.useCloudflarDOH();
    // factory.useGoogleDOH();
    // factory.useRequestMessage(new RequestMessage(){....}); 
    DohBuilder builder = factory.getBuilder();
    DohLookup lookup = builder.build();
    
    System.out.println( lookup.lookupAllHostAddr("google.com")[0]);
    System.out.println( lookup.lookupAllHostAddr("facebook.com")[0]);
 ```
