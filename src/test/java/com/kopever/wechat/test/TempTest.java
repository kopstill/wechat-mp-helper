package com.kopever.wechat.test;

import com.google.common.collect.Maps;
import com.kopever.wechat.common.constant.enumerator.WechatMsgType;
import com.kopever.wechat.common.util.Jackson;
import com.kopever.wechat.common.util.RandomUtil;
import com.kopever.wechat.test.domain.Foo;
import org.junit.Test;

import java.util.Map;
import java.util.UUID;

/**
 * Created by Lullaby on 2018/1/17
 */
public class TempTest {

    @Test
    public void testEnum() {
        switch (WechatMsgType.valueOf("event".toUpperCase())) {
            case TEXT:
                break;
            case IMAGE:
                break;
            case VOICE:
                break;
            case EVENT:
                System.out.println("event");
                break;
            default:
                break;
        }
    }

    @Test
    public void testDefaultBool() {
        Foo foo = new Foo();
        System.out.println(foo.isSandbox());
        System.out.println(Boolean.valueOf("TruE"));
        foo.setFirstName("111");
        foo.setSandbox(true);
        System.out.println(Jackson.toJson(foo));

        Map<String, Object> map = Maps.newHashMap();
        map.put("firstName", "helloworld");
        map.put("isSandbox", true);
        String json = Jackson.toJson(map);

        Foo foo1 = Jackson.fromJson(json, Foo.class);
        System.out.println(foo1.isSandbox());
    }

    @Test
    public void testUUID() {
        for (int i = 0; i < 10; i++) {
            System.out.println(UUID.randomUUID().toString().replace("-", ""));
        }
    }

    @Test
    public void testRandom() {
        for (int i = 0; i < 10; i++) {
            System.out.println(RandomUtil.getRandomAllChar(43));
        }

        System.out.println("Uno9IhHu3iwuishDCIMu8o4DVrKqVrFAToASYpKBQkY".length());
    }

}
