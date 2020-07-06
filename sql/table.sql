
--角色表T_ROLE
create table T_ROLE
(
    ROLE_ID   NUMBER primary key ,		--角色编号，主键序列
    ROLE_NAME VARCHAR2(20) not null,	--角色名称
    ROLE_DESC VARCHAR2(30) not null	--角色描述
);

create sequence seq_T_ROLE_prm;
--为主键绑定序列自增触发器
create or replace trigger tri_seq_T_ROLE_prm
    before insert on T_ROLE
    for each row
begin
    select seq_T_ROLE_prm.nextval into:new.ROLE_ID from DUAL;
end;

--员工表T_EMPLOYEE
create table T_EMPLOYEE
(
    EMPLOYEE_ID   NUMBER primary key ,		--员工编号，主键序列
    EMPLOYEE_NAME VARCHAR2(20) not null,	--用户名称，
    PASSWORD      VARCHAR2(10) not null,		--密码，
    REAL_NAME     VARCHAR2(20) not null,		--真实姓名，
    SEX           VARCHAR2(4) not null,		--性别
    BIRTHDAY      DATE,				--出生年月
    DUTY          VARCHAR2(30) not null,		--职位信息
    ENROLLDATE    DATE not null,			--入职时间
    EDUCATION     VARCHAR2(30) not null,		--学历信息
    MAJOR         VARCHAR2(30) not null,		--专业信息
    EXPERIENCE    VARCHAR2(30) not null,		--行业经验
    ROLE_ID       NUMBER references T_ROLE(ROLE_ID) ,			--外键，所属角色，引用角色编号
    PARENT_ID     NUMBER 			--外键，主管，引用员工编号
);


create sequence seq_T_EMPLOYEE;
--为主键绑定序列自增触发器
create or replace trigger tri_seq_T_EMPLOYEE
    before insert on T_EMPLOYEE
    for each row
begin
    select seq_T_EMPLOYEE.nextval into:new.EMPLOYEE_ID from DUAL;
end;

--任务表T_TASK
create table T_TASK
(
    TASK_ID         NUMBER primary key ,		--任务编号，主键序列
    TASK_NAME       VARCHAR2(50),		--任务名称
    BEGIN_DATE      DATE,			--开始时间
    END_DATE        DATE,			--结束时间
    REAL_BEGIN_DATE DATE,			--实际开始时间
    REAL_END_DATE   DATE,			--实际结束时间
    STATUS          VARCHAR2(10),		--任务状态
    IMPLEMENTOR_ID  NUMBER references T_EMPLOYEE(EMPLOYEE_ID),	--外键，实施人编号，引用员工编号
    ASSIGNER_ID     NUMBER references T_EMPLOYEE(EMPLOYEE_ID),		--外键，分配人编号，引用员工编号
    TASK_DESC       VARCHAR2(100)		--任务描述
);

create sequence seq_T_TASK;
--为主键绑定序列自增触发器
create or replace trigger tri_seq_T_TASK
    before insert on T_TASK
    for each row
begin
    select seq_T_TASK.nextval into:new.TASK_ID from DUAL;
end;

--计划表T_PLAN
create table T_PLAN
(
    PLAN_ID       NUMBER primary key ,		--计划编号，主键序列
    PLAN_NAME     VARCHAR2(50),	--计划名称
    STATUS        VARCHAR2(10),		--计划状态
    IS_FEEDBACK   VARCHAR2(5),	--是否反馈
    BEGIN_DATE    DATE,			--开始时间
    END_DATE      DATE,			--结束时间
    TASK_ID       NUMBER references T_TASK(TASK_ID) ,	--外键，所属任务，引用任务编号
    FEEDBACK_INFO VARCHAR2(100),	--反馈信息
    PLAN_DESC     VARCHAR2(100)		--计划描述
);

create sequence seq_T_PLAN;
--为主键绑定序列自增触发器
create or replace trigger tri_seq_T_PLAN
    before insert on T_PLAN
    for each row
begin
    select seq_T_PLAN.nextval into:new.PLAN_ID from DUAL;
end;

select * from T_ROLE;
select * from T_EMPLOYEE;
select * from T_TASK;
select * from T_PLAN;

