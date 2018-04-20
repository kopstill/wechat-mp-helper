package com.kopever.wechat.domain.vo.message;

import com.kopever.wechat.domain.vo.WechatMessageVO;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Lullaby on 2018/1/17
 */
@Getter
@Setter
@XStreamAlias("xml")
public class WechatTextMessageResponseVO extends WechatMessageVO implements Serializable {

    private String Content;

}
