package com.kopever.wechat.web;

import com.kopever.wechat.common.constant.config.WechatConfig;
import com.kopever.wechat.common.constant.enumerator.WechatEventType;
import com.kopever.wechat.common.constant.enumerator.WechatMsgType;
import com.kopever.wechat.common.util.RandomUtil;
import com.kopever.wechat.common.util.StreamUtil;
import com.kopever.wechat.common.util.XmlUtil;
import com.kopever.wechat.domain.vo.WechatEventVO;
import com.kopever.wechat.domain.vo.WechatVO;
import com.kopever.wechat.domain.vo.auth.WechatAuthenticationVO;
import com.kopever.wechat.domain.vo.auth.WechatVerificationVO;
import com.kopever.wechat.sdk.WXBizMsgCrypt;
import com.kopever.wechat.service.WechatEventService;
import com.kopever.wechat.service.WechatMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Lullaby on 2018/1/5
 */
@Slf4j
@RestController
public class MessageController {

    private final WechatConfig wechatConfig;

    private final WechatMessageService messageService;

    private final WechatEventService eventService;

    @Autowired
    public MessageController(WechatConfig wechatConfig,
                             WechatMessageService messageService,
                             WechatEventService eventService) {
        this.wechatConfig = wechatConfig;
        this.messageService = messageService;
        this.eventService = eventService;
    }

    @GetMapping("messages")
    public String signature(@Validated WechatVerificationVO requestVO) {
        WXBizMsgCrypt wechatCrypt = new WXBizMsgCrypt(wechatConfig.getToken());

        try {
            wechatCrypt.verifyUrl(requestVO.getSignature(), requestVO.getTimestamp(), requestVO.getNonce());
        } catch (Exception e) {
            log.error("MessageController.signature.Exception", e);
            return "sign error";
        }

        return requestVO.getEchostr();
    }

    @PostMapping("messages")
    public String messages(@Validated WechatAuthenticationVO messageVO, HttpServletRequest request) {
        try {
            WXBizMsgCrypt crypter = new WXBizMsgCrypt(
                    wechatConfig.getToken(), wechatConfig.getEncodingAesKey(), wechatConfig.getAppId());

            crypter.verifyUrl(messageVO.getSignature(), messageVO.getTimestamp(), messageVO.getNonce());

            String postData = StreamUtil.getInputStream(request.getInputStream());
            log.info("MessageController.messages.postData -> {}", postData);

            String decryptMsg = crypter.decryptMsg(messageVO.getMsgSignature(), messageVO.getTimestamp(), messageVO.getNonce(), postData);
            log.info("MessageController.messages.decryptMsg -> {}", decryptMsg);

            WechatVO wechatVO = XmlUtil.fromXml(decryptMsg, WechatVO.class);

            String message;
            switch (WechatMsgType.valueOf(wechatVO.getMsgType().toUpperCase())) {
                case TEXT:
                    message = messageService.buildWechatTextMessageResponse(decryptMsg);
                    break;
                case IMAGE:
                    message = messageService.buildWechatTextMessageResponse(decryptMsg);
                    break;
                case VOICE:
                    message = messageService.buildWechatTextMessageResponse(decryptMsg);
                    break;
                case VIDEO:
                    message = messageService.buildWechatTextMessageResponse(decryptMsg);
                    break;
                case SHORTVIDEO:
                    message = messageService.buildWechatTextMessageResponse(decryptMsg);
                    break;
                case LOCATION:
                    message = messageService.buildWechatTextMessageResponse(decryptMsg);
                    break;
                case LINK:
                    message = messageService.buildWechatTextMessageResponse(decryptMsg);
                    break;
                case EVENT:
                    message = this.events(decryptMsg);
                    break;
                default:
                    message = "impossible";
                    break;
            }

            String response = crypter.encryptMsg(message, Long.toString(System.currentTimeMillis()), RandomUtil.getRandomAllChar(32));
            log.info("MessageController.messages.response -> {}", response);

            return response;
        } catch (Exception e) {
            log.error("MessageController.messages.Exception", e);
            return "exception";
        }
    }

    private String events(String xml) {
        WechatEventVO eventVO = XmlUtil.fromXml(xml, WechatEventVO.class);

        String message;
        switch (WechatEventType.valueOf(eventVO.getEvent().toUpperCase())) {
            case SUBSCRIBE:
                message = eventService.buildSubscribeMessageResponse(eventVO);
                break;
            case UNSUBSCRIBE:
                message = eventService.buildSubscribeMessageResponse(eventVO);
                break;
            case SCAN:
                message = eventService.buildSubscribeMessageResponse(eventVO);
                break;
            case LOCATION:
                message = eventService.buildSubscribeMessageResponse(eventVO);
                break;
            case CLICK:
                message = eventService.buildSubscribeMessageResponse(eventVO);
                break;
            case VIEW:
                message = eventService.buildSubscribeMessageResponse(eventVO);
                break;
            default:
                message = "impossible";
                break;
        }
        log.info("MessageController.events.message -> {}", message);

        return message;
    }

}
