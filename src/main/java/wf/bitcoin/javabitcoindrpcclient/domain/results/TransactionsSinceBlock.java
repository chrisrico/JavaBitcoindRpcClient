package wf.bitcoin.javabitcoindrpcclient.domain.results;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by chrisrico on 1/18/18.
 */
public class TransactionsSinceBlock {
    public final Transaction[] transactions;
    public final Transaction[] removed;
    public final String lastBlock;

    public TransactionsSinceBlock(Transaction[] transactions, Transaction[] removed, String lastblock) {
        this.transactions = transactions;
        this.removed = removed;
        this.lastBlock = lastblock;
    }

    public static class Transaction {
        public final String account;
        public final String address;
        public final TransactionCategory category;
        public final BigDecimal amount;
        public final int vout;
        public final BigDecimal fee;
        public final int confirmations;
        public final String blockHash;
        public final int blockIndex;
        public final String txid;
        public final Date time;
        public final Date timeReceived;
        public final BIP125Replaceable bip125Replaceable;
        public final boolean abandoned;
        public final String label;
        public final String to;

        Transaction(String account, String address, TransactionCategory category, BigDecimal amount, int vout, BigDecimal fee, int confirmations, String blockhash, int blockindex, String txid, Date time, Date timereceived, @JsonProperty("bip125-replaceable") BIP125Replaceable bip125Replaceable, boolean abandoned, String label, String to) {
            this.account = account;
            this.address = address;
            this.category = category;
            this.amount = amount;
            this.vout = vout;
            this.fee = fee;
            this.confirmations = confirmations;
            this.blockHash = blockhash;
            this.blockIndex = blockindex;
            this.txid = txid;
            this.time = time;
            this.timeReceived = timereceived;
            this.bip125Replaceable = bip125Replaceable;
            this.abandoned = abandoned;
            this.label = label;
            this.to = to;
        }
    }
}
