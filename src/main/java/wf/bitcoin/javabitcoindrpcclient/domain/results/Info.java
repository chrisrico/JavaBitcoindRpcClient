package wf.bitcoin.javabitcoindrpcclient.domain.results;

import java.math.BigDecimal;

/**
 * Created by chrisrico on 1/18/18.
 */
public class Info {
    public final long version;
    public final long protocolVersion;
    public final long walletVersion;
    public final BigDecimal balance;
    public final int blocks;
    public final int timeOffset;
    public final int connections;
    public final String proxy;
    public final BigDecimal difficulty;
    public final boolean testnet;
    public final long keyPoolOldest;
    public final long keyPoolSize;
    public final BigDecimal payTxFee;
    public final BigDecimal relayFee;
    public final String errors;

    public Info(long version, long protocolVersion, long walletVersion, BigDecimal balance, int blocks, int timeOffset, int connections, String proxy, BigDecimal difficulty, boolean testnet, long keyPoolOldest, long keyPoolSize, BigDecimal payTxFee, BigDecimal relayFee, String errors) {
        this.version = version;
        this.protocolVersion = protocolVersion;
        this.walletVersion = walletVersion;
        this.balance = balance;
        this.blocks = blocks;
        this.timeOffset = timeOffset;
        this.connections = connections;
        this.proxy = proxy;
        this.difficulty = difficulty;
        this.testnet = testnet;
        this.keyPoolOldest = keyPoolOldest;
        this.keyPoolSize = keyPoolSize;
        this.payTxFee = payTxFee;
        this.relayFee = relayFee;
        this.errors = errors;
    }
}
