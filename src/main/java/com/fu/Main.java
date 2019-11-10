package com.fu;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class Main {
    public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
        ECKeyPair ecKeyPair = Keys.createEcKeyPair();
        ECKeyPair ecKeyPair1 = ECKeyPair.create(ecKeyPair.getPrivateKey());
        Credentials credentials = Credentials.create(ecKeyPair1);
        System.out.println(credentials.getAddress());
    }
}
