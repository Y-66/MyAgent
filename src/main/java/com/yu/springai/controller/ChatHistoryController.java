package com.yu.springai.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yu.springai.entity.po.Conversation;
import com.yu.springai.entity.vo.MessageVO;
import com.yu.springai.repository.ChatHistoryRepository;
import com.yu.springai.service.IConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ai/history")
public class ChatHistoryController {

    private final ChatHistoryRepository chatHistoryRepository;

    private final IConversationService conversationService;

    private final ChatMemory chatMemory;
    @GetMapping("/{type}")
    public List<String> getChatIds(@PathVariable("type") String type) {
        LambdaQueryWrapper<Conversation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Conversation::getBusinessType, type)
                .select(Conversation::getConversationId)
                .orderByDesc(Conversation::getCreatedAt);

        return conversationService.list(queryWrapper)
                .stream()
                .map(Conversation::getConversationId)
                .distinct()
                .toList(); // 或使用 .collect(Collectors.toList())
    }

    @GetMapping("/{type}/{chatId}")
    public List<MessageVO> getChatMessages(@PathVariable("type") String type, @PathVariable("chatId") String chatId) {
        List<Message> messages = chatMemory.get(chatId);
        return messages.stream().map(MessageVO::new).toList();
    }

    @DeleteMapping("/{type}/{chatId}")
    public void deleteChatById(@PathVariable("type") String type, @PathVariable("chatId") String chatId) {
        LambdaQueryWrapper<Conversation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Conversation::getBusinessType, type)
                        .eq(Conversation::getConversationId, chatId);
        conversationService.remove(queryWrapper);
    }
}
