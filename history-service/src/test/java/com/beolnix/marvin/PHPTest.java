package com.beolnix.marvin;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StreamUtils;

import javax.net.ssl.*;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * Created by beolnix on 21/02/16.
 */
public class PHPTest {

    private HttpClient httpclient = HttpClients.createDefault();

    @Test
    public void getSecurityId() throws Exception {
        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("asdcode-dev.buildloft.com")
                .setPath("/")
                .build();
        HttpGet httpget = new HttpGet(uri);
        HttpResponse response = httpclient.execute(httpget);
        String plainResponse = StreamUtils.copyToString(response.getEntity().getContent(), Charset.defaultCharset());



        System.out.println(plainResponse);
    }


}
