package com.kopever.wechat.common.constant.enumerator;

import lombok.Getter;

/**
 * Created by Lullaby on 2018/1/17
 */
public enum WechatMsgType {

    TEXT       ("text"),
    IMAGE      ("image"),
    VOICE      ("voice"),
    VIDEO      ("video"),
    SHORTVIDEO ("shortvideo"),
    LOCATION   ("location"),
    LINK       ("link"),

    EVENT("event");

    @Getter
    private String type;

    WechatMsgType(String type) {
        this.type = type;
    }

}
