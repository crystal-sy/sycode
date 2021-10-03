package com.sltg.common.exception.user;

/**
 * 验证码错误异常类
 * 
 * @author styra~
 */
public class CaptchaException extends UserException {
    private static final long serialVersionUID = -5795975348510008775L;

    public CaptchaException() {
        super("user.jcaptcha.error", null);
    }
}
