package com.beolnix.marvin.history.notes.livestreet.client;

import com.beolnix.marvin.history.messages.domain.model.Message;
import com.beolnix.marvin.history.notes.domain.model.Note;
import com.beolnix.marvin.history.notes.livestreet.dto.AuthDTO;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by beolnix on 21/02/16.
 */

@Service
public class PHPClient {

    // constants
    private Logger log = LoggerFactory.getLogger(PHPClient.class);

    // configuration
    private int postAttemptsLimit;
    private String phphost;
    private String blogId;

    // dependencies
    private CloseableHttpClient httpclient = HttpClients.createDefault();
    private final LifestreetAuthenticator authenticator;

    @Autowired
    public PHPClient(LifestreetAuthenticator authenticator) {
        this.authenticator = authenticator;
    }

    public void sendCreationRequest(Note note) {
        try {
            AuthDTO authDTO = authenticator.setupAuthContext();
            publishNote(authDTO, note);
        } catch (Exception e) {
            log.warn("Failed to post note from first attempt, trying one more time. Error: " + e.getMessage());
            sendCreationRequest(note, 1);
        }
    }

    private void sendCreationRequest(Note note, int attempt) {
        try {
            AuthDTO authDTO = authenticator.setupAuthContext();
            publishNote(authDTO, note);
        } catch (Exception e) {
            log.error("Can't post note because of: " + e.getMessage(), e);
            if (attempt < postAttemptsLimit) {
                authenticator.resetAuth();
                sendCreationRequest(note, ++attempt);
            }
        }
    }

    private void publishNote(AuthDTO authDTO, Note note) throws Exception {
        HttpContext context = authenticator.prepareContext(authDTO);

        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost(phphost)
                .setPath("/topic/add")
                .build();
        HttpPost httppost = new HttpPost(uri);

        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("security_ls_key", authDTO.getSecurityKey()));
        nvps.add(new BasicNameValuePair("topic_tags", "tag"));
        nvps.add(new BasicNameValuePair("blog_id", blogId));
        nvps.add(new BasicNameValuePair("topic_title", note.getTopic()));
        nvps.add(new BasicNameValuePair("topic_text", preparePostBody(note)));
        nvps.add(new BasicNameValuePair("submit_topic_publish", ""));

        httppost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        CloseableHttpResponse response = httpclient.execute(httppost, context);
    }

    private String preparePostBody(Note note) {
        StringBuilder stringBuffer = new StringBuilder()
                .append("Question:")
                .append("\n")
                .append(prepareTextBlock(note.getQuestion()))
                .append("\n")
                .append("Answer:")
                .append("\n")
                .append(prepareTextBlock(note.getAnswer()));
        return stringBuffer.toString();
    }

    private String prepareTextBlock(List<Message> messages) {
        StringBuilder stringBuffer = new StringBuilder();
        messages.forEach(msg -> {
            stringBuffer
                    .append(msg.getAuthor())
                    .append(":")
                    .append(msg.getMsg())
                    .append("\n");
        });
        return stringBuffer.toString();
    }

    @Value("${livestreet.host:localhost}")
    public void setPhphost(String phphost) {
        this.phphost = phphost;
    }

    @Value("${livestreet.postAttemptsLimit:3}")
    public void setPostAttemptsLimit(int postAttemptsLimit) {
        this.postAttemptsLimit = postAttemptsLimit;
    }

    @Value("${livestreet.blogId:2}")
    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }
}
