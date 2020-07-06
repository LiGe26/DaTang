<<<<<<< HEAD
=======
/**
先登录system用户
 */

>>>>>>> 38bb1f1... dao
--创建表空间
create tablespace datang
    datafile 'E:\tablespace\datang.ora'
    size 50m
    autoextend on;
--临时表空间
create temporary tablespace datangTemp
tempfile 'E:\tablespace\datangTemp.ora'
size 10m
autoextend on;

--创建用户
create user datang identified by 123;

--为用户制定表空间和临时表空间
alter user datang default tablespace datang;
alter user datang temporary tablespace datangTemp;
--为用户授权
Grant connect,resource,create session,
    dba, create table, create view, create trigger,
    select any table, create sequence, create procedure, create role,
    grant any privilege, drop any role,
    create public synonym, drop public synonym,SELECT ANY DICTIONARY to datang;