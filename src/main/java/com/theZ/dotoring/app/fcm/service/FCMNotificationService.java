package com.theZ.dotoring.app.fcm.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.theZ.dotoring.app.fcm.dto.FCMNotificationRequestDto;
import com.theZ.dotoring.app.menti.model.Menti;
import com.theZ.dotoring.app.menti.repository.MentiRepository;
import com.theZ.dotoring.app.mento.model.Mento;
import com.theZ.dotoring.app.mento.repository.MentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FCMNotificationService {

    private final FirebaseMessaging firebaseMessaging;
    private final MentoRepository mentoRepository;
    private final MentiRepository mentiRepository;


    // 채팅 보낼 때 for 문 돌면서 해당 user에게, 알림 보내야겠지? -> 비동기로 보내는 방법은 없을까?
    @Transactional
    public String sendNotificationByToken(FCMNotificationRequestDto requestDto) {

        // 멘토, 멘티인지 분기
        Optional<Mento> mento = mentoRepository.findByNickname(requestDto.getTargetUserId());

        Optional<Menti> menti = mentiRepository.findByNickname(requestDto.getTargetUserId());

        // 멘토라면
        if (mento.isPresent()) {
            return sendNotificationByTokenFromMento(mento.get(), requestDto);
        }

        if (menti.isPresent()) {
            return sendNotificationByTokenFromMenti(menti.get(), requestDto);
        }

        return "해당하는 유저가 존재하지 않습니다.";
    }

    private String sendNotificationByTokenFromMento(Mento mento, FCMNotificationRequestDto requestDto){
        if (mento.getFirebaseToken() != null) {
            Notification notification = Notification.builder()
                    .setTitle(requestDto.getTitle())
                    .setBody(requestDto.getBody())
                    // .setImage(requestDto.getImage())
                    .build();

            Message message = Message.builder()
                    .setToken(mento.getFirebaseToken())
                    .setNotification(notification)
                    // .putAllData(requestDto.getData())
                    .build();

            try {
                firebaseMessaging.send(message);
                return "알림을 성공적으로 전송했습니다. targetUserId=" + requestDto.getTargetUserId();
            } catch (FirebaseMessagingException e) {
                e.printStackTrace();
                return "알림 보내기를 실패하였습니다. targetUserId=" + requestDto.getTargetUserId();
            }
        } else {
            return "서버에 저장된 해당 유저의 FirebaseToken이 존재하지 않습니다. targetUserId=" + requestDto.getTargetUserId();
        }
    }

    private String sendNotificationByTokenFromMenti(Menti menti, FCMNotificationRequestDto requestDto){
        if (menti.getFirebaseToken() != null) {
            Notification notification = Notification.builder()
                    .setTitle(requestDto.getTitle())
                    .setBody(requestDto.getBody())
                    // .setImage(requestDto.getImage())
                    .build();

            Message message = Message.builder()
                    .setToken(menti.getFirebaseToken())
                    .setNotification(notification)
                    // .putAllData(requestDto.getData())
                    .build();

            try {
                firebaseMessaging.send(message);
                return "알림을 성공적으로 전송했습니다. targetUserId=" + requestDto.getTargetUserId();
            } catch (FirebaseMessagingException e) {
                e.printStackTrace();
                return "알림 보내기를 실패하였습니다. targetUserId=" + requestDto.getTargetUserId();
            }
        } else {
            return "서버에 저장된 해당 유저의 FirebaseToken이 존재하지 않습니다. targetUserId=" + requestDto.getTargetUserId();
        }
    }
}