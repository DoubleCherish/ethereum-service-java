package com.fu.account;

import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class EthereumAccount {

    private BigInteger privateKey;

    private BigInteger publicKey;

    private String ethAddress;

    public EthereumAccount() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
        ECKeyPair ecKeyPair = Keys.createEcKeyPair();
        this.privateKey = ecKeyPair.getPrivateKey();
        this.publicKey = ecKeyPair.getPublicKey();
        this.ethAddress = Numeric.prependHexPrefix(Keys.getAddress(ecKeyPair));
    }

    public BigInteger getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(BigInteger privateKey) {
        this.privateKey = privateKey;
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(BigInteger publicKey) {
        this.publicKey = publicKey;
    }

    public String getEthAddress() {
        return ethAddress;
    }

    public void setEthAddress(String ethAddress) {
        this.ethAddress = ethAddress;
    }

    @Override
    public String toString() {
        return "EthereumAccount{" +
                "privateKey='" + privateKey + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", ethAddress='" + ethAddress + '\'' +
                '}';
    }
}
