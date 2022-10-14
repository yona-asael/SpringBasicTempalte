package com.deloitte.mule.tranning.exception;

public class NotFoundException extends  BaseException {

    public NotFoundException(String module, String code) {
        super("Element not found", module, code, "The element not exists");
    }

}
