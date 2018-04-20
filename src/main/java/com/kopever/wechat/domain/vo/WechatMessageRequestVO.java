package com.kopever.wechat.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Lullaby on 2018/1/17
 */
@Getter
@Setter
public class WechatMessageRequestVO extends WechatMessageVO implements Serializable {

    private String MsgId;

}
