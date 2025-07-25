package com.yu.springai.controller;

import com.yu.springai.repository.ChatHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.content.Media;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Objects;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ai")
public class ChatController {

    private final ChatClient chatClient;
    private final ChatHistoryRepository chatHistoryRepository;

    @RequestMapping(value = "/chat", produces = "text/html;charset=utf-8")
    public Flux<String> chat(@RequestParam("prompt") String prompt,
                             @RequestParam("chatId")String chatId,
                             @RequestParam(value = "files", required = false)List<MultipartFile> files) {
        // 1. 保存回话ID
        chatHistoryRepository.save("chat", chatId);
        // 2. 请求模型

        // .call 全部返回  .stream 流式返回
        if (files == null || files.isEmpty()) {
            // 没附件纯文本聊天
            return textChat(prompt, chatId);
        } else {
            // 有附件，多模态聊天
            return multimodalityChat(prompt, chatId, files);
        }

    }

    private Flux<String> multimodalityChat(String prompt, String chatId, List<MultipartFile> files) {
        // 1. 解析多媒体
        // TODO bug：只支持图片，上传音频会报错，alibaba 的 qwen 模型与 SpringAi 的接口标准不符
        List<Media> medias = files.stream()
                .map(file -> new Media(
                        MimeType.valueOf(Objects.requireNonNull(file.getContentType())),
                        file.getResource()))
                .toList();
        // 2. 请求模型
        return chatClient.prompt()
                .user(p -> p.text(prompt).media(medias.toArray(Media[]::new)))
                .advisors(a -> a.param(CONVERSATION_ID, chatId))
                .stream()
                .content();
    }

    private Flux<String> textChat(String prompt, String chatId) {
        return chatClient.prompt()
                .user(prompt)
                .advisors(a -> a.param(CONVERSATION_ID, chatId))
                .stream()
                .content();
    }
}
