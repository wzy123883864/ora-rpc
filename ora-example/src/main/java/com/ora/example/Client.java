package com.ora.example;

import com.ora.client.RpcClient;
import com.ora.client.RpcClientConfig;

/**
 * @author wzy
 * @version 1.0
 * @description: TODO
 * @date 2021/11/18 14:26
 */
public class Client {
    public static void main(String[] args) {
        RpcClientConfig config = new RpcClientConfig();

        RpcClient client = new RpcClient(config);
        CalaService service = client.getProxy(CalaService.class);

        int add = service.add(1, 2);
        int minus = service.minus(10, 2);
        System.out.println(add);
        System.out.println(minus);
    }
}
