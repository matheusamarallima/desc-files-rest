package com.files.filesdemo.exception;

public class UploadFilesException extends RuntimeException{

    public UploadFilesException(String message){
        super(message);
    }
    public UploadFilesException(String message, Throwable trouble){
        super(message,trouble);
    }
}
