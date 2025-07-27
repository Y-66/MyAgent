package com.yu.springai.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("conversation_list") // 对应数据库表名
public class Conversation {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("business_type")
    private String businessType;

    @TableField("conversation_id")
    private String conversationId;

    @TableField("created_at")
    private LocalDateTime createdAt;


}