insert into T_ROLE (role_id, role_name, role_desc)
                       values (1, '系统管理员', '系统管理员');
insert into T_ROLE (role_id, role_name, role_desc)
values (2, '主管', '主管');
insert into T_ROLE (role_id, role_name, role_desc)
values (3, '员工', '员工');


insert into T_EMPLOYEE (employee_id, employee_name, password, real_name, sex, birthday, duty, enrolldate, education, major, experience, role_id, parent_id)
values (1, 'zhangsan', '123456', '张三', '男', to_date('12-09-1998', 'dd-mm-yyyy'), '初级工程师', to_date('12-01-2008', 'dd-mm-yyyy'), '大学', '计算机', '2年', 3, 2);
insert into T_EMPLOYEE (employee_id, employee_name, password, real_name, sex, birthday, duty, enrolldate, education, major, experience, role_id, parent_id)
values (2, 'lisi', '123456', '李四', '女', to_date('12-05-1999', 'dd-mm-yyyy'), '高级工程师', to_date('12-04-2006', 'dd-mm-yyyy'), '大学', '计算机', '4年', 2, null);
insert into T_EMPLOYEE (employee_id, employee_name, password, real_name, sex, birthday, duty, enrolldate, education, major, experience, role_id, parent_id)
values (3, 'zhaoliu', '123456', '赵六', '女', to_date('12-05-2000', 'dd-mm-yyyy'), '初级工程师', to_date('12-05-2009', 'dd-mm-yyyy'), '大学', '计算机', '2年', 1, 2);
insert into T_EMPLOYEE (employee_id, employee_name, password, real_name, sex, birthday, duty, enrolldate, education, major, experience, role_id, parent_id)
values (4, 'wangwu', '123456', '王五', '男', to_date('12-08-1999', 'dd-mm-yyyy'), '中级工程师', to_date('18-04-2008', 'dd-mm-yyyy'), '大学', '计算机', '5年', 3, 5);
insert into T_EMPLOYEE (employee_id, employee_name, password, real_name, sex, birthday, duty, enrolldate, education, major, experience, role_id, parent_id)
values (5, 'tianqi', '123456', '田七', '男', to_date('15-03-2000', 'dd-mm-yyyy'), '高级工程师', to_date('17-06-2007', 'dd-mm-yyyy'), '大学', '计算机', '4年', 2, null);
insert into T_EMPLOYEE (employee_id, employee_name, password, real_name, sex, birthday, duty, enrolldate, education, major, experience, role_id, parent_id)
values (6, 'xiaohong', '123456', '小红', '女', to_date('18-07-2000', 'dd-mm-yyyy'), '初级工程师', to_date('27-09-2007', 'dd-mm-yyyy'), '大学', '计算机', '2年', 3, 2);


insert into T_TASK (task_id, task_name, begin_date, end_date, real_begin_date, real_end_date, status, implementor_id, assigner_id, task_desc)
values (1, '任务1', to_date('23-01-2012', 'dd-mm-yyyy'), to_date('13-12-2014', 'dd-mm-yyyy'), to_date('27-01-2012', 'dd-mm-yyyy'), to_date('01-01-2015', 'dd-mm-yyyy'), '已完成', 6, 2, null);
insert into T_TASK (task_id, task_name, begin_date, end_date, real_begin_date, real_end_date, status, implementor_id, assigner_id, task_desc)
values (2, '任务2', to_date('23-06-2012', 'dd-mm-yyyy'), to_date('23-10-2014', 'dd-mm-yyyy'), to_date('29-06-2012', 'dd-mm-yyyy'), null, '实施中', 6, 2, null);
insert into T_TASK (task_id, task_name, begin_date, end_date, real_begin_date, real_end_date, status, implementor_id, assigner_id, task_desc)
values (3, '任务3', to_date('23-06-2014', 'dd-mm-yyyy'), to_date('23-10-2015', 'dd-mm-yyyy'), null, null, '待实施', 1, 2, null);
insert into T_TASK (task_id, task_name, begin_date, end_date, real_begin_date, real_end_date, status, implementor_id, assigner_id, task_desc)
values (4, '任务4', to_date('24-06-2014', 'dd-mm-yyyy'), to_date('23-09-2015', 'dd-mm-yyyy'), null, null, '待实施', 1, 2, null);
insert into T_TASK (task_id, task_name, begin_date, end_date, real_begin_date, real_end_date, status, implementor_id, assigner_id, task_desc)
values (5, '任务5', to_date('14-03-2013', 'dd-mm-yyyy'), to_date('11-09-2013', 'dd-mm-yyyy'), to_date('15-03-2013', 'dd-mm-yyyy'), to_date('01-08-2013', 'dd-mm-yyyy'), '已完成', 4, 5, null);
insert into T_TASK (task_id, task_name, begin_date, end_date, real_begin_date, real_end_date, status, implementor_id, assigner_id, task_desc)
values (6, '任务6', to_date('14-03-2013', 'dd-mm-yyyy'), to_date('13-10-2015', 'dd-mm-yyyy'), to_date('14-04-2013', 'dd-mm-yyyy'), null, '实施中', 4, 5, null);
insert into T_TASK (task_id, task_name, begin_date, end_date, real_begin_date, real_end_date, status, implementor_id, assigner_id, task_desc)
values (7, '任务7', to_date('18-04-2014', 'dd-mm-yyyy'), to_date('12-12-2015', 'dd-mm-yyyy'), null, null, '待实施', 6, 2, null);



