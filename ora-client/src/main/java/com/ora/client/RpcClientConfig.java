package com.ora.client;

import com.ora.Peer;
import com.ora.codec.Decoder;
import com.ora.codec.Encoder;
import com.ora.codec.JSONDecoder;
import com.ora.codec.JSONEncoder;
import com.ora.transport.HttpTransportClient;
import com.ora.transport.TransportClient;
import lombok.Data;


import java.util.Arrays;
import java.util.List;

/**
 * @author wzy
 * @version 1.0
 * @description: TODO
 * @date 2021/11/18 13:52
 */
@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> tranPortClass = HttpTransportClient.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> DecoderClass = JSONDecoder.class;
    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;
    private int connectCount = 1;
    private List<Peer> servers = Arrays.asList(new Peer("127.0.0.1",3000));

}
