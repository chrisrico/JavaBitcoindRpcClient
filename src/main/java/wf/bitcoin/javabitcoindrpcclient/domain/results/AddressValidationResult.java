package wf.bitcoin.javabitcoindrpcclient.domain.results;

/**
 * Created by chrisrico on 1/18/18.
 */
public class AddressValidationResult {
    public final boolean isValid;
    public final String address;
    public final boolean isMine;
    public final boolean isScript;
    public final String pubKey;
    public final boolean isCompressed;
    public final String account;

    public AddressValidationResult(boolean isvalid, String address, boolean ismine, boolean isscript, String pubkey, boolean iscompressed, String account) {
        this.isValid = isvalid;
        this.address = address;
        this.isMine = ismine;
        this.isScript = isscript;
        this.pubKey = pubkey;
        this.isCompressed = iscompressed;
        this.account = account;
    }
}
