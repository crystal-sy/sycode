package com.sltg.common.exception.file;

import com.sltg.common.exception.BaseException;

/**
 * 文件信息异常类
 * 
 * @author styra~
 */
public class FileException extends BaseException {
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args) {
        super("file", code, args, null);
    }

}
