package wf.bitcoin.javabitcoindrpcclient.domain.results;

import java.math.BigDecimal;

/**
 * Created by chrisrico on 1/18/18.
 */
public class TxOutSetInfo {
    public final long height;
    public final String bestBlock;
    public final long transactions;
    public final long txOuts;
    public final long bogoSize;
    public final String hashSerialized;
    public final long diskSize;
    public final BigDecimal totalAmount;

    public TxOutSetInfo(long height, String bestblock, long transactions, long txouts, long bogoSize, String hash_serialized_2, long disk_size, BigDecimal total_amount) {
        this.height = height;
        this.bestBlock = bestblock;
        this.transactions = transactions;
        this.txOuts = txouts;
        this.bogoSize = bogoSize;
        this.hashSerialized = hash_serialized_2;
        this.diskSize = disk_size;
        this.totalAmount = total_amount;
    }
}
