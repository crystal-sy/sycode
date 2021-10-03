package com.sltg.common.exception.user;

import com.sltg.common.exception.BaseException;

/**
 * 用户信息异常类
 * 
 * @author styra~
 */
public class UserException extends BaseException {
    private static final long serialVersionUID = -4386739915630204930L;

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
