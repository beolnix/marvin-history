package com.beolnix.marvin.history.security;

import com.beolnix.marvin.history.api.Constants;
import com.beolnix.marvin.history.configuration.AccessKey;
import com.beolnix.marvin.history.configuration.SecurityConfig;
import com.beolnix.marvin.history.error.Unauthorized;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;
import java.util.*;

/**
 * Created by beolnix on 15/02/16.
 */
@Component
public class ClientAuthHandler extends HandlerInterceptorAdapter {

    @Autowired
    private SecurityConfig securityConfig;

    private Map<String, AccessKey> keyMap = new HashMap<>();
    private Set<String> readAllowedMethods = Sets.newHashSet(HttpMethod.GET);
    private Set<String> writeAllowedMethods = Sets.newHashSet(HttpMethod.GET, HttpMethod.POST);

    @PostConstruct
    private void init() {
        securityConfig.getAccessKeys().forEach(key ->
                keyMap.put(key.getKey(), key)
        );
    }



    //before the actual handler will be executed
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws Exception {

        String receivedApiKey = request.getHeader(Constants.API_KEY_HEADER);
        String receivedApiAuth = request.getHeader(Constants.API_AUTH_HEADER);

        if (keyMap.containsKey(receivedApiKey)) {
            AccessKey accessKey = keyMap.get(receivedApiKey);
            if (accessKey.getAuth().equals(receivedApiAuth) && isAllowed(request, accessKey)) {
                // all checks are passed successfully
                return true;
            }
        }

        throw new Unauthorized();
    }

    private boolean isAllowed(HttpServletRequest request, AccessKey accessKey) {
        if (accessKey.getRoles().contains("read")) {
            return readAllowedMethods.contains(request.getMethod());
        } else if (accessKey.getRoles().contains("write")) {
            return writeAllowedMethods.contains(request.getMethod());
        }

        return false;
    }
}
