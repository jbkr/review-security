use ver2;

show tables;

drop table IF exists member;

## 테이블 생성
create table member (
	id bigint auto_increment not null COMMENT '순번',
    username varchar(50) unique not null COMMENT '사용자 아이디',
    password varchar(500) not null COMMENT '비밀번호',
    role varchar(10) not null COMMENT '역할',
    primary key(id)
);

## 설정된 코멘트 보기
show full columns from member;

select * from member;