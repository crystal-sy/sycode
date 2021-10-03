package com.sltg.common.exception.user;

/**
 * 验证码失效异常类
 * 
 * @author styra~
 */
public class CaptchaExpireException extends UserException {
    private static final long serialVersionUID = 6749371829814668176L;

    public CaptchaExpireException() {
        super("user.jcaptcha.expire", null);
    }
}
