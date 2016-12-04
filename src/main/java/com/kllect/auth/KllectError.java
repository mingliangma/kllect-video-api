package com.kllect.auth;

/**
 * Created by mingliangma on 2016-12-03.
 */
public class KllectError {
    public String message;
    public int httpStatus;
    public String errorCode;

    public KllectError(String message, String errorCode, Integer httpStatus){
        this.message = message;
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
}
