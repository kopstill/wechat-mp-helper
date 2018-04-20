package com.kopever.wechat.common.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class IPUtil {

    public static String getClientRealIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");

        if (!StringUtils.isEmpty(ip)) {
            return ip;
        }

        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isEmpty(ip)) {
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }

        return request.getRemoteAddr();
    }

}
