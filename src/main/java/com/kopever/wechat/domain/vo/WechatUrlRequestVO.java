package com.kopever.wechat.domain.vo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by Lullaby on 2018/1/15
 */
@Getter
@Setter
public class WechatUrlRequestVO implements Serializable {

    @NotBlank
    private String timestamp;

    @NotBlank
    private String nonce;

    @NotBlank
    private String signature;

}
