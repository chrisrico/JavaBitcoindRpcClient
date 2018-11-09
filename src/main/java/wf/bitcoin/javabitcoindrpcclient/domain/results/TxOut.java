package wf.bitcoin.javabitcoindrpcclient.domain.results;

import java.math.BigDecimal;

/**
 * Created by chrisrico on 1/18/18.
 */
public class TxOut {
    public final String bestBlock;
    public final long confirmations;
    public final BigDecimal value;
    public final DecodedScript scriptPubKey;
    public final boolean coinbase;

    public TxOut(String bestblock, long confirmations, BigDecimal value, DecodedScript scriptPubKey, boolean coinbase) {
        this.bestBlock = bestblock;
        this.confirmations = confirmations;
        this.value = value;
        this.scriptPubKey = scriptPubKey;
        this.coinbase = coinbase;
    }
}