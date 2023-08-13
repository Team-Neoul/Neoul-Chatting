package com.theZ.dotoring.app.chat.entity;

import com.theZ.dotoring.app.chat.dto.ChatMessageRequestDTO;
import com.theZ.dotoring.app.commonModel.CommonEntity;
import lombok.*;

import javax.persistence.Entity;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class ChatMessage extends CommonEntity {

    public enum MessageType {
        ENTER, TALK
    }

    private MessageType type;

    //채팅방 ID
    private String roomName;

    //보내는 사람
    // senderName이 나을 것 같은 이유 -> Mento, Menti 분리에도 영향을 안받기 위해
    private String senderName;

    //내용
    private String message;

    public static ChatMessage to(ChatMessageRequestDTO requestDTO){
        ChatMessage chatMessage = ChatMessage.builder()
                .message(requestDTO.getMessage())
                .senderName(requestDTO.getSenderName())
                .roomName(requestDTO.getRoomName())
                .build();

        return chatMessage;
    }

    public void updateSenderName(String username){
        this.senderName = username;
    }

    public void updateStatus(MessageType type){ this.type = type; }

    public void updateRoomName(String roomName){
        this.roomName = roomName;
    }
}