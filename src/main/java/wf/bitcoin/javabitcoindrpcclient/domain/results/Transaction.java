package wf.bitcoin.javabitcoindrpcclient.domain.results;

import java.math.BigDecimal;
import java.util.Date;

/**
 * returned by listsinceblock and listtransactions
 */
public class Transaction {
    public final BigDecimal amount;
    public final BigDecimal fee;
    public final int confirmations;
    public final String blockHash;
    public final int blockIndex;
    public final Date blockTime;
    public final String txId;
    public final Date time;
    public final Date timeReceived;
    public final BIP125Replaceable bip125Replaceable;
    public final Details details;
    public final String hex;

    public Transaction(BigDecimal amount, BigDecimal fee, int confirmations, String blockHash, int blockIndex, Date blockTime, String txId, Date time, Date timeReceived, BIP125Replaceable bip125Replaceable, Details details, String hex) {
        this.amount = amount;
        this.fee = fee;
        this.confirmations = confirmations;
        this.blockHash = blockHash;
        this.blockIndex = blockIndex;
        this.blockTime = blockTime;
        this.txId = txId;
        this.time = time;
        this.timeReceived = timeReceived;
        this.bip125Replaceable = bip125Replaceable;
        this.details = details;
        this.hex = hex;
    }

    public static class Details {
        public final String account;
        public final String address;
        public final String category;
        public final BigDecimal amount;
        public final String label;
        public final int vout;
        public final BigDecimal fee;
        public final boolean abandoned;

        Details(String account, String address, String category, BigDecimal amount, String label, int vout, BigDecimal fee, boolean abandoned) {
            this.account = account;
            this.address = address;
            this.category = category;
            this.amount = amount;
            this.label = label;
            this.vout = vout;
            this.fee = fee;
            this.abandoned = abandoned;
        }
    }
}
