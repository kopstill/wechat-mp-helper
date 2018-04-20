package com.kopever.wechat.wrapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by Lullaby on 2017/2/13.
 */
@Configuration
public class CamelToUnderscoreWrapper extends WebMvcConfigurerAdapter {

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(snakeToCamelModelAttributeMethodProcessor());
    }

    protected @Bean SnakeToCamelModelAttributeMethodProcessor snakeToCamelModelAttributeMethodProcessor() {
        return new SnakeToCamelModelAttributeMethodProcessor(true);
    }

}
