/*
 * BitcoindRpcClient-JSON-RPC-Client License
 * 
 * Copyright (c) 2013, Mikhail Yevchenko.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the 
 * Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject
 * to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR
 * ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH
 * THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
 /*
 * Repackaged with simple additions for easier maven usage by Alessandro Polverini
 */
package wf.bitcoin.javabitcoindrpcclient;

import wf.bitcoin.javabitcoindrpcclient.domain.requests.*;
import wf.bitcoin.javabitcoindrpcclient.domain.results.*;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Mikhail Yevchenko m.ṥῥẚɱ.ѓѐḿởύḙ@azazar.com Small modifications by
 *         Alessandro Polverini polverini at gmail.com
 */
public interface IClient {
    //region Blockchain
    String getBestBlockHash() throws ClientException;

    String getBlockHex(String blockHash) throws ClientException;
    Block getBlock(String blockHash) throws ClientException;
    BlockWithTransactions getBlockWithTransactions(String blockHash) throws ClientException;

    BlockChainInfo getBlockChainInfo() throws ClientException;

    int getBlockCount() throws ClientException;

    String getBlockHash(int height) throws ClientException;

    //TODO: getblockheader "hash" ( verbose )
    //TODO: getchaintips
    //TODO: getchaintxstats ( nblocks blockhash )

    BigDecimal getDifficulty() throws ClientException;

    //TODO: getmempoolancestors txid (verbose)
    //TODO: getmempooldescendants txid (verbose)
    //TODO: getmempoolentry txid
    //TODO: getmempoolinfo

    Map<String, MempoolTransaction> getRawMempoolWithTransactions() throws ClientException;
    String[] getRawMempool() throws ClientException;

    TxOut getTxOut(String txId, long vout) throws ClientException;

    //TODO: gettxoutproof ["txid",...] ( blockhash )

    TxOutSetInfo getTxOutSetInfo() throws ClientException;

    //TODO: preciousblock "blockhash"

    boolean verifyChain() throws ClientException;
    //endregion

    //region Control
    /**
     * @return infos about the bitcoind instance
     * @throws ClientException
     */
    Info getInfo() throws ClientException;

    void stop() throws ClientException;
    //endregion

    //region Generating
    void generate(int blocks, int maxTries) throws ClientException;
    //TODO: generatetoaddress nblocks address (maxtries)
    //endregion

    //region Mining
    //TODO: getblocktemplate (TemplateRequest)

    /**
     * @return miningInfo about the bitcoind instance
     * @throws ClientException
     */
    MiningInfo getMiningInfo() throws ClientException;

    BigDecimal getNetworkHashPs() throws ClientException;
    BigDecimal getNetworkHashPs(int height) throws ClientException;

    //TODO: prioritisetransaction <txid> <dummy value> <fee delta>

    void submitBlock(String hexData) throws ClientException;
    //endregion

    //region Network
    void addNode(String node, NodeCommand command) throws ClientException;

    //TODO: clearbanned
    //TODO: disconnectnode "[address]" [nodeid]

    NodeInfo[] getAddedNodeInfo(String node) throws ClientException;
    NodeInfo[] getAddedNodeInfo() throws ClientException;

    long getConnectionCount() throws ClientException;

    NetTotals getNetTotals() throws ClientException;

    NetworkInfo getNetworkInfo() throws ClientException;

    PeerInfoResult[] getPeerInfo() throws ClientException;

    //TODO: listbanned

    void ping() throws ClientException;

    //TODO: setban "subnet" "add|remove" (bantime) (absolute)
    //endregion

    //region RawTransactions
    String combineRawTransaction(String... hex) throws ClientException;

    String createRawTransaction(TxInput[] inputs, Map<String, BigDecimal> outputs, int locktime, boolean replaceable) throws ClientException;
    String createRawTransaction(TxInput[] inputs, Map<String, BigDecimal> outputs, int locktime) throws ClientException;
    String createRawTransaction(TxInput[] inputs, Map<String, BigDecimal> outputs) throws ClientException;
    String createRawTransactionVar(TxInput[] inputs, Map<String, BigDecimal> outputs, Object... params) throws ClientException;

