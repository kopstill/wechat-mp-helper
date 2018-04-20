package com.kopever.wechat.dao;

import com.kopever.wechat.domain.vo.WechatMessageVO;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Lullaby on 2018/2/9
 */
public interface WechatMessageRepository extends MongoRepository<WechatMessageVO, Long> {


}
