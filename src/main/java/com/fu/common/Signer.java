package com.fu.common;

import com.fu.account.EthereumAccount;
import org.web3j.crypto.*;
import org.web3j.utils.Numeric;

/**
 * @author fu hang
 */
public class Signer {

    public static String signTransaction(EthereumAccount ethereumAccount, RawTransaction rawTransaction){
        Credentials credentials = Credentials.create(ECKeyPair.create(ethereumAccount.getPrivateKey()));
        String hexValue = Numeric.toHexString(TransactionEncoder.signMessage(rawTransaction,credentials));
        return hexValue;
    }

    public static String calculateTransactionHash(String signResultHexValue){
        return Hash.sha3(signResultHexValue);
    }
}
