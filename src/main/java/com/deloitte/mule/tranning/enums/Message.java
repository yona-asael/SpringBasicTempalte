package com.deloitte.mule.tranning.enums;

public enum Message {
    PRODUCT_DE("PRODUCT:DE"),
    PRODUCT_NT("PRODUCT:NT");
    private final  String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
