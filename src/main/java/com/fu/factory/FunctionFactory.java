package com.fu.factory;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author fu hang
 */

public class FunctionFactory {

    public static final String FUNC_TRANSFER = "transfer";

    /**
     * ERC20 token transfer function
     * @param toAddress
     * @param value
     * @return
     */
    public static Function newERC20TokenTransferFunction(String toAddress, BigInteger value){
        Function function = new Function(
                FUNC_TRANSFER, // method selector
                Arrays.<Type>asList(new Address(toAddress),new Uint256(value)), // args
                Collections.<TypeReference<?>>emptyList() // callback function
        );
        return function;
    }

    public Function autoConstructFunction(String functionName,String arg){
        String [] args = arg.split(",");
        // TODO check args
        switch (functionName){
            case FUNC_TRANSFER:
                return newERC20TokenTransferFunction(args[0],new BigInteger(args[1]));
            default:
                return null;
        }
    }

    public static String constructFunctionData(Function function){
        return FunctionEncoder.encode(function);
    }
}
