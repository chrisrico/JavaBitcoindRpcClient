package wf.bitcoin.javabitcoindrpcclient.domain.results;
/**
 public final * Created by chrisrico on 1/18/18.
 public final */
public class MultiSig {
    public final String address;
    public final String redeemScript;

    public MultiSig(String address, String redeemScript) {
        this.address = address;
        this.redeemScript = redeemScript;
    }
}
