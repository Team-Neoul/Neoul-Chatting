package com.theZ.dotoring.app.chat.service;

import com.theZ.dotoring.app.chat.dto.ChatMessageReponseDTO;
import com.theZ.dotoring.app.chat.dto.ChatMessageRequestDTO;
import com.theZ.dotoring.app.chat.entity.ChatMessage;
import com.theZ.dotoring.app.chat.repository.MessageRepository;
import com.theZ.dotoring.app.chatRoom.entity.ChatRoom;
import com.theZ.dotoring.app.chatRoom.repository.ChatRoomRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    private final ChatRoomRespository roomRepository;

    // todo: 메세지를 DB에 저장
    @Transactional
    public ChatMessageReponseDTO.ChatDTO saveMessage(ChatMessageRequestDTO messageDTO, SimpMessageHeaderAccessor headerAccessor) {

        ChatMessage message = ChatMessage.to(messageDTO);

        String userName = Objects.requireNonNull(headerAccessor.getUser()).getName();

        // 이 때 재접속인지 처음 입장인지 분기 처리가 되어야 함.
        // 만약 room이 존재한다면 분기처리가 되어야 하지만, room이 없다면 처음 입장일 수 밖에 없다.
        // room이 없을 때는 분기처리 하지말자.

        // 해당 roomName의 room이 있는지
        ChatRoom chatRoom = findRoom(message.getRoomName());

        // 있다면 message 저장
        if (chatRoom != null) {

            // 재입장인지 아닌지 분기 처리
            for (String s : chatRoom.getVisitedNames()) {
                // 재입장이라면
                if (s.equals(userName)) {
                    return ChatMessageReponseDTO.from(ifUserReEnter(chatRoom, message, userName));
                }
            }

            // 재입장이 아니더라도
            return ChatMessageReponseDTO.from(ifUserNotReEnterExistRoom(chatRoom, message, userName));
        }

        // 맨 처음 입장이라면 -> Room 생성 후 메시지 생성
        return ChatMessageReponseDTO.from(ifUserFirstEnter(message, userName));
    }

    // 재입장이라면
    private ChatMessage ifUserReEnter(ChatRoom chatRoom, ChatMessage message, String userName){
        chatRoom.updateLastRecievedChat(message.getMessage());

        message.updateStatus(ChatMessage.MessageType.TALK);
        message.updateSenderName(userName);
        message.updateRoomName(chatRoom.getRoomName());

        return messageRepository.save(message);
    }

    // 재입장은 아니되 이미 방이 존재할 때
    private ChatMessage ifUserNotReEnterExistRoom(ChatRoom chatRoom, ChatMessage message, String userName){
        chatRoom.getVisitedNames().add(userName);
        chatRoom.updateLastRecievedChat(message.getMessage());

        message.updateStatus(ChatMessage.MessageType.ENTER);
        message.updateSenderName(userName);
        message.updateRoomName(chatRoom.getRoomName());

        return messageRepository.save(message);
    }

    // 맨 처음 입장이라면
    private ChatMessage ifUserFirstEnter(ChatMessage message, String userName){
        ChatRoom chatRoomOP = createRoom(message);

        chatRoomOP.getVisitedNames().add(userName);
        chatRoomOP.setRandomRoomName();
        chatRoomOP.updateLastRecievedChat(message.getMessage());

        message.updateStatus(ChatMessage.MessageType.ENTER);
        message.updateSenderName(userName);
        message.updateRoomName(chatRoomOP.getRoomName());

        return messageRepository.save(message);
    }

    // Room 생성
    private ChatRoom createRoom(ChatMessage message){
        ChatRoom chatRoom =
                ChatRoom.builder()
                .lastReceivedChat(message.getMessage()).build();
        return roomRepository.save(chatRoom);
    }

    public ChatRoom findRoom(String roomName) {
        Optional<ChatRoom> room = roomRepository.findByRoomName(roomName);

        return room.orElse(null);
    }
}
