package wf.bitcoin.javabitcoindrpcclient.domain.results;

/**
 * Created by chrisrico on 1/18/18.
 */
public class NodeAddress {
    public final String address;
    public final String connected;

    public NodeAddress(String address, String connected) {
        this.address = address;
        this.connected = connected;
    }
}
