
create table board(
seq number(5) constraint board_seq_pk primary key,
title varchar2(100) constraint board_title_nn not null,
contents varchar2(4000),
writer varchar2(30) constraint board_writer_fk references member(userid),
time date,
viewcount number(5),
password number(10));


create table member(
userid varchar2(30) constraint member_userid_pk primary key,
password varchar2(10) constraint member_password_nn not null,
name varchar2(30) constraint member_name_nn not null,
address varchar2(100) ,
gender varchar2(1) ,
indate date default sysdate ,
phone varchar2(30) ,
email varchar2(30));
 



create sequence board_seq;
select board_seq.nextval from dual;

or

select into ((select max(seq)+1 from board), ...)

