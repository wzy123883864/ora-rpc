package com.ora.example;

import com.ora.server.RpcServer;
import com.ora.server.RpcServerConfig;
import com.ora.transport.HttpTomcatTransportServer;

/**
 * @author wzy
 * @version 1.0
 * @description: TODO
 * @date 2021/11/18 14:26
 */
public class Server {
    public static void main(String[] args) {
        // 默认配置
        RpcServerConfig config = new RpcServerConfig();
        config.setTransportClass(HttpTomcatTransportServer.class);
        RpcServer server = new RpcServer(config);
        server.register(CalaService.class,new CalaServiceImpl());
        server.start();
    }
}
