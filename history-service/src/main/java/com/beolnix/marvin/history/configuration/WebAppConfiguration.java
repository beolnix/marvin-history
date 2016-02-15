package com.beolnix.marvin.history.configuration;

import com.beolnix.marvin.history.security.ClientAuthHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by beolnix on 15/02/16.
 */
@Configuration
public class WebAppConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private ClientAuthHandler clientAuthHandler;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(clientAuthHandler);
    }
}
