package com.githubshowcase.taskflow_api.common;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String msg) { super(msg); }
}