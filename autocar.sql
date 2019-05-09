drop table clean_user;
CREATE TABLE clean_user (
  userid varchar(20) not null primary key,
  is_admin number default(0),
  passwd varchar(20) not null,
  email varchar(30) not null,
  address varchar(50),
  ip varchar(30),
  bin number default(0),
  cap number default(0),
  lat number default(0.0),
  lon number default(0.0),
  phone varchar(30),
  REG_DATE DATE DEFAULT(SYSDATE),
  UPDATE_DATE DATE DEFAULT(SYSDATE)
);

drop table garbage_collection;
CREATE TABLE garbage_collection (
  garbage_no number primary key,
  userid varchar(20) not null,
  cap number default(0),
  empty_date date default(sysdate)
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

select * from clean_user;

DESC CLEAN_USER;

UPDATE CLEAN_USER SET 
		 PASSWD='123123',
		 EMAIL='sim2984@naver.com',
		 ADDRESS='서울시 강남구',  
		 BIN=0,
		 LAT=0.0,
		 LON=0.12,
		 PHONE='010-2007-2846' 
		WHERE USERID='abc';
    rollback;
    
select * from clean_user where bin = 1;