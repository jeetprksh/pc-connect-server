package com.jeetprksh.pcconnectserver.entity.http;

public class Request {

    private Object data;

    public Request(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
