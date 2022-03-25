#backend - springboot

> backend 실행 - localhost:8080


# front - vue js
```
 /vue/vue-front 폴더로 이동
 1. npm install
 2. npm run start - localhost 8081
```
# 간단한 설명
```
vue에서 backend로 호출 시, /api 로 url경로가 있으면 proxy로 localhost:8080 으로 호출 하게끔 설정 -> vue-front/build/webpack.dev.conf.js 설정
vue에서 인증이 필요한 페이지는 router/index.js 에서 설정
springboot에서 /api/admin/**,/api/login 경로만 secury 로 인증 받게끔 설정
테스트는 junit으로 작성해 놓았습니다.
```
## 페이지 URL
```
첫 페이지(글 리스트) : http://localhost:8081/
    (내부에 문의하기 링크 존재)
어드민 : http://localhost:8081/admin/requestList
```
## DB Schema
``` 
기존적으로 MYSQL 로 만들었습니다.

-- 어드민 계정 리스트 정보
create table admin
(
    seq    int auto_increment primary key,
    userId varchar(16)  not null,
    userPw varchar(256) not null,
    constraint admin_userId_uindex unique (userId)
)
    comment '어드민 계정정보';

-- 문의글 정보 테이블
create table board
(
    seq         int auto_increment
        primary key,
    userId      varchar(64)                        not null comment '문의자',
    userPw      varchar(256)                       not null comment '문의자 비번',
    title       varchar(128)                       not null comment '문의제목',
    contents    text                               null comment '문의 내용',
    regYmdt     datetime default CURRENT_TIMESTAMP null comment '문의 일시',
    resUserId   varchar(128)                       null comment '처리 담당자ID',
    resContents text                               null comment '처리내용',
    resYmdt     datetime                           null comment '처리일시'
)
    comment '문의글 정보';

-- 테스트 어드민 계정 생성 (기본적으로 sha256 암호화)
insert into admin(userId, userPw) values('test',sha2('test123',256));

```
작성일 : 2022-03-25