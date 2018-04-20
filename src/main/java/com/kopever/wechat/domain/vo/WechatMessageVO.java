package com.kopever.wechat.domain.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by Lullaby on 2018/2/9
 */
@Getter
@Setter
@Document(collection = "wechat_message")
public class WechatMessageVO extends WechatVO implements Serializable {


}
