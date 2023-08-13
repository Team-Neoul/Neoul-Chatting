package com.theZ.dotoring.app.chatRoom.entity;

import com.theZ.dotoring.app.commonModel.CommonEntity;
import lombok.*;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class ChatRoom extends CommonEntity {

    // 해당 roomName 지정을 해줘야 함.
    private String roomName;

    // 마지막으로 받은 문자 업데이트 해줘야 함.
    private String lastReceivedChat;

    @ElementCollection
    @CollectionTable(
            name = "visitedNames",
            joinColumns = @JoinColumn(name = "chatRoom_id")
    )
    @Builder.Default
    // 방문한 사람들 names
    private List<String> visitedNames = new ArrayList<>();

    // todo: roomName 랜덤하게 생성
    public void setRandomRoomName(){
        this.roomName = UUID.randomUUID().toString();
    }

    public void updateLastRecievedChat(String lastReceivedChat){
        this.lastReceivedChat = lastReceivedChat;
    }
}