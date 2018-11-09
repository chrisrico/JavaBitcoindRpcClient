package wf.bitcoin.javabitcoindrpcclient.domain.results;

/**
 * Created by chrisrico on 1/19/18.
 */
public class SignRawTransactionResult {
    public final String hex;
    public final boolean complete;
    public final SignTransactionError[] errors;

    public SignRawTransactionResult(String hex, boolean complete, SignTransactionError[] errors) {
        this.hex = hex;
        this.complete = complete;
        this.errors = errors;
    }

    public static class SignTransactionError {
        public final String txid;
        public final int vout;
        public final String scriptSig;
        public final long sequence;
        public final String error;

        public SignTransactionError(String txid, int vout, String scriptSig, long sequence, String error) {
            this.txid = txid;
            this.vout = vout;
            this.scriptSig = scriptSig;
            this.sequence = sequence;
            this.error = error;
        }
    }
}
