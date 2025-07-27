package com.yu.springai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yu.springai.entity.po.Conversation;
import com.yu.springai.mapper.ConversationMapper;
import com.yu.springai.service.IConversationService;
import com.yu.springai.service.ICourseReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConversationServiceImpl extends ServiceImpl<ConversationMapper, Conversation> implements IConversationService {

}
