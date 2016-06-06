# 删表
# drop table t_user;

# 建表操作
create table t_user(
	id varchar(50) primary key,
	name varchar(20) not null,
	password varchar(20) not null
);

# 往t_user表中层插入一个用户jisonami
insert into t_user(id, name, password) values((select sys_guid() from dual),'jisonami','123456');