    DecodedScript decodeScript(String hex) throws ClientException;

    RawTransaction getRawTransaction(String txid) throws ClientException;

    String fundRawTransaction(String hex, FundRawTransactionOptions options) throws ClientException;
    String fundRawTransaction(String hex) throws ClientException;

    String sendRawTransaction(String hex, boolean allowHighFees) throws ClientException;
    String sendRawTransaction(String hex) throws ClientException;

    SignRawTransactionResult signRawTransaction(String hex, TxInputExtended[] inputs, String[] privateKeys, SigHashType sigHashType) throws ClientException;
    SignRawTransactionResult signRawTransaction(String hex, TxInputExtended[] inputs, String[] privateKeys) throws ClientException;
    SignRawTransactionResult signRawTransaction(String hex, TxInputExtended[] inputs) throws ClientException;
    SignRawTransactionResult signRawTransaction(String hex) throws ClientException;
    SignRawTransactionResult signRawTransactionVar(String hex, Object... params) throws ClientException;
    //endregion

    //region Util
    MultiSig createMultiSig(int nRequired, String[] keys) throws ClientException;

    BigDecimal estimateFee(int nBlocks) throws ClientException;

    EstimateSmartFeeResult estimateSmartFee(int nBlocks, EstimateMode mode) throws ClientException;
    EstimateSmartFeeResult estimateSmartFee(int nBlocks) throws ClientException;

    //TODO: signmessagewithprivkey "privkey" "message"

    AddressValidationResult validateAddress(String address) throws ClientException;

    boolean verifyMessage(String bitcoinAddress, String signature, String message) throws ClientException;
    //endregion

    //region Wallet
    void abandonTransaction(String txid) throws ClientException;

    //TODO: abortrescan

    String addMultiSigAddress(int nRequired, String[] keyObject, @Deprecated String account) throws ClientException;
    String addMultiSigAddress(int nRequired, String[] keyObject) throws ClientException;

    String addWitnessAddress(String address) throws ClientException;

    void backupWallet(String destination) throws ClientException;

    BumpFeeResult bumpFee(String txid, BumpFeeOptions options) throws ClientException;
    BumpFeeResult bumpFee(String txid) throws ClientException;

    String dumpPrivKey(String address) throws ClientException;

    void dumpWallet(String filename) throws ClientException;

    void encryptWallet(String passphrase) throws ClientException;

    @Deprecated String getAccount(String address) throws ClientException;

    @Deprecated String getAccountAddress(String address) throws ClientException;

    @Deprecated String[] getAddressesByAccount(@Deprecated String account) throws ClientException;

    /**
     * @param account
     * @param minConf
     * @return returns the balance in the account
     * @throws ClientException
     */
    BigDecimal getBalance(@Deprecated String account, int minConf, boolean includeWatchonly) throws ClientException;
    BigDecimal getBalance(@Deprecated String account, int minConf) throws ClientException;
    @Deprecated BigDecimal getBalance(String account) throws ClientException;
    BigDecimal getBalance() throws ClientException;
    BigDecimal getBalanceVar(Object... params) throws ClientException;

    @Deprecated String getNewAddress(String account) throws ClientException;
    String getNewAddress() throws ClientException;

    String getRawChangeAddress() throws ClientException;

    @Deprecated BigDecimal getReceivedByAccount(String account, int minConf) throws ClientException;
    @Deprecated BigDecimal getReceivedByAccount(String account) throws ClientException;

