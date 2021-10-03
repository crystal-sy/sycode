package com.sltg.common.exception.file;

/**
 * 文件名称超长限制异常类
 * 
 * @author styra~
 */
public class FileNameLengthLimitExceededException extends FileException {
    private static final long serialVersionUID = 2720990027454222303L;

    public FileNameLengthLimitExceededException(int defaultFileNameLength) {
        super("upload.filename.exceed.length", new Object[] { defaultFileNameLength });
    }
}
