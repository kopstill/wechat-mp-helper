package com.kopever.wechat.sign;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.util.Arrays;

public class WechatSignTest {

    @Test
    public void testSignature() {
        // signature=bc27bc1b48b9400e710a401b5cce2e3c1d823fcd&timestamp=1515818939&nonce=1606988837&openid=oJBgJ08XhPDAj7zeQVSk5ThARDr8
        String token = "Mui81cmSnwlK88aa8El3L3YK1RxD8kiY";

        String signature = "bc27bc1b48b9400e710a401b5cce2e3c1d823fcd";
        String timestamp = "1515818939";
        String nonce = "1606988837";
        String openid = "oJBgJ08XhPDAj7zeQVSk5ThARDr8";

        String[] array = {timestamp, nonce, openid, token};
        Arrays.sort(array);

        String text = array[0] + array[1] + array[2] + array[3];
        String sign = DigestUtils.sha1Hex(text);
        System.out.println(sign);
        System.out.println(signature.equals(sign));
    }

}