    /**
     * Returns the total amount received by &lt;bitcoinaddress&gt; in transactions
     * with at least [minconf] confirmations. While some might consider this
     * obvious, value reported by this only considers *receiving* transactions. It
     * does not check payments that have been made *from* this address. In other
     * words, this is not "getaddressbalance". Works only for addresses in the
     * local wallet, external addresses will always show 0.
     *
     * @param address
     * @param minConf
     * @return the total amount received by &lt;bitcoinaddress&gt;
     */
    BigDecimal getReceivedByAddress(String address, int minConf) throws ClientException;
    BigDecimal getReceivedByAddress(String address) throws ClientException;

    Transaction getTransaction(String txid, boolean includeWatchonly) throws ClientException;
    Transaction getTransaction(String txid) throws ClientException;

    BigDecimal getUnconfirmedBalance() throws ClientException;

    WalletInfo getWalletInfo() throws ClientException;

    void importAddress(String address, String label, boolean rescan, boolean p2sh) throws ClientException;
    void importAddress(String address, String label, boolean rescan) throws ClientException;
    void importAddress(String address, String label) throws ClientException;
    void importAddress(String address) throws ClientException;
    void importAddressVar(String address, Object... params) throws ClientException;

    //TODO: importmulti "requests" ( "options" )

    void importPrivKey(String privkey, String label, boolean rescan) throws ClientException;
    void importPrivKey(String privkey, String label) throws ClientException;
    void importPrivKey(String privkey) throws ClientException;
    void importPrivKeyVar(String privKey, Object... params) throws ClientException;

    //TODO: importpubkey "pubkey" ( "label" rescan )

    void importWallet(String filename) throws ClientException;

    void keyPoolRefill() throws ClientException;

    //TODO: listaddressgroupings
    //TODO: listlockunspent

    ReceivedAddress[] listReceivedByAddress(int minConf, boolean includeEmpty) throws ClientException;
    ReceivedAddress[] listReceivedByAddress(int minConf) throws ClientException;
    ReceivedAddress[] listReceivedByAddress() throws ClientException;
    ReceivedAddress[] listReceivedByAddressVar(Object... params) throws ClientException;

    TransactionsSinceBlock listSinceBlock(String blockHash, int targetConfirmations) throws ClientException;
    TransactionsSinceBlock listSinceBlock(String blockHash) throws ClientException;
    TransactionsSinceBlock listSinceBlock() throws ClientException;
    TransactionsSinceBlock listSinceBlockVar(Object... params) throws ClientException;

    Transaction[] listTransactions(@Deprecated String account, int count, int from) throws ClientException;
    Transaction[] listTransactions(@Deprecated String account, int count) throws ClientException;
    @Deprecated Transaction[] listTransactions(String account) throws ClientException;
    Transaction[] listTransactions() throws ClientException;
    Transaction[] listTransactionsVar(Object... params) throws ClientException;

    Unspent[] listUnspent(int minConf, int maxConf, String[] addresses, boolean includeUnsafe, ListUnspentOptions options) throws ClientException;
    Unspent[] listUnspent(int minConf, int maxConf, String[] addresses, boolean includeUnsafe) throws ClientException;
    Unspent[] listUnspent(int minConf, int maxConf, String... addresses) throws ClientException;
    Unspent[] listUnspent(int minConf, int maxConf) throws ClientException;
    Unspent[] listUnspent(int minConf) throws ClientException;
    Unspent[] listUnspent() throws ClientException;
    Unspent[] listUnspentVar(Object... params) throws ClientException;

    //TODO: listwallets
    //TODO: lockunspent unlock ([{"txid":"txid","vout":n},...])

    @Deprecated String move(String from, String to, BigDecimal amount, String comment) throws ClientException;
    @Deprecated String move(String from, String to, BigDecimal amount) throws ClientException;

    //TODO: removeprunedfunds "txid"

