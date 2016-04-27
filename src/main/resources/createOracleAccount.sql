create temporary tablespace jison_temp tempfile 'C:\APP\ADMINISTRATOR\ORADATA\ORCL\jison_temp.dbf' size 64m autoextend
on next 64m maxsize 2048m extent management local;

create tablespace jison_blog logging datafile 'C:\APP\ADMINISTRATOR\ORADATA\ORCL\jison_blog.dbf' size 64m autoextend
 on next 64m maxsize 2048m extent management local;
 
 create user jison identified by jison default tablespace jison_blog temporary tablespace jison_temp;
 
grant connect, resource, unlimited tablespace to jison; 