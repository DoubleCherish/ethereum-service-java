package com.fu.factory;

import org.web3j.crypto.RawTransaction;

import java.math.BigInteger;

public class RawTransactionFactory {

    public static RawTransaction newContractTransaction(BigInteger nonce,
                                                   BigInteger gasPrice,
                                                   BigInteger gasLimit,
                                                   String contractAddress,
                                                   BigInteger etherValue,
                                                   String functionData
    ){
        return RawTransaction.createTransaction(nonce,
                                                gasPrice,
                                                gasLimit,
                                                contractAddress,
                                                etherValue,
                                                functionData
                );
    }
}