    /**
     * Will send the given amount to the given address, ensuring the account has a
     * valid balance using minConf confirmations.
     *
     * @param account
     * @param address
     * @param amount           is a real and is rounded to 8 decimal places
     * @param minConf
     * @param comment
     * @param commentTo
     * @return the transaction ID if successful
     * @throws ClientException
     */
    @Deprecated String sendFrom(String account, String address, BigDecimal amount, int minConf, String comment, String commentTo) throws ClientException;
    @Deprecated String sendFrom(String account, String address, BigDecimal amount, int minConf, String comment) throws ClientException;
    @Deprecated String sendFrom(String account, String address, BigDecimal amount, int minConf) throws ClientException;
    @Deprecated String sendFrom(String account, String address, BigDecimal amount) throws ClientException;
    @Deprecated String sendFromVar(String from, String address, BigDecimal amount, Object... params) throws ClientException;

    String sendMany(@Deprecated String account, Map<String, BigDecimal> outputs, int minConf, String comment, String[] addresses, boolean replaceable, int conf_target, EstimateMode estimateMode) throws ClientException;
    String sendMany(@Deprecated String account, Map<String, BigDecimal> outputs, int minConf, String comment, String[] addresses, boolean replaceable, int conf_target) throws ClientException;
    String sendMany(@Deprecated String account, Map<String, BigDecimal> outputs, int minConf, String comment, String[] addresses, boolean replaceable) throws ClientException;
    String sendMany(@Deprecated String account, Map<String, BigDecimal> outputs, int minConf, String comment, String[] addresses) throws ClientException;
    String sendMany(@Deprecated String account, Map<String, BigDecimal> outputs, int minConf, String comment) throws ClientException;
    String sendMany(@Deprecated String account, Map<String, BigDecimal> outputs) throws ClientException;
    String sendManyVar(@Deprecated String account, Map<String, BigDecimal> outputs, Object... params) throws ClientException;

    /**
     * @param address
     * @param amount    is a real and is rounded to 8 decimal places
     * @param comment
     * @param commentTo
     * @param estimateMode
     * @return the transaction ID &lt;txid&gt; if successful
     * @throws ClientException
     */
    String sendToAddress(String address, BigDecimal amount, String comment, String commentTo, boolean subtractFeeFromAmount, boolean replaceable, int confTarget, EstimateMode estimateMode) throws ClientException;
    String sendToAddress(String address, BigDecimal amount, String comment, String commentTo, boolean subtractFeeFromAmount, boolean replaceable, int confTarget) throws ClientException;
    String sendToAddress(String address, BigDecimal amount, String comment, String commentTo, boolean subtractFeeFromAmount, boolean replaceable) throws ClientException;
    String sendToAddress(String address, BigDecimal amount, String comment, String commentTo, boolean subtractFeeFromAmount) throws ClientException;
    String sendToAddress(String address, BigDecimal amount, String comment, String commentTo) throws ClientException;
    String sendToAddress(String address, BigDecimal amount, String comment) throws ClientException;
    String sendToAddress(String address, BigDecimal amount) throws ClientException;
    String sendToAddressVar(String address, BigDecimal amount, Object... params) throws ClientException;

    boolean setTxFee(BigDecimal amount) throws ClientException;

    String signMessage(String address, String message) throws ClientException;

    void walletLock() throws ClientException;

    void walletPassPhrase(String passphrase, long timeout) throws ClientException;

    //TODO: walletpassphrasechange "oldpassphrase" "newpassphrase"
    //endregion

    //region RegTest
    /**
     * Used in regtest mode to generate an arbitrary number of blocks
     *
     * @param numBlocks a boolean indicating if blocks must be generated with the
     *                  cpu
     * @return the list of hashes of the generated blocks
     * @throws ClientException
     */
    String[] generate(int numBlocks) throws ClientException;

    /**
     * In regtest mode, invalidates a block to create an orphan chain
     *
     * @param hash
     * @throws ClientException
     */
    void invalidateBlock(String hash) throws ClientException;

    /**
     * In regtest mode, undo the invalidation of a block, possibly making it on
     * the top of the chain
     *
     * @param hash
     * @throws ClientException
     */
    void reconsiderBlock(String hash) throws ClientException;
    //endregion
}

