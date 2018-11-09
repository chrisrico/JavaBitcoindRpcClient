package wf.bitcoin.javabitcoindrpcclient;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Chris Rico
 */

public class BitcoinAmountSerializer extends StdSerializer<BigDecimal> {
    protected BitcoinAmountSerializer() {
        super(BigDecimal.class);
    }

    @Override
    public void serialize(BigDecimal value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(value.setScale(8, RoundingMode.HALF_DOWN).toString());
    }
}
