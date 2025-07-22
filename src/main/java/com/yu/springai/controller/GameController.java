package com.yu.springai.controller;

import com.yu.springai.repository.ChatHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class GameController {
    private final ChatClient gameChatClient;

    @RequestMapping(value = "/game", produces = "text/html;charset=utf-8")
    public Flux<String> chat(String prompt, String chatId) {


        // .call 全部返回  .stream 流式返回
        return gameChatClient.prompt()
                .user(prompt)
                .advisors(a -> a.param(CONVERSATION_ID, chatId))
                .stream()
                .content();
    }
}
