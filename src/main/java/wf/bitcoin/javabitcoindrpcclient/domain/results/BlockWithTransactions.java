package wf.bitcoin.javabitcoindrpcclient.domain.results;

import java.math.BigDecimal;
import java.util.Date;

public class BlockWithTransactions extends AbstractBlock {
    public final Transaction[] tx;

    public BlockWithTransactions(String hash, int confirmations, int size, int strippedSize, int weight, int height, int version, String versionHex, String merkleRoot, Transaction[] tx, Date time, Date medianTime, long nonce, String bits, BigDecimal difficulty, String chainwork, String previousBlockHash, String nextBlockHash) {
        super(hash, confirmations, size, strippedSize, weight, height, version, versionHex, merkleRoot, time, medianTime, nonce, bits, difficulty, chainwork, previousBlockHash, nextBlockHash);
        this.tx = tx;
    }
}
