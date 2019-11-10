package com.fu.validate;

import com.fu.common.Web3Wrapper;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

public class Validator {

    Web3Wrapper web3Wrapper;

    /**
     * 以太坊公链交易认证逻辑
     * @param contractAddress
     * @param blockHash
     * @param txHash
     * @param logIndex
     * @return
     */
    public boolean validateTx(String contractAddress, String blockHash, String txHash, BigInteger logIndex) throws IOException {
        Web3j web3j = web3Wrapper.getClient();
        EthTransaction ethTransaction = web3j.ethGetTransactionByHash(txHash).send();
        if(!ethTransaction.getTransaction().isPresent()){
            //交易查询不到
            return false;
        }else{
            if(!blockHash.equals(ethTransaction.getTransaction().get().getBlockHash())){
                // 交易所在区块与原先存证的不符合，交易可能重新被打包
                return false;
            }else {
                EthGetTransactionReceipt ethGetTransactionReceipt = web3j.ethGetTransactionReceipt(txHash).send();
                if(!ethGetTransactionReceipt.getTransactionReceipt().isPresent()){
                    // 交易收据不存在，可能交易进入pending队列还未被打包进区块
                    return false;
                }
                TransactionReceipt transactionReceipt = ethGetTransactionReceipt.getTransactionReceipt().get();
                List<Log> logs = transactionReceipt.getLogs();
                boolean flag = false;
                //开始验证交易事件发生的index否在原位置，若不在说明交易重组了。因为blockHash txHash logIndex能唯一定位一笔合约交易
                if(logIndex != null){
                    for(Log log : logs){
                        // 判断合约地址是否为正确地址及事件发生索引是否为当初记录的
                        if(contractAddress.equals(log.getAddress()) && logIndex.compareTo(log.getLogIndex())==0){
                            flag = true;
                        }
                    }
                }else{
                    flag = true;
                }
                // 基础验证完毕，可以确认一笔交易发生，不能回撤
                if(transactionReceipt.isStatusOK() && flag){
                    return true;
                }else{
                    return false;
                }
            }
        }


    }
}
