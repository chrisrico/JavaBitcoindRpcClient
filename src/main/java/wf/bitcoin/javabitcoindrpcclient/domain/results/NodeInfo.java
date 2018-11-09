package wf.bitcoin.javabitcoindrpcclient.domain.results;

/**
 * Created by chrisrico on 1/18/18.
 */
public class NodeInfo {
    public final String addedNode;
    public final boolean connected;
    public final NodeAddress[] addresses;

    public NodeInfo(String addedNode, boolean connected, NodeAddress[] addresses) {
        this.addedNode = addedNode;
        this.connected = connected;
        this.addresses = addresses;
    }
}
