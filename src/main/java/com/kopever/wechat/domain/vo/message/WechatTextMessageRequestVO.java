package com.kopever.wechat.domain.vo.message;

import com.kopever.wechat.domain.vo.WechatMessageRequestVO;
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
public class WechatTextMessageRequestVO extends WechatMessageRequestVO implements Serializable {

    private String Content;

}
