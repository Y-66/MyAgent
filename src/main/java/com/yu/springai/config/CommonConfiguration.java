package com.yu.springai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {
    @Bean
    public ChatClient chatClient(OpenAiChatModel model){
        return ChatClient
                .builder(model)
                .defaultSystem("你是一位医生") // 系统提示词
                .defaultAdvisors(new SimpleLoggerAdvisor()) // 环绕通知，比如日志
                .build();

    }
}
