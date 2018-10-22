package org.dns.jdoh.dnsserver;

import org.dns.jdoh.api.RequestMessage;

public enum  RequestSupplier implements RequestMessage {
    GOOGLE(
            "https://dns.google.com/resolve?",
            "dns.google.com",
            new byte[]{ (byte)216, (byte)58, (byte)205, (byte)174 },
            443,
            new String[] {"accept: application/dns-json", "Connection: keep-alive"},
            true
    ), CLOUDFLARE(
            "https://cloudflare-dns.com/dns-query?",
            "cloudflare-dns.com",
            new byte[]{ (byte)1 ,(byte)1, (byte)1, (byte)1 },
            443,
            new String[] {"accept: application/dns-json", "Connection: keep-alive"},
            false
    );

    RequestSupplier(String url, String hostname, byte[] hostIP, int port, String[] headers, boolean close) {
        this.url = url;
        this.hostname = hostname;
        this.hostIP = hostIP;
        this.port = port;
        this.headers = headers;
        this.closeConnection =close;
    }


    private String url;
    private String hostname;
    private byte[] hostIP;
    private int port;
    private String[] headers;
    private boolean closeConnection;

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getHostName() {
        return hostname;
    }

    @Override
    public byte[] getHostIP() {
        return hostIP;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public String[] getHeaders() {
        return headers;
    }

    @Override
    public boolean closeConnection() {
        return closeConnection;
    }
}
