package com.kopever.wechat.service;

import com.kopever.wechat.common.constant.enumerator.WechatMsgType;
import com.kopever.wechat.common.util.XmlUtil;
import com.kopever.wechat.dao.WechatMessageRepository;
import com.kopever.wechat.domain.vo.message.WechatTextMessageRequestVO;
import com.kopever.wechat.domain.vo.message.WechatTextMessageResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Lullaby on 2018/1/16
 */
@Service
public class WechatMessageService {

    private final WechatMessageRepository wechatMessageRepository;

    @Autowired
    public WechatMessageService(WechatMessageRepository wechatMessageRepository) {
        this.wechatMessageRepository = wechatMessageRepository;
    }

    public String buildWechatTextMessageResponse(String xml) {
        WechatTextMessageRequestVO requestVO = XmlUtil.fromXml(xml, WechatTextMessageRequestVO.class);

        wechatMessageRepository.save(requestVO);

        WechatTextMessageResponseVO responseVO = new WechatTextMessageResponseVO();
        responseVO.setToUserName(requestVO.getFromUserName());
        responseVO.setFromUserName(requestVO.getToUserName());
        responseVO.setCreateTime(System.currentTimeMillis());
        responseVO.setMsgType(WechatMsgType.TEXT.getType());
        responseVO.setContent("mmp");

        wechatMessageRepository.save(responseVO);

        return XmlUtil.toXml(responseVO, WechatTextMessageResponseVO.class);
    }

}
