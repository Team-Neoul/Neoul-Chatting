package com.theZ.dotoring.app.chat.repository;

import com.theZ.dotoring.app.chat.entity.ChatMessage;
import com.theZ.dotoring.app.chatRoom.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findByRoomName(String roomName);

}
