package com.beolnix.marvin.history.notes.livestreet.util;

import com.beolnix.marvin.history.notes.livestreet.dto.AuthDTO;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by beolnix on 21/02/16.
 */

@Component
public class AuthExtractor {

    public AuthDTO extract(String content) {
        AuthDTO authDTO = new AuthDTO();
        authDTO.setSecurityKey(extractSecurityKey(content));
        authDTO.setSessionId(extractSessionKey(content));
        return authDTO;
    }

    private String extractSessionKey(String content) {
        return extractCustom("SESSION_ID", content);
    }

    private String extractSecurityKey(String content) {
        return extractCustom("LIVESTREET_SECURITY_KEY", content);
    }

    private String extractCustom(String propName, String content) {
        Pattern pattern = Pattern.compile(propName + ".*=.*'.*';");
        Matcher matcher = pattern.matcher(content);
        matcher.find();
        String token = matcher.group();
        return token.split("=")[1].replace("'","").replace(";", "").trim();
    }
}
