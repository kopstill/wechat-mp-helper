package com.kopever.wechat.web.view;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Lullaby on 2018/1/22
 */
@Getter
@Setter
public class JsonResponse<T> implements Serializable {

    private int code;

    private String message;

    private T result;

}
