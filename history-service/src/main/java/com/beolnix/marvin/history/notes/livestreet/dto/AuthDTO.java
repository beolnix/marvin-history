package com.beolnix.marvin.history.notes.livestreet.dto;

/**
 * Created by beolnix on 21/02/16.
 */
public class AuthDTO {
    private String securityKey;
    private String sessionId;

    public String getSecurityKey() {
        return securityKey;
    }

    public void setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
