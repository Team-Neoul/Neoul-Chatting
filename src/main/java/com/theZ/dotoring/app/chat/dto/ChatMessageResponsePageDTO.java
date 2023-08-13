package com.theZ.dotoring.app.chat.dto;

import com.theZ.dotoring.app.chat.entity.ChatMessage;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class ChatMessageResponsePageDTO {

    private final Long id;

    private final String type;

    private final String roomName;

    private final String senderName;

    private final String message;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public ChatMessageResponsePageDTO(ChatMessage message) {
        this.id = message.getId();
        this.type = message.getType().toString();
        this.roomName = message.getRoomName();
        this.senderName = message.getSenderName();
        this.message = message.getMessage();
        this.createdAt = message.getCreatedAt();
        this.updatedAt = message.getUpdatedAt();
    }
}
