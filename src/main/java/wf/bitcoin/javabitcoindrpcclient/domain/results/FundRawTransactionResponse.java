package wf.bitcoin.javabitcoindrpcclient.domain.results;

import java.math.BigDecimal;

/**
 * Created by chrisrico on 1/19/18.
 */
public class FundRawTransactionResponse {
    public final String hex;
    public final BigDecimal fee;
    public final int changepos;

    public FundRawTransactionResponse(String hex, BigDecimal fee, int changepos) {
        this.hex = hex;
        this.fee = fee;
        this.changepos = changepos;
    }
}
