package com.kopever.wechat.domain.vo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by Lullaby on 2018/1/17
 */
@Getter
@Setter
public class WechatVO implements Serializable {

    @NotBlank
    private String ToUserName;

    @NotBlank
    private String FromUserName;

    @NotBlank
    private Long CreateTime;

    @NotBlank
    private String MsgType;

}
