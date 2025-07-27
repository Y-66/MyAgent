package com.yu.springai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yu.springai.entity.po.Conversation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConversationMapper extends BaseMapper<Conversation> {
}
