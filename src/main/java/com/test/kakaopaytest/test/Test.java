package com.test.kakaopaytest.test;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Test {
    private int seq;
    private String userId;
    private String userPw;
    private String title;
    private String contents;
    private LocalDateTime regYmdt;
    private String resUserId;
    private String resContents;
    private LocalDateTime resYmdt;
}
