package com.kopever.wechat.domain.vo.auth;

import com.kopever.wechat.domain.vo.WechatUrlRequestVO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by Lullaby on 2018/1/15
 */
@Getter
@Setter
public class WechatAuthenticationVO extends WechatUrlRequestVO implements Serializable {

    @NotBlank
    private String encryptType;

    @NotBlank
    private String msgSignature;

    private String openid;

}
