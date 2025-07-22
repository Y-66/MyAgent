package com.yu.springai.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ai")
public class ChatController {

    private final ChatClient chatClient;

    @RequestMapping("/chat")
    public Flux<String> chat(String prompt) {
        // .call 全部返回  .stream 流式返回
        return chatClient.prompt()
                .user(prompt)
                .stream()
                .content();
    }
}
