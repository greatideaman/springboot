package com.att.exceptions;

/**
 * <h1>com.att.web.exceptions.ConfigNotFoundException</h1>
 * Description :
 *
 * @author gcanter
 * on 2019-06-24
 */


public class ConfigNotFoundException extends RuntimeException {
    public ConfigNotFoundException(){
        super();
    }

    public ConfigNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ConfigNotFoundException(final String message) {
        super(message);
    }

    public ConfigNotFoundException(final Throwable cause) {
        super(cause);
    }
}
