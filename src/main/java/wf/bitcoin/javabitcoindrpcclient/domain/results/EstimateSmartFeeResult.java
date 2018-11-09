package wf.bitcoin.javabitcoindrpcclient.domain.results;

import java.math.BigDecimal;

/**
 * Created by chrisrico on 1/18/18.
 */
public class EstimateSmartFeeResult {
    public final String[] errors;
    public final BigDecimal feeRate;
    public final int blocks;

    public EstimateSmartFeeResult(BigDecimal feerate, String[] errors, int blocks) {
        this.errors = errors;
        this.feeRate = feerate;
        this.blocks = blocks;
    }
}
