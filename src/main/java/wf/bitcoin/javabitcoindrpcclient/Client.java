package wf.bitcoin.javabitcoindrpcclient;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import wf.bitcoin.javabitcoindrpcclient.domain.requests.*;
import wf.bitcoin.javabitcoindrpcclient.domain.results.*;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Stream;

public class Client implements IClient {
    public static final Charset UTF8 = Charset.forName("UTF-8");

    protected final JsonRpcHttpClient client;

    public Client(final URL url) throws MalformedURLException, URISyntaxException {
        URI noAuth = new URI(url.getProtocol(), null, url.getHost(), url.getPort(), url.getPath(), url.getQuery(), null);

        HashMap<String, String> headers = new HashMap<>();
        if (url.getUserInfo() != null) {
            String auth = Base64.getEncoder().encodeToString(url.getUserInfo().getBytes(UTF8));
            headers.put("Authorization", "Basic " + auth);
        }

        client = new JsonRpcHttpClient(noAuth.toURL(), headers);
        ObjectMapper mapper = client.getObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(BigDecimal.class, new BitcoinAmountSerializer());

        mapper.registerModules(module, new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));
        mapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
    }

    public Client(final String rpcUrl) throws MalformedURLException, URISyntaxException {
        this(new URL(rpcUrl));
    }

    public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        client.setHostNameVerifier(hostnameVerifier);
    }

    public void setSslContext(SSLContext context) {
        client.setSslContext(context);
    }

    protected void invoke(String method, Object... params) throws ClientException {
        try {
            client.invoke(method, nullTerminated(params));
        } catch (Throwable ex) {
            throw new ClientException(method, Arrays.deepToString(params), ex);
        }
    }

    @SuppressWarnings("unchecked")
    protected <T> T invoke(JavaType type, String method, Object... params) throws ClientException {
        try {
            return (T) client.invoke(method, nullTerminated(params), type);
        } catch (Throwable ex) {
            throw new ClientException(method, Arrays.deepToString(params), ex);
        }
    }

    protected <T> T invoke(Class<T> cls, String method, Object... params) throws ClientException {
        try {
            return client.invoke(method, nullTerminated(params), cls);
        } catch (Throwable ex) {
            throw new ClientException(method, Arrays.deepToString(params), ex);
        }
    }

    private void invokeVar(String method, Object[]... params) throws ClientException {
        Object[] flattened = Stream.of(params).flatMap(Stream::of).toArray();
        invoke(method, flattened);
    }

    private <T> T invokeVar(Class<T> cls, String method, Object[]... params) throws ClientException {
        Object[] flattened = Stream.of(params).flatMap(Stream::of).toArray();
        return invoke(cls, method, flattened);
    }

    private Object[] nullTerminated(Object[] params) {
        List<Object> result = new ArrayList<>();
        for (Object param : params) {
            if (param == null) break;
            result.add(param);
        }
        return result.toArray();
    }

    private MapType getMapType(Class<?> valueType) {
        return client.getObjectMapper().getTypeFactory().constructMapType(Map.class, String.class, valueType);
    }

    //region Blockchain
    @Override
    public String getBestBlockHash() throws ClientException {
        return invoke(String.class, "getbestblockhash");
    }

    @Override
    public String getBlockHex(String blockHash) throws ClientException {
        return invoke(String.class, "getblock", 0);
    }

    @Override
    public Block getBlock(String blockhash) throws ClientException {
        return invoke(Block.class, "getblock", 1);
    }

    @Override
    public BlockWithTransactions getBlockWithTransactions(String blockHash) throws ClientException {
        return invoke(BlockWithTransactions.class, "getblock", 2);
    }

    @Override
    public BlockChainInfo getBlockChainInfo() throws ClientException {
        return invoke(BlockChainInfo.class, "getblockchaininfo");
    }

    @Override
    public int getBlockCount() throws ClientException {
        return invoke(int.class, "getblockcount");
    }

    @Override
    public String getBlockHash(int height) throws ClientException {
        return invoke(String.class, "getblockhash");
    }

    @Override
    public BigDecimal getDifficulty() throws ClientException {
        return invoke(BigDecimal.class, "getdifficulty");
    }

    @Override
    public Map<String, MempoolTransaction> getRawMempoolWithTransactions() throws ClientException {
        return invoke(getMapType(MempoolTransaction.class), "getrawmempool", true);
    }

    @Override
    public String[] getRawMempool() throws ClientException {
        return invoke(String[].class, "getrawmempool");
    }

    @Override
    public TxOut getTxOut(String txid, long vout) throws ClientException {
        return invoke(TxOut.class, "gettxout", txid, vout);
    }

    @Override
    public TxOutSetInfo getTxOutSetInfo() throws ClientException {
        return invoke(TxOutSetInfo.class, "gettxoutsetinfo");
    }

    @Override
    public boolean verifyChain() throws ClientException {
        return invoke(boolean.class, "verifychain");
    }
    //endregion

    //region Control
    @Override
    public Info getInfo() throws ClientException {
        return invoke(Info.class, "getinfo");
    }

    @Override
    public void stop() throws ClientException {
        invoke("stop");
    }
    //endregion

    //region Generating
    @Override
    public void generate(int blocks, int maxTries) throws ClientException {
        invoke("generate", blocks, maxTries);
    }
    //endregion

    //region Mining
    @Override
    public MiningInfo getMiningInfo() throws ClientException {
        return invoke(MiningInfo.class, "getmininginfo");
    }

    @Override
    public BigDecimal getNetworkHashPs() throws ClientException {
        return invoke(BigDecimal.class, "getnetworkhashps");
    }

    @Override
    public BigDecimal getNetworkHashPs(int height) throws ClientException {
        return invoke(BigDecimal.class, "getnetworkhashps", height);
    }

    @Override
    public void submitBlock(String hex) throws ClientException {
        invoke("submitblock", hex);
    }
    //endregion

    //region Network
    @Override
    public void addNode(String node, NodeCommand command) throws ClientException {
        invoke("addnode", node, command);
    }

    @Override
    public NodeInfo[] getAddedNodeInfo(String node) throws ClientException {
        return invoke(NodeInfo[].class, "getaddednodeinfo", node);
    }

    @Override
    public NodeInfo[] getAddedNodeInfo() throws ClientException {
        return invoke(NodeInfo[].class, "getaddednodeinfo");
    }

    @Override
    public long getConnectionCount() throws ClientException {
        return invoke(long.class, "getconnectioncount");
    }

    @Override
    public NetTotals getNetTotals() throws ClientException {
        return invoke(NetTotals.class, "getnettotals");
    }

    @Override
    public NetworkInfo getNetworkInfo() throws ClientException {
        return invoke(NetworkInfo.class, "getnetworkinfo");
    }

    @Override
    public PeerInfoResult[] getPeerInfo() throws ClientException {
        return invoke(PeerInfoResult[].class, "getpeerinfo");
    }

    @Override
    public void ping() throws ClientException {
        invoke("ping");
    }
    //endregion

    //region RawTransactions
    @Override
    public String combineRawTransaction(String... hex) throws ClientException {
        return invoke(String.class, "combinerawtransaction", new Object[]{hex});
    }

    @Override
    public String createRawTransaction(TxInput[] inputs, Map<String, BigDecimal> outputs, int locktime, boolean replaceable) throws ClientException {
        return createRawTransactionVar(inputs, outputs, locktime, replaceable);
    }

    @Override
    public String createRawTransaction(TxInput[] inputs, Map<String, BigDecimal> outputs, int locktime) throws ClientException {
        return createRawTransactionVar(inputs, outputs, locktime);
    }

    @Override
    public String createRawTransaction(TxInput[] inputs, Map<String, BigDecimal> outputs) throws ClientException {
        return createRawTransactionVar(inputs, outputs);
    }

    @Override
    public String createRawTransactionVar(TxInput[] inputs, Map<String, BigDecimal> outputs, Object... params) throws ClientException {
        if (inputs == null) inputs = new TxInput[0];
        if (outputs == null) outputs = new HashMap<>();
        return invokeVar(String.class, "createrawtransaction", new Object[]{inputs, outputs}, params);
    }

    @Override
    public DecodedScript decodeScript(String hex) throws ClientException {
        return invoke(DecodedScript.class, "decodescript", hex);
    }

    @Override
    public RawTransaction getRawTransaction(String txid) throws ClientException {
        return invoke(RawTransaction.class, "getrawtransaction", txid);
    }

    @Override
    public String fundRawTransaction(String hex, FundRawTransactionOptions options) throws ClientException {
        if (options == null) new FundRawTransactionOptions();
        return invoke(String.class, "fundrawtransaction", hex, options);
    }

    @Override
    public String fundRawTransaction(String hex) throws ClientException {
        return invoke(String.class, "fundrawtransaction", hex);
    }

    @Override
    public String sendRawTransaction(String hex, boolean allowHighFees) throws ClientException {
        return invoke(String.class, "sendrawtransaction", hex, allowHighFees);
    }

    @Override
    public String sendRawTransaction(String hex) throws ClientException {
        return invoke(String.class, "sendrawtransaction", hex);
    }

    @Override
    public SignRawTransactionResult signRawTransaction(String hex, TxInputExtended[] inputs, String[] privateKeys, SigHashType sigHashType) throws ClientException {
        return signRawTransactionVar(hex, inputs, privateKeys, sigHashType);
    }

    @Override
    public SignRawTransactionResult signRawTransaction(String hex, TxInputExtended[] inputs, String[] privateKeys) throws ClientException {
        return signRawTransactionVar(hex, inputs, privateKeys);
    }

    @Override
    public SignRawTransactionResult signRawTransaction(String hex, TxInputExtended[] inputs) throws ClientException {
        return signRawTransactionVar(hex, inputs);
    }

    @Override
    public SignRawTransactionResult signRawTransaction(String hex) throws ClientException {
        return signRawTransactionVar(hex);
    }

    @Override
    public SignRawTransactionResult signRawTransactionVar(String hex, Object... params) throws ClientException {
        return invokeVar(SignRawTransactionResult.class, "signrawtransaction", new Object[]{hex}, params);
    }
    //endregion

    //region Util
    @Override
    public MultiSig createMultiSig(int nRequired, String[] keys) throws ClientException {
        return invoke(MultiSig.class, "createmultisig", nRequired, keys);
    }

    @Override
    public BigDecimal estimateFee(int nBlocks) throws ClientException {
        return invoke(BigDecimal.class, "estimatefee", nBlocks);
    }

    @Override
    public EstimateSmartFeeResult estimateSmartFee(int nBlocks, EstimateMode mode) throws ClientException {
        return invoke(EstimateSmartFeeResult.class, "estimatesmartfee", nBlocks, mode);
    }

    @Override
    public EstimateSmartFeeResult estimateSmartFee(int nBlocks) throws ClientException {
        return invoke(EstimateSmartFeeResult.class, "estimatesmartfee", nBlocks);
    }

    @Override
    public AddressValidationResult validateAddress(String address) throws ClientException {
        return invoke(AddressValidationResult.class, "validateaddress", address);
    }

    @Override
    public boolean verifyMessage(String address, String signature, String message) throws ClientException {
        return invoke(boolean.class, "verifymessage", address, signature, message);
    }
    //endregion

    //region Wallet
    @Override
    public void abandonTransaction(String txid) throws ClientException {
        invoke("abandontransaction", txid);
    }

    @Override
    public String addMultiSigAddress(int nRequired, String[] keyObject, @Deprecated String account) throws ClientException {
        return invoke(String.class, "addmultisigaddress", nRequired, keyObject, account);
    }

    @Override
    public String addMultiSigAddress(int nRequired, String[] keyObject) throws ClientException {
        return invoke(String.class, "addmultisigaddress", nRequired, keyObject);
    }

    @Override
    public String addWitnessAddress(String address) throws ClientException {
        return invoke(String.class, "addwitnessaddress", address);
    }

    @Override
    public void backupWallet(String destination) throws ClientException {
        invoke("backupwallet", destination);
    }

    @Override
    public BumpFeeResult bumpFee(String txid, BumpFeeOptions options) throws ClientException {
        if (options == null) options = new BumpFeeOptions();
        return invoke(BumpFeeResult.class, "bumpfee", txid, options);
    }

    @Override
    public BumpFeeResult bumpFee(String txid) throws ClientException {
        return invoke(BumpFeeResult.class, "bumpfee", txid);
    }

    @Override
    public String dumpPrivKey(String address) throws ClientException {
        return invoke(String.class, "dumpprivkey", address);
    }

    @Override
    public void dumpWallet(String filename) throws ClientException {
        invoke("dumpwallet", filename);
    }

    @Override
    public void encryptWallet(String passphrase) throws ClientException {
        invoke("encryptwallet", passphrase);
    }

    @Override
    public String getAccount(String address) throws ClientException {
        return invoke(String.class, "getaccount", address);
    }

    @Override
    public String getAccountAddress(String address) throws ClientException {
        return invoke(String.class, "getaccountaddress", address);
    }

    @Override
    public String[] getAddressesByAccount(@Deprecated String account) throws ClientException {
        return invoke(String[].class, "getaddressesbyaccount", account);
    }

    @Override
    public BigDecimal getBalance(@Deprecated String account, int minConf, boolean includeWatchonly) throws ClientException {
        return getBalanceVar(account, minConf, includeWatchonly);
    }

    @Override
    public BigDecimal getBalance(@Deprecated String account, int minConf) throws ClientException {
        return getBalanceVar(account, minConf);
    }

    @Override
    public BigDecimal getBalance(@Deprecated String account) throws ClientException {
        return getBalanceVar(account);
    }

    @Override
    public BigDecimal getBalance() throws ClientException {
        return getBalanceVar();
    }

    @Override
    public BigDecimal getBalanceVar(Object... params) throws ClientException {
        return invoke(BigDecimal.class, "getbalance", params);
    }

    @Deprecated
    @Override
    public String getNewAddress(String account) throws ClientException {
        return invoke(String.class, "getnewaddress", account);
    }

    @Override
    public String getNewAddress() throws ClientException {
        return invoke(String.class, "getnewaddress");
    }

    @Override
    public String getRawChangeAddress() throws ClientException {
        return invoke(String.class, "getrawchangeaddress");
    }

    @Deprecated
    @Override
    public BigDecimal getReceivedByAccount(String account, int minConf) throws ClientException {
        return invoke(BigDecimal.class, "getreceivedbyaccount", account, minConf);
    }

    @Deprecated
    @Override
    public BigDecimal getReceivedByAccount(String account) throws ClientException {
        return invoke(BigDecimal.class, "getreceivedbyaccount", account);
    }

    @Override
    public BigDecimal getReceivedByAddress(String address, int minConf) throws ClientException {
        return invoke(BigDecimal.class, "getreceivedbyaddress", address, minConf);
    }

    @Override
    public BigDecimal getReceivedByAddress(String address) throws ClientException {
        return invoke(BigDecimal.class, "getreceivedbyaddress", address);
    }

    @Override
    public Transaction getTransaction(String txid, boolean includeWatchonly) throws ClientException {
        return invoke(Transaction.class, "gettransaction", txid, includeWatchonly);
    }

    @Override
    public Transaction getTransaction(String txid) throws ClientException {
        return invoke(Transaction.class, "gettransaction", txid);
    }

    @Override
    public BigDecimal getUnconfirmedBalance() throws ClientException {
        return invoke(BigDecimal.class, "getunconfirmedbalance");
    }

    @Override
    public WalletInfo getWalletInfo() throws ClientException {
        return invoke(WalletInfo.class, "getwalletinfo");
    }

    @Override
    public void importAddress(String address, String label, boolean rescan, boolean p2sh) throws ClientException {
        importAddressVar(address, label, rescan, p2sh);
    }

    @Override
    public void importAddress(String address, String label, boolean rescan) throws ClientException {
        importAddressVar(address, label, rescan);
    }

    @Override
    public void importAddress(String address, String label) throws ClientException {
        importAddressVar(address, label);
    }

    @Override
    public void importAddress(String address) throws ClientException {
        importAddressVar(address);
    }

    @Override
    public void importAddressVar(String address, Object... params) throws ClientException {
        invokeVar("importaddress", new Object[]{address}, params);
    }

    @Override
    public void importPrivKey(String privkey, String label, boolean rescan) throws ClientException {
        importPrivKeyVar(privkey, label, rescan);
    }

    @Override
    public void importPrivKey(String privkey, String label) throws ClientException {
        importPrivKeyVar(privkey, label);
    }

    @Override
    public void importPrivKey(String privkey) throws ClientException {
        importPrivKeyVar(privkey);
    }

    @Override
    public void importPrivKeyVar(String privKey, Object... params) throws ClientException {
        invokeVar("importprivkey", new Object[]{privKey}, params);
    }

    @Override
    public void importWallet(String filename) throws ClientException {
        invoke("importwallet", filename);
    }

    @Override
    public void keyPoolRefill() throws ClientException {
        invoke("keypoolrefill");
    }

    @Override
    public ReceivedAddress[] listReceivedByAddress(int minConf, boolean includeEmpty) throws ClientException {
        return listReceivedByAddressVar(minConf, includeEmpty);
    }

    @Override
    public ReceivedAddress[] listReceivedByAddress(int minConf) throws ClientException {
        return listReceivedByAddressVar(minConf);
    }

    @Override
    public ReceivedAddress[] listReceivedByAddress() throws ClientException {
        return listReceivedByAddressVar();
    }

    @Override
    public ReceivedAddress[] listReceivedByAddressVar(Object... params) throws ClientException {
        return invoke(ReceivedAddress[].class, "listreceivedbyaddress", params);
    }

    @Override
    public TransactionsSinceBlock listSinceBlock(String blockHash, int targetConfirmations) throws ClientException {
        return listSinceBlockVar(blockHash, targetConfirmations);
    }

    @Override
    public TransactionsSinceBlock listSinceBlock(String blockHash) throws ClientException {
        return listSinceBlockVar(blockHash);
    }

    @Override
    public TransactionsSinceBlock listSinceBlock() throws ClientException {
        return listSinceBlockVar();
    }

    @Override
    public TransactionsSinceBlock listSinceBlockVar(Object... params) throws ClientException {
        return invoke(TransactionsSinceBlock.class, "listsincelastblock", params);
    }

    @Override
    public Transaction[] listTransactions(@Deprecated String account, int count, int from) throws ClientException {
        return listTransactionsVar(account, count, from);
    }

    @Override
    public Transaction[] listTransactions(@Deprecated String account, int count) throws ClientException {
        return listTransactionsVar(account, count);
    }

    @Override
    public Transaction[] listTransactions(@Deprecated String account) throws ClientException {
        return listTransactionsVar(account);
    }

    @Override
    public Transaction[] listTransactions() throws ClientException {
        return listTransactionsVar();
    }

    @Override
    public Transaction[] listTransactionsVar(Object... params) throws ClientException {
        return invoke(Transaction[].class, "listtransactions", params);
    }

    @Override
    public Unspent[] listUnspent(int minConf, int maxConf, String[] addresses, boolean includeUnsafe, ListUnspentOptions options) throws ClientException {
        if (options == null) options = new ListUnspentOptions();
        return listUnspentVar(minConf, maxConf, addresses, includeUnsafe, options);
    }

    @Override
    public Unspent[] listUnspent(int minConf, int maxConf, String[] addresses, boolean includeUnsafe) throws ClientException {
        return listUnspentVar(minConf, maxConf, addresses, includeUnsafe);
    }

    @Override
    public Unspent[] listUnspent(int minConf, int maxConf, String... addresses) throws ClientException {
        return listUnspentVar(minConf, maxConf, addresses);
    }

    @Override
    public Unspent[] listUnspent(int minConf, int maxConf) throws ClientException {
        return listUnspentVar(minConf, maxConf);
    }

    @Override
    public Unspent[] listUnspent(int minConf) throws ClientException {
        return listUnspentVar(minConf);
    }

    @Override
    public Unspent[] listUnspent() throws ClientException {
        return listUnspentVar();
    }

    @Override
    public Unspent[] listUnspentVar(Object... params) throws ClientException {
        return invoke(Unspent[].class, "listunspent", params);
    }

    @Deprecated
    @Override
    public String move(String from, String to, BigDecimal amount, String comment) throws ClientException {
        return invoke(String.class, "move", from, to, amount, "", comment);
    }

    @Deprecated
    @Override
    public String move(String from, String to, BigDecimal amount) throws ClientException {
        return invoke(String.class, "move", from, to, amount);
    }

    @Deprecated
    @Override
    public String sendFrom(String account, String address, BigDecimal amount, int minConf, String comment, String commentTo) throws ClientException {
        return sendFromVar(account, address, amount, minConf, comment, commentTo);
    }

    @Deprecated
    @Override
    public String sendFrom(String account, String address, BigDecimal amount, int minConf, String comment) throws ClientException {
        return sendFromVar(account, address, amount, minConf, comment);
    }

    @Deprecated
    @Override
    public String sendFrom(String account, String address, BigDecimal amount, int minConf) throws ClientException {
        return sendFromVar(account, address, amount, minConf);
    }

    @Deprecated
    @Override
    public String sendFrom(String account, String address, BigDecimal amount) throws ClientException {
        return sendFromVar(account, address, amount);
    }

    @Deprecated
    @Override
    public String sendFromVar(String from, String address, BigDecimal amount, Object... params) throws ClientException {
        return invokeVar(String.class, "sendfrom", new Object[]{from, address, amount}, params);
    }

    @Override
    public String sendMany(@Deprecated String account, Map<String, BigDecimal> outputs, int minConf, String comment, String[] addresses, boolean replaceable, int conf_target, EstimateMode estimateMode) throws ClientException {
        return sendManyVar(account, outputs, minConf, comment, addresses, replaceable, conf_target, estimateMode);
    }

    @Override
    public String sendMany(@Deprecated String account, Map<String, BigDecimal> outputs, int minConf, String comment, String[] addresses, boolean replaceable, int conf_target) throws ClientException {
        return sendManyVar(account, outputs, minConf, comment, addresses, replaceable, conf_target);
    }

    @Override
    public String sendMany(@Deprecated String account, Map<String, BigDecimal> outputs, int minConf, String comment, String[] addresses, boolean replaceable) throws ClientException {
        return sendManyVar(account, outputs, minConf, comment, addresses, replaceable);
    }

    @Override
    public String sendMany(@Deprecated String account, Map<String, BigDecimal> outputs, int minConf, String comment, String[] addresses) throws ClientException {
        return sendManyVar(account, outputs, minConf, comment, addresses);
    }

    @Override
    public String sendMany(@Deprecated String account, Map<String, BigDecimal> outputs, int minConf, String comment) throws ClientException {
        return sendManyVar(account, outputs, minConf, comment);
    }

    @Override
    public String sendMany(@Deprecated String account, Map<String, BigDecimal> outputs) throws ClientException {
        return sendManyVar(account, outputs);
    }

    @Override
    public String sendManyVar(String account, Map<String, BigDecimal> outputs, Object... params) throws ClientException {
        return invokeVar(String.class, "sendmany", new Object[]{account, outputs}, params);
    }

    @Override
    public String sendToAddress(String address, BigDecimal amount, String comment, String commentTo, boolean subtractFeeFromAmount, boolean replaceable, int confTarget, EstimateMode estimateMode) throws ClientException {
        return sendToAddressVar(address, amount, comment, commentTo, subtractFeeFromAmount, replaceable, confTarget, estimateMode);
    }

    @Override
    public String sendToAddress(String address, BigDecimal amount, String comment, String commentTo, boolean subtractFeeFromAmount, boolean replaceable, int confTarget) throws ClientException {
        return sendToAddressVar(address, amount, comment, commentTo, subtractFeeFromAmount, replaceable, confTarget);
    }

    @Override
    public String sendToAddress(String address, BigDecimal amount, String comment, String commentTo, boolean subtractFeeFromAmount, boolean replaceable) throws ClientException {
        return sendToAddressVar(address, amount, comment, commentTo, subtractFeeFromAmount, replaceable);
    }

    @Override
    public String sendToAddress(String address, BigDecimal amount, String comment, String commentTo, boolean subtractFeeFromAmount) throws ClientException {
        return sendToAddressVar(address, amount, comment, commentTo, subtractFeeFromAmount);
    }

    @Override
    public String sendToAddress(String address, BigDecimal amount, String comment, String commentTo) throws ClientException {
        return sendToAddressVar(address, amount, comment, commentTo);
    }

    @Override
    public String sendToAddress(String address, BigDecimal amount, String comment) throws ClientException {
        return sendToAddressVar(address, amount, comment);
    }

    @Override
    public String sendToAddress(String address, BigDecimal amount) throws ClientException {
        return sendToAddressVar(address, amount);
    }

    @Override
    public String sendToAddressVar(String address, BigDecimal amount, Object... params) throws ClientException {
        return invokeVar(String.class, "sendtoaddress", new Object[]{address, amount}, params);
    }

    @Override
    public boolean setTxFee(BigDecimal amount) throws ClientException {
        return invoke(boolean.class, "settxfee", amount);
    }

    @Override
    public String signMessage(String address, String message) throws ClientException {
        return invoke(String.class, "signmessage", address, message);
    }

    @Override
    public void walletLock() throws ClientException {
        invoke("walletlock");
    }

    @Override
    public void walletPassPhrase(String passphrase, long timeout) throws ClientException {
        invoke("walletpassphrase", passphrase, timeout);
    }
    //endregion

    //region RegTest
    @Override
    public String[] generate(int numBlocks) throws ClientException {
        return invoke(String[].class, "generate", numBlocks);
    }

    @Override
    public void invalidateBlock(String hash) throws ClientException {
        invoke("invalidateblock", hash);
    }

    @Override
    public void reconsiderBlock(String hash) throws ClientException {
        invoke("reconsiderblock", hash);
    }
    //endregion
}
