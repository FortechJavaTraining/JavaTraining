create table  DEPARTMENT
(
    ID bigint not null constraint PK_DEPARTMENT primary key,
    NAME varchar (255)
);
CREATE SEQUENCE S_DEPARTMENT
START WITH 1
INCREMENT BY 1;