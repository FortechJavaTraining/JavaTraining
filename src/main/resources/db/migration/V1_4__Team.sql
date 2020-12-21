create table  TEAM
(
    ID bigint not null constraint PK_TEAM primary key,
    NAME varchar (255),
    EXTERNAL_ID bigint not null,
    TEAM_LEAD bigint not null
);
CREATE SEQUENCE S_TEAM
    START WITH 1
    INCREMENT BY 1;