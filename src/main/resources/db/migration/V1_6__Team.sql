ALTER TABLE TEAM ADD COLUMN TEAM_LEAD bigint null constraint FK_EMPLOYEE_TEAM_ID references EMPLOYEE(ID);