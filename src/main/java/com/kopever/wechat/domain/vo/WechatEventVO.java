package com.kopever.wechat.domain.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by Lullaby on 2018/1/17
 */
@Getter
@Setter
@Document(collection = "wechat_event")
public class WechatEventVO extends WechatVO implements Serializable {

    private String Event;

}
