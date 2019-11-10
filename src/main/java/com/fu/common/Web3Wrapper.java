package com.fu.common;

import org.web3j.protocol.Web3j;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Web3Wrapper {

    private List<Web3j> gethClients;

    // postconstruct
    public void init(){
        // TODO
        //
    }

    public Web3j getClient(){
        boolean flag = true;
        Web3j web3j = null;
        int tryTimes = 0;
        Random random = new Random(System.currentTimeMillis());
        while(flag){
            int i = random.nextInt(gethClients.size());
            web3j = gethClients.get(i);
            // web3j check liveness
            try {
                web3j.web3ClientVersion().send().getWeb3ClientVersion();
                flag =false;
            } catch (IOException e) {
                // log
                ++tryTimes;
                if(tryTimes == 10){
                    break;
                }
                try {
                    TimeUnit.MICROSECONDS.sleep(1000);
                } catch (InterruptedException ex) {
                    // exception logic
                }
            }
        }
        if(flag){
            return null;
        }
        return web3j;
    }

}
