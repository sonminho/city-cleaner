CREATE TABLE BOARD (
  BOARD_ID NUMBER PRIMARY KEY,
  TITLE VARCHAR2(256) NOT NULL,
  WRITER VARCHAR2(50) NOT NULL,
  PASSWORD VARCHAR2(20) NOT NULL,
  READ_CNT NUMBER DEFAULT(0),
  CONTENT CLOB,
  REG_DATE DATE DEFAULT(SYSDATE),
  UPDATE_DATE DATE DEFAULT(SYSDATE)
);

create sequence board_seq;

drop table clean_user;
CREATE TABLE clean_user (
  userid varchar(20) not null primary key,
  passwd varchar(20) not null,
  email varchar(30) not null,
  address varchar(50) not null,
  bin number default(0),
  lat number default(0.0),
  lon number default(0.0),
  phone varchar(30),
  REG_DATE DATE DEFAULT(SYSDATE),
  UPDATE_DATE DATE DEFAULT(SYSDATE)
);

INSERT INTO CLEAN_USER (userid, passwd, email, address, lat, lon, reg_date, update_date) 
values ('abc', '1234', 's@naver.com', '서울시', 37.1, 23.4, sysdate, sysdate);
INSERT INTO CLEAN_USER (userid, passwd, email, address, lat, lon, reg_date, update_date) 
values ('abcd', '1234', 's@naver.com', '서울시', 37.1, 23.4, sysdate, sysdate);
select * from clean_user;
commit;

SELECT COUNT(*) FROM CLEAN_USER;
desc clean_user;

select userid, passwd, email, address, lat, lon, bin, reg_date, update_date 
		from (select row_number() over(order by reg_date asc) as seq, userid, passwd, email, address, lat, lon, bin, reg_date, update_date from clean_user)
		where seq between 1 and 10;

select row_number() over(order by reg_date asc) as seq, userid, passwd, email, address, lat, lon, bin, reg_date, update_date from clean_user;