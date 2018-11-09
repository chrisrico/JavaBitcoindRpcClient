package wf.bitcoin.javabitcoindrpcclient.domain.results;

/**
 * Created by chrisrico on 1/18/18.
 */
public class NetTotals {
    public final long totalBytesRecv;
    public final long totalBytesSent;
    public final long timeMillis;
    public final UploadTarget uploadTarget;

    public NetTotals(long totalbytesrecv, long totalbytessent, long timenillis, UploadTarget uploadtarget) {
        this.totalBytesRecv = totalbytesrecv;
        this.totalBytesSent = totalbytessent;
        this.timeMillis = timenillis;
        this.uploadTarget = uploadtarget;
    }

    public static class UploadTarget {
        public final long timeFrame;
        public final int target;
        public final boolean targetReached;
        public final boolean serveHistoricalBlocks;
        public final long bytesLeftInCycle;
        public final long timeLeftInCycle;

        public UploadTarget(long timeframe, int target, boolean target_reached, boolean serve_historical_blocks, long bytes_left_in_cycle, long time_left_in_cycle) {
            this.timeFrame = timeframe;
            this.target = target;
            this.targetReached = target_reached;
            this.serveHistoricalBlocks = serve_historical_blocks;
            this.bytesLeftInCycle = bytes_left_in_cycle;
            this.timeLeftInCycle = time_left_in_cycle;
        }
    }
}
