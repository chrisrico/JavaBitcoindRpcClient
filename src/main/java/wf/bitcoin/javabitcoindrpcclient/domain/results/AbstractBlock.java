package wf.bitcoin.javabitcoindrpcclient.domain.results;

import java.math.BigDecimal;
import java.util.Date;

public abstract class AbstractBlock {
    public final String hash;
    public final int confirmations;
    public final int size;
    public final int strippedSize;
    public final int weight;
    public final int height;
    public final int version;
    public final String versionHex;
    public final String merkleRoot;
    public final Date time;
    public final Date medianTime;
    public final long nonce;
    public final String bits;
    public final BigDecimal difficulty;
    public final String chainwork;
    public final String previousBlockHash;
    public final String nextBlockHash;

    public AbstractBlock(String hash, int confirmations, int size, int strippedSize, int weight, int height, int version, String versionHex, String merkleRoot, Date time, Date medianTime, long nonce, String bits, BigDecimal difficulty, String chainwork, String previousBlockHash, String nextBlockHash) {
        this.hash = hash;
        this.confirmations = confirmations;
        this.size = size;
        this.strippedSize = strippedSize;
        this.weight = weight;
        this.height = height;
        this.version = version;
        this.versionHex = versionHex;
        this.merkleRoot = merkleRoot;
        this.time = time;
        this.medianTime = medianTime;
        this.nonce = nonce;
        this.bits = bits;
        this.difficulty = difficulty;
        this.chainwork = chainwork;
        this.previousBlockHash = previousBlockHash;
        this.nextBlockHash = nextBlockHash;
    }
}
