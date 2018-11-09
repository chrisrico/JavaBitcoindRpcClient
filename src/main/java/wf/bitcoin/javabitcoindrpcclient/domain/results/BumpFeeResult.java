package wf.bitcoin.javabitcoindrpcclient.domain.results;

import java.math.BigDecimal;

public class BumpFeeResult {
    public final String txid;
    public final BigDecimal origfee;
    public final BigDecimal fee;
    public final String[] errors;

    public BumpFeeResult(String txid, BigDecimal origfee, BigDecimal fee, String[] errors) {
        this.txid = txid;
        this.origfee = origfee;
        this.fee = fee;
        this.errors = errors;
    }
}
