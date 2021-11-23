package com.ora;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wzy
 * @version 1.0
 * @description: 表示网络传输的一个端点
 * @date 2021/11/17 16:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Peer {
    private String host;
    private int port;
}
