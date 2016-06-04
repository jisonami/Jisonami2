# 删表
# drop table t_user;

# 建表操作
create table t_user(
	id varchar(50) primary key,
	name varchar(20) not null,
	password varchar(20) not null
);