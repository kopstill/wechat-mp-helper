package com.kopever.wechat.test.domain;

import lombok.Setter;

/**
 * Created by Lullaby on 2018/1/17
 */
public class Person {

    public Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    private String firstname;

    private String lastname;

    @Setter
    private PhoneNumber phone;

    @Setter
    private PhoneNumber fax;

}
