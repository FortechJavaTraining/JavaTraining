create table  TEAM
(
    ID bigint not null constraint PK_TEAM primary key,
    NAME varchar (255),
    EXTERNAL_ID bigint not null,
    TEAM_LEAD bigint null constraint FK_EMPLOYEE_TEAM_ID references EMPLOYEE(ID)
);
CREATE SEQUENCE S_TEAM
    START WITH 1
    INCREMENT BY 1;

-- DROP TABLE TEAM
-- DROP SEQUENCE S_TEAM

--  ALTER TABLE flyway_schema_history
-- DROP COLUMN where version = 1.5;