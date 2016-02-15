package com.beolnix.marvin.history.security;

import com.beolnix.marvin.history.api.Constants;
import com.beolnix.marvin.history.error.Unauthorized;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by beolnix on 15/02/16.
 */
@Component
public class ClientAuthHandler extends HandlerInterceptorAdapter {

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.auth}")
    private String apiAuth;

    //before the actual handler will be executed
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws Exception {

        String receivedApiKey = request.getHeader(Constants.API_KEY_HEADER);
        String receivedApiAuth = request.getHeader(Constants.API_AUTH_HEADER);

        if (apiKey.equals(receivedApiKey) && apiAuth.equals(receivedApiAuth)) {
            return true;
        } else {
            throw new Unauthorized();
        }
    }
}
