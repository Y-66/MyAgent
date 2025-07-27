package com.yu.springai.controller;

import com.yu.springai.entity.po.Conversation;
import com.yu.springai.repository.ChatHistoryRepository;
import com.yu.springai.service.IConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ai")
public class CustomerServiceController {

    private final ChatClient serviceChatClient;
    private final ChatHistoryRepository chatHistoryRepository;
    private final IConversationService conversationService;

    @RequestMapping(value = "/service", produces = "text/html;charset=utf-8")
    public Flux<String> service(String prompt, String chatId) {
        // 1. 保存回话ID
        Conversation conversation = new Conversation();
        conversation.setBusinessType("service");
        conversation.setConversationId(chatId);
        conversationService.save(conversation);
        //chatHistoryRepository.save("service", chatId);
        // 2. 请求模型

        // .call 全部返回  .stream 流式返回
        return serviceChatClient.prompt()
                .user(prompt)
                .advisors(a -> a.param(CONVERSATION_ID, chatId))
                .stream()
                .content();
    }
}
