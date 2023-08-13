package com.theZ.dotoring.app.chatRoom.service;

import com.theZ.dotoring.app.auth.MemberDetails;
import com.theZ.dotoring.app.chat.entity.ChatMessage;
import com.theZ.dotoring.app.chat.repository.MessageRepository;
import com.theZ.dotoring.app.chatRoom.entity.ChatRoom;
import com.theZ.dotoring.app.chatRoom.repository.ChatRoomRespository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatRoomService {

    // todo: DB에 저장하는 방식으로 변경하기
    private final ChatRoomRespository chatRoomRespository;

    private final MessageRepository messageRepository;

    @Transactional
    public List<ChatRoom> findAllRooms(MemberDetails memberDetails){

        return chatRoomRespository.findChatRoomsByVisitedUserName(memberDetails.getUsername());
    }

    @Transactional
    public List<ChatMessage> findMessageByRoom(String roomName){

        return messageRepository.findByRoomName(roomName);
    }
}