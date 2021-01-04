ALTER TABLE TEAM ADD COLUMN PROJECT_ID bigint null constraint FK_PROJECT_TEAM_ID references PROJECT(ID);

-- ALTER TABLE EMPLOYEE
-- DROP COLUMN TEAM_ID;

--  ALTER TABLE flyway_schema_history
-- DROP COLUMN where version = 1.11;