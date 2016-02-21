package com.beolnix.marvin.livestreet;

import com.beolnix.marvin.history.notes.livestreet.dto.AuthDTO;
import com.beolnix.marvin.history.notes.livestreet.util.AuthExtractor;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.FileCopyUtils;

import static org.junit.Assert.*;


import java.io.InputStreamReader;

/**
 * Created by beolnix on 21/02/16.
 */
public class AuthExtractorTestCase {

    private String content;

    @Before
    public void readTestData() throws Exception {
        content = FileCopyUtils.copyToString(new InputStreamReader((getClass().getResourceAsStream("/index_content_example.html"))));
    }

    @Test
    public void test() {
        AuthExtractor authExtractor = new AuthExtractor();
        AuthDTO authDTO = authExtractor.extract(content);

        assertNotNull(authDTO);
        assertNotNull(authDTO.getSecurityKey());
        assertNotNull(authDTO.getSessionId());

        assertEquals("91e6a0731fff93101beec668a8dbf9ab", authDTO.getSecurityKey());
        assertEquals("0giqbt4t9ojom8cjsencabs6n0", authDTO.getSessionId());
    }
}
