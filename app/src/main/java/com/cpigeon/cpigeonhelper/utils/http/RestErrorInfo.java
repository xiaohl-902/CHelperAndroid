package com.cpigeon.cpigeonhelper.utils.http;


import com.cpigeon.cpigeonhelper.common.network.ApiResponse;

public class RestErrorInfo {
    public int code;
    public String message;
    public RestErrorInfo(ApiResponse responseJson){
        this.code=responseJson.errorCode;
        this.message=responseJson.msg;
    }
    public RestErrorInfo(int code, String message){
        this.code=code;
        this.message=message;
    }
    public RestErrorInfo(String message)
    {
        this.code=-1;
        this.message=message;
    }
}
