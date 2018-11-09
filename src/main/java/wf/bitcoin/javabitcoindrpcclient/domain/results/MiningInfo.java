package wf.bitcoin.javabitcoindrpcclient.domain.results;
import java.math.BigDecimal;

/**
 * Created by chrisrico on 1/18/18.
 */
public class MiningInfo {
    public final int blocks;
    public final int getCurrentBlockWeight;
    public final int currentBlockWeight;
    public final int currentBlockTx;
    public final BigDecimal difficulty;
    public final String errors;
    public final BigDecimal networkKHashps;
    public final int pooledTx;
    public final String chain;

    public MiningInfo(int blocks, int getCurrentBlockWeight, int currentBlockWeight, int currentBlockTx, BigDecimal difficulty, String errors, BigDecimal networkKHashps, int pooledTx, String chain) {
        this.blocks = blocks;
        this.getCurrentBlockWeight = getCurrentBlockWeight;
        this.currentBlockWeight = currentBlockWeight;
        this.currentBlockTx = currentBlockTx;
        this.difficulty = difficulty;
        this.errors = errors;
        this.networkKHashps = networkKHashps;
        this.pooledTx = pooledTx;
        this.chain = chain;
    }
}
