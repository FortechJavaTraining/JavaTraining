ALTER TABLE EMPLOYEE ADD COLUMN TEAM_ID bigint null constraint FK_EMPLOYEE_TEAM_ID references TEAM(ID);

-- ALTER TABLE EMPLOYEE
-- DROP COLUMN TEAM_ID;

--  ALTER TABLE flyway_schema_history
-- DROP COLUMN where version = 1.7;