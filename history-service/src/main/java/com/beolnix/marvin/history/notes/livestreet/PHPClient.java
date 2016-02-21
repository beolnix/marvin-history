package com.beolnix.marvin.history.notes.livestreet;

import com.beolnix.marvin.history.notes.domain.model.Note;
import com.beolnix.marvin.history.notes.livestreet.dto.AuthDTO;
import com.beolnix.marvin.history.notes.livestreet.util.AuthExtractor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.net.URI;
import java.nio.charset.Charset;

/**
 * Created by beolnix on 21/02/16.
 */

@Service
public class PHPClient {

    @Value("${phphost:localhost}")
    private String phphost;

    @Autowired
    private AuthExtractor authExtractor;

    private CloseableHttpClient httpclient = HttpClients.createDefault();


    public void sendCreationRequest(Note note) throws Exception {
        AuthDTO authDTO = getAuthDTO();
    }

    private AuthDTO getAuthDTO() throws Exception {
        URI uri = new URIBuilder()
                .setScheme("https")
                .setHost(phphost)
                .setPath("/")
                .build();
        HttpGet httpget = new HttpGet(uri);
        CloseableHttpResponse response = httpclient.execute(httpget);
        String plainResponse = StreamUtils.copyToString(response.getEntity().getContent(), Charset.defaultCharset());
        return authExtractor.extract(plainResponse);
    }
}
