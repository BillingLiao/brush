package com.shokey.brushcommon.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 这是一个很讨厌的异常
 */
public class MyUsernameNotFoundException extends AuthenticationException {
    public MyUsernameNotFoundException(String msg) {
        super(msg);
    }

}
