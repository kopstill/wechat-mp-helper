package com.kopever.wechat.service;

import com.kopever.wechat.common.constant.enumerator.WechatMsgType;
import com.kopever.wechat.common.util.XmlUtil;
import com.kopever.wechat.domain.vo.WechatEventVO;
import com.kopever.wechat.domain.vo.message.WechatTextMessageResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WechatEventService {

    public String buildSubscribeMessageResponse(WechatEventVO eventVO) {
        WechatTextMessageResponseVO responseVO = new WechatTextMessageResponseVO();
        responseVO.setToUserName(eventVO.getFromUserName());
        responseVO.setFromUserName(eventVO.getToUserName());
        responseVO.setCreateTime(System.currentTimeMillis());
        responseVO.setMsgType(WechatMsgType.TEXT.getType());
        responseVO.setContent("" +
                "富强、民主、文明、和谐\n" +
                "自由、平等、公正、法治\n" +
                "爱国、敬业、诚信、友善");

        return XmlUtil.toXml(responseVO, WechatTextMessageResponseVO.class);
    }

}
