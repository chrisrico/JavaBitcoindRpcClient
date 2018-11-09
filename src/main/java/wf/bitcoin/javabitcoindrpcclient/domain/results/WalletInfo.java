package wf.bitcoin.javabitcoindrpcclient.domain.results;

import java.math.BigDecimal;

/**
 * Created by chrisrico on 1/18/18.
 */
public class WalletInfo {
    public final String walletName;
    public final long walletVersion;
    public final BigDecimal balance;
    public final BigDecimal unconfirmedBalance;
    public final BigDecimal immatureBalance;
    public final long txCount;
    public final long keyPoolOldest;
    public final long keyPoolSize;
    public final long keyPoolSizeHdInternal;
    public final BigDecimal payTxFee;
    public final long unlockedUntil;
    public final String hdMasterKeyId;

    public WalletInfo(String walletname, long walletversion, BigDecimal balance, BigDecimal unconfirmed_balance, BigDecimal immature_balance, long txcount, long keypoololdest, long keypoolsize, long keypoolsize_hd_internal, BigDecimal paytxfee, long unlocked_until, String hdmasterkeyid) {
        this.walletName = walletname;
        this.walletVersion = walletversion;
        this.balance = balance;
        this.unconfirmedBalance = unconfirmed_balance;
        this.immatureBalance = immature_balance;
        this.txCount = txcount;
        this.keyPoolOldest = keypoololdest;
        this.keyPoolSize = keypoolsize;
        this.keyPoolSizeHdInternal = keypoolsize_hd_internal;
        this.unlockedUntil = unlocked_until;
        this.payTxFee = paytxfee;
        this.hdMasterKeyId = hdmasterkeyid;
    }
}
