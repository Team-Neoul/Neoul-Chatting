package com.theZ.dotoring.app.chatRoom.repository;

import com.theZ.dotoring.app.chatRoom.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRespository extends JpaRepository<ChatRoom, Long> {

    Optional<ChatRoom> findByRoomName(String roomName);

    @Query("SELECT DISTINCT cr FROM ChatRoom cr JOIN cr.visitedNames vn WHERE vn = :userName ORDER BY cr.updatedAt DESC")
    List<ChatRoom> findChatRoomsByVisitedUserName(String userName);
}
