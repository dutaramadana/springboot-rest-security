package com.program.restsecurity.rest.response;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

public class Response<T>{

    private int statusCode;
    private HttpStatus httpStatus;
    private T payload;
    private String message;
    private Metadata metadata;

    public Response(int statusCode, HttpStatus httpStatus, T payload, String message, Metadata metadata) {
        this.statusCode = statusCode;
        this.httpStatus = httpStatus;
        this.payload = payload;
        this.message = message;
        this.metadata = metadata;
    }

    public Response() {
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
