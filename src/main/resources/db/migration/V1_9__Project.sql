create table  PROJECT
(
    ID bigint not null constraint PK_PROJECT primary key,
    NAME varchar (101) unique not NULL ,
    EXTERNAL_ID varchar(255) unique not NULL
);
CREATE SEQUENCE S_PROJECT
    START WITH 1
    INCREMENT BY 1;