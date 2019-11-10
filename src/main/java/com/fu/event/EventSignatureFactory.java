package com.fu.event;

import com.fu.factory.FunctionFactory;

public class EventSignatureFactory {

    public String getEventSignature(String eventName){
        switch (eventName){
            case FunctionFactory.FUNC_TRANSFER:
                return BaseEvent.encodeEvent(BaseEvent.TRANSFER_EVENT);
            default:
                return null;
        }
    }
}
