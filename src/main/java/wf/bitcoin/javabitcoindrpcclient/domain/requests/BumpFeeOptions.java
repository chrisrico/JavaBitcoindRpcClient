package wf.bitcoin.javabitcoindrpcclient.domain.requests;

import java.math.BigDecimal;

//TODO: JSON serialiation
public class BumpFeeOptions {
    private int confTarget;
    private BigDecimal totalFee;
    private boolean replaceable;
    private EstimateMode estimateMode;
}
