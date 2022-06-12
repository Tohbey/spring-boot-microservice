package com.example.userservice.dto;

import lombok.Data;

@Data
public class ResponseObject {
    protected Boolean valid;
    protected String message;
    protected Object data;
}
