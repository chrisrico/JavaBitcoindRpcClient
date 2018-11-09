package wf.bitcoin.javabitcoindrpcclient.domain.requests;

/**
 * Created by chrisrico on 1/19/18.
 */
public enum SigHashType {
    ALL("ALL"),
    NONE("NONE"),
    SINGLE("SINGLE"),
    ALL_ANYONECANPAY("ALL|ANYONECANPAY"),
    NONE_ANYONECANPAY("NONE|ANYONECANPAY"),
    SINGLE_ANYONECANPAY("SINGLE|ANYONECANPAY");

    private final String type;

    SigHashType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}