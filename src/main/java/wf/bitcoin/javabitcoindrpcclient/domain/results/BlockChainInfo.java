package wf.bitcoin.javabitcoindrpcclient.domain.results;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Created by chrisrico on 1/18/18.
 */
public class BlockChainInfo {
    public final String chain;
    public final int blocks;
    public final int headers;
    public final String bestBlockHash;
    public final BigDecimal difficulty;
    public final Date medianTime;
    public final BigDecimal verificationProgress;
    public final String chainWork;
    public final boolean pruned;
    public final SoftFork[] softForks;
    public final Map<String, Bip9SoftFork> bip9Softforks;

    public BlockChainInfo(String chain, int blocks, int headers, String bestblockhash, BigDecimal difficulty, Date mediantime, BigDecimal verificationprogress, String chainwork, boolean pruned, SoftFork[] softforks, Map<String, Bip9SoftFork> bip9_softforks) {
        this.chain = chain;
        this.blocks = blocks;
        this.headers = headers;
        this.bestBlockHash = bestblockhash;
        this.difficulty = difficulty;
        this.medianTime = mediantime;
        this.verificationProgress = verificationprogress;
        this.chainWork = chainwork;
        this.pruned = pruned;
        this.softForks = softforks;
        this.bip9Softforks = bip9_softforks;
    }

    public static class SoftFork {
        public final String id;
        public final int version;
        public final Reject reject;

        SoftFork(String id, int version, Reject reject) {
            this.id = id;
            this.version = version;
            this.reject = reject;
        }

        public static class Reject {
            public final boolean status;

            Reject(boolean status) {
                this.status = status;
            }
        }
    }

    public static class Bip9SoftFork {
        public final String status;
        public final int bit;
        public final Date startTime;
        public final Date timeout;
        public final int since;
        public final Statistics statistics;

        Bip9SoftFork(String status, int bit, Date startTime, Date timeout, int since, Statistics statistics) {
            this.status = status;
            this.bit = bit;
            this.startTime = startTime;
            this.timeout = timeout;
            this.since = since;
            this.statistics = statistics;
        }

        public static class Statistics {
            public final int period;
            public final int threshold;
            public final int elapsed;
            public final int count;
            public final boolean possible;

            Statistics(int period, int threshold, int elapsed, int count, boolean possible) {
                this.period = period;
                this.threshold = threshold;
                this.elapsed = elapsed;
                this.count = count;
                this.possible = possible;
            }
        }
    }
}