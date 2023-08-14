package com.theZ.dotoring.app.chatRoom.service;

import com.theZ.dotoring.app.auth.MemberDetails;
import com.theZ.dotoring.app.chat.entity.ChatMessage;
import com.theZ.dotoring.app.chat.repository.MessageRepository;
import com.theZ.dotoring.app.chatRoom.entity.ChatRoom;
import com.theZ.dotoring.app.chatRoom.repository.ChatRoomRespository;
import com.theZ.dotoring.app.letter.domain.Letter;
import com.theZ.dotoring.app.letter.dto.LetterByMemberResponseDTO;
import com.theZ.dotoring.app.letter.mapper.LetterMapper;
import com.theZ.dotoring.app.menti.repository.MentiRepository;
import com.theZ.dotoring.app.mento.repository.MentoRepository;
import com.theZ.dotoring.common.MessageCode;
import com.theZ.dotoring.exception.NotFoundLetterException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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
    public List<String> findVisitedNames(String roomName){

        Optional<ChatRoom> chatRoom = chatRoomRespository.findByRoomName(roomName);

        if (chatRoom.isEmpty()) {
            throw new RuntimeException();
        }

        return chatRoom.get().getVisitedNames();
    }

    @Transactional
    public List<ChatRoom> findAllRooms(MemberDetails memberDetails){

        return chatRoomRespository.findChatRoomsByVisitedUserName(memberDetails.getUsername());
    }

    @Transactional
    public Slice<ChatMessage> findMessageByRoom(String roomName, Pageable pageable){

        List<ChatMessage> messages = messageRepository.findByRoomNameOrderByCreatedAtDesc(roomName, pageable).toList();

        return new PageImpl<>(messages, pageable, messages.size());
    }
}