package wf.bitcoin.javabitcoindrpcclient.domain.results;

import java.math.BigDecimal;

/**
 * Created by chrisrico on 1/18/18.
 */
public class Unspent {
    public final String txid;
    public final int vout;
    public final String address;
    public final String account;
    public final String scriptPubKey;
    public final BigDecimal amount;
    public final int confirmations;
    public final String redeemScript;
    public final boolean spendable;
    public final boolean solvable;
    public final boolean safe;

    public Unspent(String txid, int vout, String address, String account, String scriptPubKey, BigDecimal amount, int confirmations, String redeemScript, boolean spendable, boolean solvable, boolean safe) {
        this.txid = txid;
        this.vout = vout;
        this.address = address;
        this.account = account;
        this.scriptPubKey = scriptPubKey;
        this.amount = amount;
        this.confirmations = confirmations;
        this.redeemScript = redeemScript;
        this.spendable = spendable;
        this.solvable = solvable;
        this.safe = safe;
    }
}
