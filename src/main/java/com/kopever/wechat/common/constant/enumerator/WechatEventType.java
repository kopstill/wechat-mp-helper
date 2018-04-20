package com.kopever.wechat.common.constant.enumerator;

import lombok.Getter;

public enum WechatEventType {

    SUBSCRIBE   ("subscribe"),
    UNSUBSCRIBE ("unsubscribe"),
    SCAN        ("SCAN"),
    LOCATION    ("LOCATION"),
    CLICK       ("CLICK"),
    VIEW        ("VIEW");

    @Getter
    private String type;

    WechatEventType(String type) {
        this.type = type;
    }

}
