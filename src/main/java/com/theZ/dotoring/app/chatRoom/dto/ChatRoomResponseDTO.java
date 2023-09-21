package com.theZ.dotoring.app.chatRoom.dto;

import com.theZ.dotoring.app.chat.dto.ChatMessageReponseDTO;
import com.theZ.dotoring.app.chatRoom.entity.ChatRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ChatRoomResponseDTO {

    public List<ChatRoomResponseDTO.ChatRoomDTO> chatRoomResponseDTOS;

    public ChatRoomResponseDTO(List<ChatRoom> chatRooms) {
        this.chatRoomResponseDTOS = chatRooms.stream().map(ChatRoomResponseDTO::from).collect(Collectors.toList());
    }

    @Getter
    @Builder
    public static class ChatRoomDTO {
        private Long id;

        private String roomName;

        private String lastReceivedChat;

        private List<String> visitedNames;

        private LocalDateTime createdAt;

        private LocalDateTime updateAt;
        
    }

    public static ChatRoomResponseDTO.ChatRoomDTO from(ChatRoom chatRoom){
        return ChatRoomDTO
                .builder()
                .id(chatRoom.getId())
                .roomName(chatRoom.getRoomName())
                .lastReceivedChat(chatRoom.getLastReceivedChat())
                .visitedNames(chatRoom.getVisitedNames())
                .createdAt(chatRoom.getCreatedAt())
                .updateAt(chatRoom.getUpdatedAt())
                .build();
    }
}
