package wf.bitcoin.javabitcoindrpcclient;

import com.fasterxml.jackson.databind.JavaType;
import org.junit.Test;
import wf.bitcoin.javabitcoindrpcclient.domain.requests.SigHashType;
import wf.bitcoin.javabitcoindrpcclient.domain.results.BlockChainInfo;
import wf.bitcoin.javabitcoindrpcclient.domain.results.MempoolTransaction;
import wf.bitcoin.javabitcoindrpcclient.domain.results.SignRawTransactionResult;
import wf.bitcoin.javabitcoindrpcclient.domain.results.TxInputExtended;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by fpeters on 11-01-17.
 */

public class ClientTest {

    MyClientTest client;

    private String getJson(String name) {
        String resourcePath = "/json/" + name + ".json";
        try (InputStream stream = getClass().getResourceAsStream(resourcePath)) {
            Scanner scanner = new Scanner(stream).useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        } catch (IOException e) {
            throw new RuntimeException("Unable to read resource " + resourcePath, e);
        }
    }

    @Test
    public void getBalanceTest() throws Exception {
        client = new MyClientTest("getbalance");
        BigDecimal balance = client.getBalance();
        assertEquals(new BigDecimal("1.23456789"), balance);
    }

    @Test
    public void getBlockchainInfoTest() throws Exception {
        client = new MyClientTest("getblockchaininfo");
        BlockChainInfo info = client.getBlockChainInfo();
        assertEquals(new Date(1479168000), info.bip9Softforks.get("segwit").startTime);
    }

    @Test
    public void getRawMempoolTest() throws Exception {
        client = new MyClientTest("getrawmempool");
        String[] txids = client.getRawMempool();
        assertEquals(txids[0], "a09e41ad19ebfdb14c7ef78b39389369b459b5d2ec24ffffc110a9ac4f24b2b8");
    }

    @Test
    public void getRawMempoolVerboseTest() throws Exception {
        client = new MyClientTest("getrawmempool", "_verbose");
        Map<String, MempoolTransaction> txs = client.getRawMempoolWithTransactions();
        assertEquals(txs.get("ffbd6c9dfb10479ac11b3e036abde57d49ef10e229f67669ca356e04330eb8d8").fee, new BigDecimal("0.00003460"));
    }

    @Test
    public void signRawTransactionTest() throws Exception {
        client = new MyClientTest("signrawtransaction", "_privatekey");
        TxInputExtended[] inputList = {};
        String[] privateKeys = {"cSjzx3VAM1r9iLXLvL6N61oS3zKns9Z9DcocrbkEzesPTDHWm5r4"};
        SignRawTransactionResult result = client.signRawTransaction("0100000001B8B2244FACA910C1FFFF24ECD2B559B4699338398BF77E4CB1FDEB19AD419EA0010000001976A9144CB4C3B90994FEF58FABB6D8368302E917C6EFB188ACFFFFFFFF012E2600000000000017A9140B2D7ED4E5076383BA8E98B9B3BCE426B7A2EA1E8700000000",
                inputList, privateKeys, SigHashType.ALL);
        assertEquals("0100000001b8b2244faca910c1ffff24ecd2b559b4699338398bf77e4cb1fdeb19ad419ea0010000006b483045022100b68b7fe9cfabb32949af6747b6769dffcf2aa4170e4df2f0e9d0a4571989e94e02204cf506c210cdb6b6b4413bf251a0b57ebcf1b1b2d303ba6183239b557ef0a310012102ab46e1d7b997d8094e97bc06a21a054c2ef485fac512e2dc91eb9831af55af4effffffff012e2600000000000017a9140b2d7ed4e5076383ba8e98b9b3bce426b7a2ea1e8700000000",
                result.hex);
    }

    @Test
    public void signRawTransactionTestException() throws Exception {
        client = new MyClientTest("signrawtransaction", "_fail");
        TxInputExtended[] inputList = {};
        String[] privateKeys = {};
        try {
            client.signRawTransaction("0100000001B8B2244FACA910C1FFFF24ECD2B559B4699338398BF77E4CB1FDEB19AD419EA0010000001976A9144CB4C3B90994FEF58FABB6D8368302E917C6EFB188ACFFFFFFFF012E2600000000000017A9140B2D7ED4E5076383BA8E98B9B3BCE426B7A2EA1E8700000000", inputList, privateKeys, SigHashType.ALL);
        } catch (Exception e) {
            assertThat(e.getMessage(), is("Incomplete"));
        }
    }

    @Test
    public void signRawTransactionTest2() throws Exception {
        client = new MyClientTest("signrawtransaction", "");
        SignRawTransactionResult result = client.signRawTransaction("0100000001B8B2244FACA910C1FFFF24ECD2B559B4699338398BF77E4CB1FDEB19AD419EA0010000001976A9144CB4C3B90994FEF58FABB6D8368302E917C6EFB188ACFFFFFFFF012E2600000000000017A9140B2D7ED4E5076383BA8E98B9B3BCE426B7A2EA1E8700000000");
        assertEquals("0100000001b8b2244faca910c1ffff24ecd2b559b4699338398bf77e4cb1fdeb19ad419ea0010000006b483045022100b68b7fe9cfabb32949af6747b6769dffcf2aa4170e4df2f0e9d0a4571989e94e02204cf506c210cdb6b6b4413bf251a0b57ebcf1b1b2d303ba6183239b557ef0a310012102ab46e1d7b997d8094e97bc06a21a054c2ef485fac512e2dc91eb9831af55af4effffffff012e2600000000000017a9140b2d7ed4e5076383ba8e98b9b3bce426b7a2ea1e8700000000",
                result.hex);
    }

    class MyClientTest extends Client {
        String expectedMethod;
        String result;

        MyClientTest(String expectedMethod) throws MalformedURLException, URISyntaxException {
            this(expectedMethod, "");
        }

        MyClientTest(String expectedMethod, String suffix) throws MalformedURLException, URISyntaxException {
            super("http://test:user@localhost:18332");
            this.expectedMethod = expectedMethod;
            this.result = getJson(expectedMethod + suffix);
        }

        @Override
        protected void invoke(String method, Object... params) {
            assertEquals(expectedMethod, method);
        }

        @Override
        protected <T> T invoke(JavaType type, String method, Object... params) throws ClientException {
            assertEquals(expectedMethod, method);

            try {
                return client.getObjectMapper().readValue(result, type);
            } catch (IOException e) {
                e.printStackTrace();
                throw new ClientException("Unable to parse JSON as " + type.toString(), e);
            }
        }

        @Override
        protected <T> T invoke(Class<T> cls, String method, Object... params) throws ClientException {
            assertEquals(expectedMethod, method);

            try {
                return client.getObjectMapper().readValue(result, cls);
            } catch (IOException e) {
                e.printStackTrace();
                throw new ClientException("Unable to parse JSON as " + cls.toString(), e);
            }
        }
    }
}