insert into T_PLAN (plan_id, plan_name, status, is_feedback, begin_date, end_date, task_id, feedback_info, plan_desc)
values (1, '计划1_1', '已完成', '是', to_date('27-01-2012', 'dd-mm-yyyy'), to_date('30-09-2012', 'dd-mm-yyyy'), 1, '计划1_1顺利完成', null);
insert into T_PLAN (plan_id, plan_name, status, is_feedback, begin_date, end_date, task_id, feedback_info, plan_desc)
values (2, '计划1_2', '已完成', '是', to_date('03-10-2012', 'dd-mm-yyyy'), to_date('01-01-2013', 'dd-mm-yyyy'), 1, '计划1_2顺利完成', null);
insert into T_PLAN (plan_id, plan_name, status, is_feedback, begin_date, end_date, task_id, feedback_info, plan_desc)
values (3, '计划1_3', '已完成', '是', to_date('01-02-2013', 'dd-mm-yyyy'), to_date('01-01-2015', 'dd-mm-yyyy'), 1, '计划1_3顺利完成', null);
insert into T_PLAN (plan_id, plan_name, status, is_feedback, begin_date, end_date, task_id, feedback_info, plan_desc)
values (4, '计划5_1', '已完成', '是', to_date('15-03-2013', 'dd-mm-yyyy'), to_date('27-05-2013', 'dd-mm-yyyy'), 5, '计划5_1顺利完成', null);
insert into T_PLAN (plan_id, plan_name, status, is_feedback, begin_date, end_date, task_id, feedback_info, plan_desc)
values (5, '计划5_2', '已完成', '是', to_date('28-05-2013', 'dd-mm-yyyy'), to_date('01-08-2013', 'dd-mm-yyyy'), 5, '计划5_2顺利完成', null);
insert into T_PLAN (plan_id, plan_name, status, is_feedback, begin_date, end_date, task_id, feedback_info, plan_desc)
values (6, '计划2_1', '已完成', '是', to_date('30-06-2012', 'dd-mm-yyyy'), to_date('01-04-2013', 'dd-mm-yyyy'), 2, '计划2_1顺利完成', null);
insert into T_PLAN (plan_id, plan_name, status, is_feedback, begin_date, end_date, task_id, feedback_info, plan_desc)
values (7, '计划2_2', '待实施', '否', null, null, 2, null, null);
insert into T_PLAN (plan_id, plan_name, status, is_feedback, begin_date, end_date, task_id, feedback_info, plan_desc)
values (8, '计划2_3', '待实施', '否', null, null, 2, null, null);
insert into T_PLAN (plan_id, plan_name, status, is_feedback, begin_date, end_date, task_id, feedback_info, plan_desc)
values (9, '计划6_1', '待实施', '否', null, null, 2, null, null);


--增加员工状态列
alter table T_EMPLOYEE add (workState number default (1))
--1在职  0离职


