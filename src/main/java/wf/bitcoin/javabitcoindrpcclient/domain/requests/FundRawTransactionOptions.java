package wf.bitcoin.javabitcoindrpcclient.domain.requests;

import java.math.BigDecimal;

/**
 * Created by chrisrico on 1/19/18.
 */
public class FundRawTransactionOptions {
    private String changeAddress;
    private int changePosition;
    private boolean includeWatching;
    private boolean lockUnspents;
    private BigDecimal feeRate;
    private int[] subtractFeeFromOutputs;
    private boolean replaceable;
    private int conf_target;
    private EstimateMode estimate_mode;

    public FundRawTransactionOptions() {
    }

    public String getChangeAddress() {
        return changeAddress;
    }

    public void setChangeAddress(String changeAddress) {
        this.changeAddress = changeAddress;
    }

    public int getChangePosition() {
        return changePosition;
    }

    public void setChangePosition(int changePosition) {
        this.changePosition = changePosition;
    }

    public boolean isIncludeWatching() {
        return includeWatching;
    }

    public void setIncludeWatching(boolean includeWatching) {
        this.includeWatching = includeWatching;
    }

    public boolean isLockUnspents() {
        return lockUnspents;
    }

    public void setLockUnspents(boolean lockUnspents) {
        this.lockUnspents = lockUnspents;
    }

    public BigDecimal getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }

    public int[] getSubtractFeeFromOutputs() {
        return subtractFeeFromOutputs;
    }

    public void setSubtractFeeFromOutputs(int[] subtractFeeFromOutputs) {
        this.subtractFeeFromOutputs = subtractFeeFromOutputs;
    }

    public boolean isReplaceable() {
        return replaceable;
    }

    public void setReplaceable(boolean replaceable) {
        this.replaceable = replaceable;
    }

    public int getConfirmationTarget() {
        return conf_target;
    }

    public void setConfirmationTarget(int confirmationTarget) {
        this.conf_target = confirmationTarget;
    }

    public EstimateMode getEstimateMode() {
        return estimate_mode;
    }

    public void setEstimateMode(EstimateMode estimateMode) {
        this.estimate_mode = estimateMode;
    }
}

