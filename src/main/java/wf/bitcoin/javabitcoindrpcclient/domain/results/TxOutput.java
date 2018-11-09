package wf.bitcoin.javabitcoindrpcclient.domain.results;

import java.math.BigDecimal;

/**
 * Created by chrisrico on 1/18/18.
 */
public class TxOutput {
    public final String address;
    public final BigDecimal amount;

    public TxOutput(String address, BigDecimal amount) {
        this.address = address;
        this.amount = amount;
    }
}
