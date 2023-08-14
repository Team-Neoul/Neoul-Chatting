package com.theZ.dotoring.app.chat.controller;

import com.theZ.dotoring.app.chat.dto.ChatMessageReponseDTO;
import com.theZ.dotoring.app.chat.dto.ChatMessageRequestDTO;
import com.theZ.dotoring.app.chat.service.MessageService;
import com.theZ.dotoring.app.chatRoom.service.ChatRoomService;
import com.theZ.dotoring.app.fcm.dto.FCMNotificationRequestDto;
import com.theZ.dotoring.app.fcm.service.FCMNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageController {

    private final SimpMessagingTemplate template;

    private final MessageService messageService;

    private final ChatRoomService chatRoomService;

    private final FCMNotificationService fcmNotificationService;

    // todo: Message를 Service를 통해서 저장해야 한다.
    @MessageMapping("/chat/message")
    @Transactional
    public void enter(ChatMessageRequestDTO messageDTO, SimpMessageHeaderAccessor headerAccessor) {

        ChatMessageReponseDTO.ChatDTO chatMessage = messageService.saveMessage(messageDTO, headerAccessor);

        if (Objects.equals(chatMessage.getType(), "ENTER")) {
            template.convertAndSend("/sub/chat/room/" + chatMessage.getRoomName(), chatMessage.getSenderName() + "님이 채팅방에 입장하셨습니다.");
            List<String> visitedNames = chatRoomService.findVisitedNames(chatMessage.getRoomName());

            for (String name : visitedNames) {
                String ans = fcmNotificationService.sendNotificationByToken(
                        new FCMNotificationRequestDto(name, chatMessage.getSenderName() + "님이 채팅을 보내셨습니다.", chatMessage.getMessage()));
                log.info(ans);
            }
        }

        if (Objects.equals(chatMessage.getType(), "TALK")) {
            template.convertAndSend("/sub/chat/room/" + chatMessage.getRoomName(), chatMessage);
            // 여기서 알람 전달해야 함.
            List<String> visitedNames = chatRoomService.findVisitedNames(chatMessage.getRoomName());

            for (String name : visitedNames) {
                String ans = fcmNotificationService.sendNotificationByToken(
                        new FCMNotificationRequestDto(name, chatMessage.getSenderName() + "님이 채팅을 보내셨습니다.", chatMessage.getMessage()));
                log.info(ans);
            }
        }
    }
}
