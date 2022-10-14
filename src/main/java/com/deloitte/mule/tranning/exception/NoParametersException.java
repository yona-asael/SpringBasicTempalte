package com.deloitte.mule.tranning.exception;

public class NoParametersException  extends  BaseException {

    public NoParametersException() {
        super("Missing Parameters", "Product CT", "ERROR:PR", "The parameters is not provided");
    }

}
