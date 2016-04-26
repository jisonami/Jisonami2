# 建表操作
create table t_user(
	id varchar(50) primary key,
	name varchar(20) not null,
	password varchar(20) not null
);
create table t_blog(
  id varchar(50) primary key,
  title varchar(200) not null,
  content clob not null,
  author varchar(20) not null,
  blogType varchar(4000),
  publishTime date,
  editTime date
);
create table t_blogtype(
  id varchar(50) primary key,
  blogauthor varchar(20) not null,
  name varchar(20) not null
);

# 改字段
# alter table t_blog modify (title varchar(200));

# 删表
# drop table t_user;
# drop table t_blog;