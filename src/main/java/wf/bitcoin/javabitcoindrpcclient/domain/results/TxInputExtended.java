package wf.bitcoin.javabitcoindrpcclient.domain.results;

import java.math.BigDecimal;

public class TxInputExtended extends TxInput {
    public final String scriptPubKey;
    public final String redeemScript;
    public final BigDecimal amount;

    public TxInputExtended(String txid, int vout, String scriptPubKey, String redeemScript, BigDecimal amount) {
        super(txid, vout);
        this.scriptPubKey = scriptPubKey;
        this.redeemScript = redeemScript;
        this.amount = amount;
    }
}
