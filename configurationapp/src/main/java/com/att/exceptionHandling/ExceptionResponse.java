package com.att.exceptionHandling;

/**
 * Created by Achille on 10/5/2018.
 */
public class ExceptionResponse extends Exception {


    private static final long serialVersionUID = 1L;
    private int code;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}
