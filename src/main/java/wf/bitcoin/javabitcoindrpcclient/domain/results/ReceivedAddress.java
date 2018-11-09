package wf.bitcoin.javabitcoindrpcclient.domain.results;

import java.math.BigDecimal;

/**
 * Created by chrisrico on 1/18/18.
 */
public class ReceivedAddress {
    public final boolean involvesWatchonly;
    public final String address;
    public final String account;
    public final BigDecimal amount;
    public final int confirmations;
    public final String label;
    public final String[] txids;

    public ReceivedAddress(boolean involvesWatchonly, String address, String account, BigDecimal amount, int confirmations, String label, String[] txids) {
        this.involvesWatchonly = involvesWatchonly;
        this.address = address;
        this.account = account;
        this.amount = amount;
        this.confirmations = confirmations;
        this.label = label;
        this.txids = txids;
    }
}
