package com.kopever.wechat.domain.vo.auth;

import com.kopever.wechat.domain.vo.WechatUrlRequestVO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@Getter
@Setter
public class WechatVerificationVO extends WechatUrlRequestVO implements Serializable {

    @NotBlank
    private String echostr;

}
