package com.ora.server;

import com.ora.codec.Decoder;
import com.ora.codec.Encoder;
import com.ora.codec.JSONDecoder;
import com.ora.codec.JSONEncoder;
import com.ora.transport.HttpJettyTransportServer;
import com.ora.transport.TransportServer;
import lombok.Data;

/**
 * @author wzy
 * @version 1.0
 * @description: server配置
 * @date 2021/11/18 10:11
 */
@Data
public class RpcServerConfig {
    private Class<? extends TransportServer> transportClass = HttpJettyTransportServer.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> dncoderClass = JSONDecoder.class;
    private int port = 3000;
}
