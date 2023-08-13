package com.theZ.dotoring.app.chat.controller;

import com.theZ.dotoring.app.chat.dto.ChatMessageReponseDTO;
import com.theZ.dotoring.app.chat.dto.ChatMessageRequestDTO;
import com.theZ.dotoring.app.chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageController {

    private final SimpMessagingTemplate template;

    private final MessageService messageService;

    // todo: Message를 Service를 통해서 저장해야 한다.
    @MessageMapping("/chat/message")
    public void enter(ChatMessageRequestDTO messageDTO, SimpMessageHeaderAccessor headerAccessor) {

        // message.getRoom.getVisiedNames 에 header에 있는 User의 username이 있다면 환영 아니면 환영이 아니어야 함.
        // Service에서 메세지 타입을 정해서 Message에 타입을 넘겨주는 것은 어떨까? Enter, Send 이렇게
        // 그 후 Controller에서는 반환값(Message)을 통해서 분기 처리 되는거지

        ChatMessageReponseDTO.ChatDTO chatMessage = messageService.saveMessage(messageDTO, headerAccessor);

        // 왜 지금 구독자가 못받지?
        // DTO 및 서비스 로직 문제 X -> 디버깅시 아래 코드까지 정상적으로 Message DTO 객체가 도달하기에
        // 그럼 지금 설정 및 uri 문제인가?
        if (Objects.equals(chatMessage.getType(), "ENTER")) {
            template.convertAndSend("/sub/chat/room/" + chatMessage.getRoomName(), chatMessage.getSenderName() + "님이 채팅방에 입장하셨습니다.");
        }

        if (Objects.equals(chatMessage.getType(), "TALK")) {
            template.convertAndSend("/sub/chat/room/" + chatMessage.getRoomName(), chatMessage);
        }

        // 지금 해당 api Response가 보내지고 있는 걸 어떻게 테스팅할까? 아니면 해당 메세지 자체에 있는 Room 정보를 사용할 수 있나? -> 후자가 정답
    }
}
