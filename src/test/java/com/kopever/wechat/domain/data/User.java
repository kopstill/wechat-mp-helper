package com.kopever.wechat.domain.data;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Created by Lullaby on 2018/2/7
 */
@Getter
@Setter
public class User {

    private String id;

    private String username;

    private String email;

    private Byte age;

    private Character gender;

    private List<String> addresses;

    private Map<String, Object> children;

    @Getter
    @Setter
    public static class Child {

        private String name;

        private Character gender;

        private Byte age;

    }

}
