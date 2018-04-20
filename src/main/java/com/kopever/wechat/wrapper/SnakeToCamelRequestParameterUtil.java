package com.kopever.wechat.wrapper;

import org.springframework.util.StringUtils;

/**
 * Created by Lullaby on 2017/2/13.
 */
class SnakeToCamelRequestParameterUtil {

    static String convertSnakeToCamel(String snake) {
        if (snake == null) {
            return null;
        }

        if (!snake.contains("_")) {
            return snake;
        }

        StringBuilder result = new StringBuilder();

        String[] split = StringUtils.split(snake, "_");
        int index = 0;
        for (String str : split) {
            if (index == 0) {
                result.append(str.toLowerCase());
            } else {
                result.append(capitalize(str));
            }
            index++;
        }

        return result.toString();
    }

    private static String capitalize(String str) {
        if (str == null) {
            return null;
        }

        if (str.length() == 1) {
            return str.toUpperCase();
        }

        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

}
