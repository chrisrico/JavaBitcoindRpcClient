package wf.bitcoin.javabitcoindrpcclient.domain.results;

/**
 * Created by chrisrico on 1/18/18.
 */

public class TxInput {
    public final String txid;
    public final int vout;

    public TxInput(String txid, int vout) {
        this.txid = txid;
        this.vout = vout;
    }
}

