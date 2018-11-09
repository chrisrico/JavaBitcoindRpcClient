package wf.bitcoin.javabitcoindrpcclient.domain.results;

import java.math.BigDecimal;

/**
 * Created by chrisrico on 1/18/18.
 */
public class NetworkInfo {
    public final long version;
    public final String subversion;
    public final long protocolVersion;
    public final String localServices;
    public final boolean localRelay;
    public final long timeOffset;
    public final long connections;
    public final boolean networkActive;
    public final Network[] networks;
    public final BigDecimal relayFee;
    public final String[] localAddresses;
    public final String warnings;

    public NetworkInfo(long version, String subversion, long protocolversion, String localservices, boolean localrelay, long timeoffset, long connections, boolean networkactive, Network[] networks, BigDecimal felayfee, String[] localaddresses, String warnings) {
        this.version = version;
        this.subversion = subversion;
        this.protocolVersion = protocolversion;
        this.localServices = localservices;
        this.localRelay = localrelay;
        this.timeOffset = timeoffset;
        this.connections = connections;
        this.networkActive = networkactive;
        this.networks = networks;
        this.relayFee = felayfee;
        this.localAddresses = localaddresses;
        this.warnings = warnings;
    }

    public static class Network {
        public final String name;
        public final boolean limited;
        public final boolean reachable;
        public final String proxy;
        public final boolean proxyRandomizeCredentials;

        public Network(String name, boolean limited, boolean reachable, String proxy, boolean proxy_randomizer_credentials) {
            this.name = name;
            this.limited = limited;
            this.reachable = reachable;
            this.proxy = proxy;
            this.proxyRandomizeCredentials = proxy_randomizer_credentials;
        }
    }

}
