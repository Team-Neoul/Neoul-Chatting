package com.theZ.dotoring.app.chatRoom.controller;

import com.theZ.dotoring.app.auth.MemberDetails;
import com.theZ.dotoring.app.chat.dto.ChatMessageReponseDTO;
import com.theZ.dotoring.app.chat.dto.ChatMessageResponsePageDTO;
import com.theZ.dotoring.app.chat.entity.ChatMessage;
import com.theZ.dotoring.app.chatRoom.dto.ChatRoomResponseDTO;
import com.theZ.dotoring.app.chatRoom.entity.ChatRoom;
import com.theZ.dotoring.app.chatRoom.service.ChatRoomService;
import com.theZ.dotoring.common.ApiResponse;
import com.theZ.dotoring.common.ApiResponseGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    // visitedNames와 현재 userName과 일치하는 채팅 방들을 최신 수정순으로 반환
    @GetMapping("/rooms")
    public ApiResponse<ApiResponse.CustomBody<ChatRoomResponseDTO>> findAllRooms(@AuthenticationPrincipal MemberDetails memberDetails){

        List<ChatRoom> chatRooms = chatRoomService.findAllRooms(memberDetails);

        return ApiResponseGenerator.success(new ChatRoomResponseDTO(chatRooms), HttpStatus.OK);
    }

    // 특정 roomName의 Message들 반환
    @GetMapping("/rooms/{roomName}")
    public ApiResponse<ApiResponse.CustomBody<Slice<ChatMessageResponsePageDTO>>> findMessageByRoom(@PathVariable("roomName") String roomName, Pageable pageable){

        Slice<ChatMessage> chatMessages = chatRoomService.findMessageByRoom(roomName, pageable);

        Slice<ChatMessageResponsePageDTO> pageDTOSlice = chatMessages.map(ChatMessageResponsePageDTO::new);

        return ApiResponseGenerator.success(pageDTOSlice, HttpStatus.OK);
    }
}