package wf.bitcoin.javabitcoindrpcclient.domain.requests;

import java.math.BigDecimal;

/**
 * Created by chrisrico on 1/20/18.
 */
//TODO: JSON serialization
public class ListUnspentOptions {
    private BigDecimal minimumAmount;
    private BigDecimal maximumAmount;
    private int maximumCount;
    private BigDecimal minimumSumAmount;
}
