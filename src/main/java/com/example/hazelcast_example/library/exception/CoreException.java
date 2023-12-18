package com.example.hazelcast_example.library.exception;

import com.example.hazelcast_example.library.enums.MessageCodes;
import lombok.Getter;

@Getter
public class CoreException extends RuntimeException{

    private final MessageCodes code;
    private final Object[] args;

    public CoreException(MessageCodes code,Object... args) {
        this.code = code;
        this.args = args;
    }

    public CoreException(MessageCodes messageCodes, String s, MessageCodes code, Object[] args) {
        this.code = code;
        this.args = args;
    }
}
