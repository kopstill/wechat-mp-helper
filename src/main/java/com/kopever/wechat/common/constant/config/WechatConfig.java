package com.kopever.wechat.common.constant.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Lullaby on 2018/1/9
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "wechat")
@PropertySource("classpath:business.yml")
public class WechatConfig {

    @Value("${app_id}")
    private String appId;

    @Value("${app_secret}")
    private String appSecret;

    @Value("${token}")
    private String token;

    @Value("${encoding_aes_key}")
    private String encodingAesKey;

    @Value("${mp_id}")
    private String mpId;

    @Value("${mp_original_id}")
    private String mpOriginalId;

}
