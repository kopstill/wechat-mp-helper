package com.kopever.wechat.common.util;

import java.io.*;

/**
 * Created by Lullaby on 2018/1/16
 */
public class StreamUtil {

    private static final int BUFFER_SIZE = 1024;

    private static final String ENCODING = "UTF-8";

    public static String getInputStream(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new BufferedInputStream(inputStream, BUFFER_SIZE), ENCODING), BUFFER_SIZE)) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        }

        return builder.toString();
    }

}
