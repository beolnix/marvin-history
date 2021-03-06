package com.beolnix.marvin.history.api;

/**
 * Created by beolnix on 24/01/16.
 */
public class Constants {
    public static final String FEIGN_SERVICE = "history";
    public static final String FEIGN_CLIENT_NAME = "https://" + FEIGN_SERVICE;
    public static final String HISTORY_URL_ROOT = "/api/v1";

    public static final String API_KEY_HEADER = "X-WA-KEY";
    public static final String API_AUTH_HEADER = "X-WA-AUTH";
    public static final String API_DATE_HEADER = "X-WA-DATE";
}
