create table EMPLOYEE
(
    ID bigint not null constraint PK_EMPLOYEE primary key,
    JOB varchar (255),
    NAME varchar (10),
    DEPARTMENT_ID bigint not null constraint FK_EMPLOYEE_DEPARTMENT_ID references DEPARTMENT(ID),
    TEAM_ID bigint not null constraint FK_EMPLOYEE_TEAM_ID references TEAM(ID)
);
CREATE SEQUENCE S_EMPLOYEE
START WITH 1
INCREMENT BY 1;