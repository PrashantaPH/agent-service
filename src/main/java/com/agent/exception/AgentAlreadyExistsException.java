package com.agent.exception;

import lombok.Getter;

@Getter
public class AgentAlreadyExistsException extends RuntimeException {

    private final String errorCode;

    public AgentAlreadyExistsException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public AgentAlreadyExistsException(String message, String errorCode, Throwable throwable) {
        super(message, throwable);
        this.errorCode = errorCode;
    }
}
