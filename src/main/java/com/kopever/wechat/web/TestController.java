package com.kopever.wechat.web;

import com.kopever.wechat.domain.vo.auth.WechatVerificationVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private static final Logger LOG = LoggerFactory.getLogger(MessageController.class);

    @GetMapping("/test")
    public String test() {
        LOG.info("/test");
        return "test";
    }

    @GetMapping("/test/body")
    public WechatVerificationVO testBody() {
        WechatVerificationVO vo = new WechatVerificationVO();
        vo.setTimestamp(String.valueOf(System.currentTimeMillis()));
        return vo;
    }

    @GetMapping("/test/void")
    public void testVoid() {
        LOG.info("/test/void");
    }

    @GetMapping("/test/exception")
    public void testException() {
        LOG.info("/test/exception");
        throw new RuntimeException("test exception");
    }

}
