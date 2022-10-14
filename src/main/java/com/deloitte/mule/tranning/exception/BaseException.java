package com.deloitte.mule.tranning.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException  extends  RuntimeException {
    public String message;
    public String module;
    public String code;
    public String exception;

    BaseException (String message, String module, String code, String exception ) {
        super(String.format("Error: #s Module: #s Code: %s", message, module, code), new Throwable(exception));
    }
}
