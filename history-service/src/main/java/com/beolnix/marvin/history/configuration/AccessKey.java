package com.beolnix.marvin.history.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by beolnix on 21/02/16.
 */
public class AccessKey {

    private String key;
    private String auth;
    private List<String> roles;

    public AccessKey(String key, String auth, List<String> roles) {
        this.key = key;
        this.auth = auth;
        this.roles = roles;
    }

    public AccessKey() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
