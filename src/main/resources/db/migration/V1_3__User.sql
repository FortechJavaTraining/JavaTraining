create table USERS
(
    ID bigint not null constraint PK_USER primary key,
    USER_NAME varchar (255) constraint U_USER_NAME unique,
    PASSWORD varchar (255)
);
CREATE SEQUENCE S_USER
START WITH 1
INCREMENT BY 1;