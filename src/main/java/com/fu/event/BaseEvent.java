package com.fu.event;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;

import java.util.Arrays;

public class BaseEvent {

    // Example
    public static final Event TRANSFER_EVENT = new Event(
            "Transfer",
            Arrays.<TypeReference<?>>asList(
                    new TypeReference<Address>(true) {},
                    new TypeReference<Address>(true) {},
                    new TypeReference<Uint256>() {}
            )
    );

    public static String encodeEvent(Event event){
        return EventEncoder.encode(event);
    }
}
