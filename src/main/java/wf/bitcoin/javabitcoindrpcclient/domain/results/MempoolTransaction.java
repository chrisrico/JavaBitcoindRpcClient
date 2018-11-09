package wf.bitcoin.javabitcoindrpcclient.domain.results;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by chrisrico on 1/19/18.
 */
public class MempoolTransaction  {
    public final int size;
    public final BigDecimal fee;
    public final BigDecimal modifiedFee;
    public final Date time;
    public final int height;
    public final int descendantCount;
    public final int descendentSize;
    public final BigDecimal descendentFees;
    public final int ancestorCount;
    public final int ancestorSize;
    public final BigDecimal ancestorFees;
    public final String[] depends;

    public MempoolTransaction(int size, BigDecimal fee, BigDecimal modifiedfee, Date time, int height, int descendantcount, int descendantsize, BigDecimal descendantfees, int ancestorcount, int ancestorsize, BigDecimal ancestorfees, String[] depends) {
        this.size = size;
        this.fee = fee;
        this.modifiedFee = modifiedfee;
        this.time = time;
        this.height = height;
        this.descendantCount = descendantcount;
        this.descendentSize = descendantsize;
        this.descendentFees = descendantfees;
        this.ancestorCount = ancestorcount;
        this.ancestorSize = ancestorsize;
        this.ancestorFees = ancestorfees;
        this.depends = depends;
    }
}
