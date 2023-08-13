package com.theZ.dotoring.app.chat.repository;

import com.theZ.dotoring.app.chat.entity.ChatMessage;
import com.theZ.dotoring.app.chatRoom.entity.ChatRoom;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<ChatMessage, Long> {

    Slice<ChatMessage> findByRoomNameOrderByCreatedAtDesc(String roomName, Pageable pageable);

}
