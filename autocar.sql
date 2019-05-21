drop table clean_user;
CREATE TABLE clean_user (
  userid varchar(20) not null primary key,
  is_admin number default(0),
  passwd varchar(20) not null,
  email varchar(30) not null,
  address varchar(100),
  ip varchar(30),
  bin number default(0),
  cap number default(0),
  lat number default(0.0),
  lon number default(0.0),
  phone varchar(30),
  condition varchar(30) default('waiting'),
  REG_DATE DATE DEFAULT(SYSDATE),
  UPDATE_DATE DATE DEFAULT(SYSDATE)
);

select * from clean_user;

drop table garbage_collection;
CREATE TABLE garbage_collection (
  collection_no number primary key,
  userid varchar(20) not null,
  cap number default(0),
  address varchar(100),
  empty_date date default(sysdate)
);
DROP SEQUENCE collection_seq;
CREATE SEQUENCE collection_seq;

select * from clean_user;
select * from GARBAGE_COLLECTION;
delete from garbage_collection;
commit;

insert into garbage_collection (collection_no, userid, address, cap) values(collection_seq.nextval, 'wooribank', '서울특별시 강남구 역삼2동 726', 50);
