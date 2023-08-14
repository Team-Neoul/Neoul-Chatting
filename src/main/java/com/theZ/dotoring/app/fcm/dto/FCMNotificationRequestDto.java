package com.theZ.dotoring.app.fcm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FCMNotificationRequestDto {

    private String targetUserId;
    private String title;
    private String body;
    // private String image;
    // private Map<String, String> data;

}