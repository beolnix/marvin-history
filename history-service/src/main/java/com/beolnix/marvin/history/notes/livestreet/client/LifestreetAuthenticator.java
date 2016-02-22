package com.beolnix.marvin.history.notes.livestreet.client;

import com.beolnix.marvin.history.notes.livestreet.dto.AuthDTO;
import com.beolnix.marvin.history.notes.livestreet.util.AuthExtractor;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.annotation.concurrent.ThreadSafe;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by beolnix on 22/02/16.
 */
@ThreadSafe
@Component
public class LifestreetAuthenticator {

    private CloseableHttpClient httpclient = HttpClients.createDefault();
    private volatile AuthDTO authDTO = null;
    private ReentrantLock lock = new ReentrantLock();

    private String phphost;
    private String login;
    private String password;
    private final AuthExtractor authExtractor;

    public LifestreetAuthenticator(AuthExtractor authExtractor) {
        this.authExtractor = authExtractor;
    }

    public void resetAuth() {
        authDTO = null;
    }

    public AuthDTO setupAuthContext() throws Exception {
        if (authDTO == null) {
            lock.lock();
            try {
                if (authDTO == null) {
                    authDTO = getAuthDTO();
                    login(authDTO, prepareContext(authDTO));
                    return authDTO;
                } else {
                    return authDTO;
                }
            } finally {
                lock.unlock();
            }
        } else {
            return authDTO;
        }
    }

    private void validateParams() {
        if (StringUtils.isBlank(phphost)) {
            throw new IllegalArgumentException("host must be specified");
        }

        if (StringUtils.isBlank(login)) {
            throw new IllegalArgumentException("login must be configured");
        }

        if (StringUtils.isBlank(password)) {
            throw new IllegalArgumentException("password must be configured");
        }
    }

    public HttpContext prepareContext(@NotNull AuthDTO authDTO) {
        if (authDTO == null) {
            throw new IllegalArgumentException("authDTO must not be null");
        }
        CookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie("PHPSESSID", authDTO.getSessionId());
        cookie.setDomain(phphost);
        cookie.setPath("/");

        cookieStore.addCookie(cookie);

        HttpContext localContext = new BasicHttpContext();
        localContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);

        return localContext;
    }

    private void login(AuthDTO authDTO, HttpContext httpContext) throws Exception {
        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost(phphost)
                .setPath("/login/ajax-login")
                .build();
        HttpPost httppost = new HttpPost(uri);

        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("security_ls_key", authDTO.getSecurityKey()));
        nvps.add(new BasicNameValuePair("login", this.login));
        nvps.add(new BasicNameValuePair("password", this.password));

        httppost.setEntity(new UrlEncodedFormEntity(nvps));

        CloseableHttpResponse response = httpclient.execute(httppost, httpContext);
    }

    private AuthDTO getAuthDTO() throws Exception {
        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost(phphost)
                .setPath("/")
                .build();
        HttpGet httpget = new HttpGet(uri);
        CloseableHttpResponse response = httpclient.execute(httpget);
        String plainResponse = StreamUtils.copyToString(response.getEntity().getContent(), Charset.defaultCharset());
        return authExtractor.extract(plainResponse);
    }

    @Value("${livestreet.host:localhost}")
    public void setPhphost(String phphost) {
        this.phphost = phphost;
    }

    @Value("${livestreet.login:test}")
    public void setLogin(String login) {
        this.login = login;
    }

    @Value("${livestreet.password:test}")
    public void setPassword(String password) {
        this.password = password;
    }
}
