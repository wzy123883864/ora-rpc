package com.ora.transport;

import com.ora.Peer;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author 王志远
 */
public class HttpTransportClient implements TransportClient{
    private String url;
    @Override
    public void connect(Peer peer) {
        this.url = "http://" + peer.getHost() + ":" + peer.getPort();
    }

    @Override
    public InputStream write(InputStream data) {
        try {
            HttpURLConnection httpConn= (HttpURLConnection)new URL(url).openConnection();
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            httpConn.setUseCaches(false);
            httpConn.setRequestMethod("POST");

            httpConn.connect();
            IOUtils.copy(data,httpConn.getOutputStream());
            int responseCode = httpConn.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                return httpConn.getInputStream();
            }else{
                return httpConn.getErrorStream();
            }
        } catch (IOException e) {
           throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() {

    }
}
