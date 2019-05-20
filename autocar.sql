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
  empty_date date default(sysdate)
);
CREATE SEQUENCE collection_seq;


delete from garbage_collection;
commit;
insert into garbage_collection (collection_no, userid) values(collection_seq.nextval, 'gs25');
commit;
select collection_no, userid, cap, empty_date
from (select row_number() over(order by collection_no desc) as seq, collection_no, userid, cap, empty_date from garbage_collection) where seq between 1 and 10;
