package wf.bitcoin.javabitcoindrpcclient.domain.results;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by chrisrico on 1/18/18.
 */
public class RawTransaction {
    public final String txid;
    public final String hash;
    public final long size;
    public final long vsize;
    public final int version;
    public final long lockTime;
    public final In[] vIn; //TODO: Create special interface instead of this
    public final Out[] vOut; //TODO: Create special interface instead of this

    public RawTransaction(String txid, String hash, int version, long lockTime, long size, long vsize, In[] vIn, Out[] vOut) {
        this.txid = txid;
        this.hash = hash;
        this.size = size;
        this.vsize = vsize;
        this.version = version;
        this.lockTime = lockTime;
        this.vIn = vIn;
        this.vOut = vOut;
    }

    class In extends TxInput {
        public final Map<String, Object> scriptSig;
        public final String[] txinwitness;
        public final long sequence;

        public In(String txid, int vout, Map<String, Object> scriptSig, String[] txinwitness, long sequence) {
            super(txid, vout);
            this.scriptSig = scriptSig;
            this.txinwitness = txinwitness;
            this.sequence = sequence;
        }
    }

    class Out {
        public final BigDecimal value;
        public final int n;
        public final Out.ScriptPubKey scriptPubKey;

        Out(BigDecimal value, int n, ScriptPubKey scriptPubKey) {
            this.value = value;
            this.n = n;
            this.scriptPubKey = scriptPubKey;
        }

        class ScriptPubKey {
            public final String asm;
            public final String hex;
            public final int reqSigs;
            public final String type;
            public final String[] addresses;

            ScriptPubKey(String asm, String hex, int reqSigs, String type, String[] addresses) {
                this.asm = asm;
                this.hex = hex;
                this.reqSigs = reqSigs;
                this.type = type;
                this.addresses = addresses;
            }
        }
    }
}
