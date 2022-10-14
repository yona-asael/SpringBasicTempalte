package com.deloitte.mule.tranning.enums;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    NORMAL(0), NOSTOCK(1),DEPRECATED(2),DELETED(3), NOSTATUS(999);
    private final  Integer status;

    Status (Integer status) {
        this.status= status;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Status forValue(Integer status) {
        for( Status r : Status .values() ){
            if ( r.getStatus().equals(status) )
                return r;
        }
        return null;
    }

    @JsonValue
    public Integer getStatus() {
        return this.status;
    }
}
