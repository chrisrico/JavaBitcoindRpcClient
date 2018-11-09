package wf.bitcoin.javabitcoindrpcclient.domain.results;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by chrisrico on 1/18/18.
 */
public class PeerInfoResult {
    public final long id;
    public final String addr;
    public final String addrBind;
    public final String addrLocal;
    public final String services;
    public final boolean relayTxes;
    public final long lastSend;
    public final long lastRecv;
    public final long bytesSent;
    public final long bytesRecv;
    public final long connTime;
    public final int timeOffset;
    public final BigDecimal pingTime;
    public final BigDecimal minPing;
    public final BigDecimal pingWait;
    public final long version;
    public final String subVer;
    public final boolean isInbound;
    public final boolean addNode;
    public final int startingHeight;
    public final long banScore;
    public final int syncedHeaders;
    public final int syncedBlocks;
    public final int[] inFlight;
    public final boolean whitelisted;
    public final Map<String, Long> bytesSentPerMsg;
    public final Map<String, Long> bytesRecvPerMsg;

    public PeerInfoResult(long id, String addr, String addrbind, String addrlocal, String services, boolean relaytxes, long lastsend, long lastrecv, long bytessent, long bytesrecv, long conntime, int timeoffset, BigDecimal pingtime, BigDecimal minping, BigDecimal pingwait, long version, String subver, boolean isinbound, boolean addnode, int startingheight, long banscore, int synced_headers, int synced_blocks, int[] inflight, boolean whitelisted, Map<String, Long> bytessent_per_msg, Map<String, Long> bytesrecv_per_msg) {
        this.id = id;
        this.addr = addr;
        this.addrBind = addrbind;
        this.addrLocal = addrlocal;
        this.services = services;
        this.relayTxes = relaytxes;
        this.lastSend = lastsend;
        this.lastRecv = lastrecv;
        this.bytesSent = bytessent;
        this.bytesRecv = bytesrecv;
        this.connTime = conntime;
        this.timeOffset = timeoffset;
        this.pingTime = pingtime;
        this.minPing = minping;
        this.pingWait = pingwait;
        this.version = version;
        this.subVer = subver;
        this.isInbound = isinbound;
        this.addNode = addnode;
        this.startingHeight = startingheight;
        this.banScore = banscore;
        this.syncedHeaders = synced_headers;
        this.syncedBlocks = synced_blocks;
        this.inFlight = inflight;
        this.whitelisted = whitelisted;
        this.bytesSentPerMsg = bytessent_per_msg;
        this.bytesRecvPerMsg = bytesrecv_per_msg;
    }
}
