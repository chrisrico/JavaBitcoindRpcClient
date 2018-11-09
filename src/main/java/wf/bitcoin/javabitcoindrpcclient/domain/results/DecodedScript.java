package wf.bitcoin.javabitcoindrpcclient.domain.results;

/**
 * Created by chrisrico on 1/18/18.
 */
public class DecodedScript {
    public final String asm;
    public final String hex;
    public final String type;
    public final int reqSigs;
    public final String[] addresses;
    public final String p2sh;

    public DecodedScript(String asm, String hex, String type, int reqSigs, String[] addresses, String p2sh) {
        this.asm = asm;
        this.hex = hex;
        this.type = type;
        this.reqSigs = reqSigs;
        this.addresses = addresses;
        this.p2sh = p2sh;
    }
}
