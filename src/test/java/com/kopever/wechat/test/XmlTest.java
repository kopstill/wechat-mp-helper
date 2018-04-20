package com.kopever.wechat.test;

import com.kopever.wechat.common.constant.enumerator.WechatMsgType;
import com.kopever.wechat.common.instance.XStreamInstance;
import com.kopever.wechat.common.util.XmlUtil;
import com.kopever.wechat.domain.vo.WechatVO;
import com.kopever.wechat.domain.vo.message.WechatTextMessageRequestVO;
import com.kopever.wechat.domain.vo.message.WechatTextMessageResponseVO;
import com.kopever.wechat.test.domain.Person;
import com.kopever.wechat.test.domain.PhoneNumber;
import com.thoughtworks.xstream.XStream;
import org.junit.Test;

/**
 * Created by Lullaby on 2018/1/16
 */
public class XmlTest {

    @Test
    public void testXstreamAlias() {
//        XStream xstream = new XStream(new StaxDriver());
        XStream xstream = XStreamInstance.INSTANCE.getXstream();

        xstream.alias("person", Person.class);
        xstream.alias("phonenumber", PhoneNumber.class);

        Person joe = new Person("Joe", "Walnes");
        joe.setPhone(new PhoneNumber(123, "1234-456"));
        joe.setFax(new PhoneNumber(123, "9999-999"));

        String xml = xstream.toXML(joe);
        System.out.println(xml);

        Person newJoe = (Person) xstream.fromXML(xml);
        System.out.println(newJoe);
    }

    @Test
    public void testXstreamFromXML() {
        String xml = "<xml><ToUserName><![CDATA[gh_561263450934]]></ToUserName>\n" +
                "<FromUserName><![CDATA[oJBgJ08XhPDAj7zeQVSk5ThARDr8]]></FromUserName>\n" +
                "<CreateTime>1516209999</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[test]]></Content>\n" +
                "<MsgId>6512072360006265558</MsgId>\n" +
                "</xml>";

        WechatTextMessageRequestVO requestVO = XmlUtil.fromXml(xml, WechatTextMessageRequestVO.class);

//        XStream xstream = XStreamInstance.INSTANCE.getXstream();
//        xstream.processAnnotations(WechatTextMessageResponseVO.class);
//        xstream.alias("xml", WechatTextMessageRequestVO.class);

        WechatTextMessageResponseVO responseVO = new WechatTextMessageResponseVO();
        responseVO.setToUserName(requestVO.getFromUserName());
        responseVO.setFromUserName(requestVO.getToUserName());
        responseVO.setCreateTime(System.currentTimeMillis());
        responseVO.setMsgType(WechatMsgType.TEXT.getType());
        responseVO.setContent("mmp");

//        xstream.processAnnotations(WechatTextMessageResponseVO.class);
        String toXML = XmlUtil.toXml(responseVO, WechatTextMessageResponseVO.class);
        System.out.println(toXML);
    }

    @Test
    public void testXstreamToXML() {
        WechatTextMessageRequestVO requestVO = new WechatTextMessageRequestVO();
        requestVO.setToUserName("tousername");
        requestVO.setFromUserName("fromusername");
        requestVO.setCreateTime(System.currentTimeMillis());
        requestVO.setMsgType(WechatMsgType.TEXT.getType());
        requestVO.setContent("mmp");

//        XStream xstream = new XStream(new StaxDriver());
        XStream xstream = XStreamInstance.INSTANCE.getXstream();
        xstream.processAnnotations(WechatTextMessageRequestVO.class);
        String xml = xstream.toXML(requestVO);
        System.out.println(xml);

        WechatTextMessageRequestVO vo = (WechatTextMessageRequestVO) xstream.fromXML(xml);
        System.out.println(vo.getContent());
    }

    @Test
    public void testXstreamAliasExtend() {
        String xml = "<xml><ToUserName><![CDATA[gh_561263450934]]></ToUserName>\n" +
                "<FromUserName><![CDATA[oJBgJ08XhPDAj7zeQVSk5ThARDr8]]></FromUserName>\n" +
                "<CreateTime>1516210455</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[test]]></Content>\n" +
                "<MsgId>6512074318511352597</MsgId>\n" +
                "</xml>";

        XStream xstream = XStreamInstance.INSTANCE.getXstream();
        xstream.ignoreUnknownElements();
        xstream.alias("xml", WechatVO.class);

        WechatVO vo = (WechatVO) xstream.fromXML(xml);
        System.out.println(vo.getMsgType());
    }

}
