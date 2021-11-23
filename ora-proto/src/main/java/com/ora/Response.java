package com.ora;

import lombok.Data;

/**
 * @author wzy
 * @version 1.0
 * @description: TODO
 * @date 2021/11/17 17:06
 */
@Data
public class Response {
    private int code;
    private String message;
    private Object data;
}
