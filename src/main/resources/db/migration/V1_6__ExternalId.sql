ALTER TABLE EMPLOYEE
    ADD EXTERNAL_ID varchar (255);

CREATE FUNCTION external_id_insert() RETURNS TRIGGER AS $$
BEGIN
    new.EXTERNAL_ID=new.id || '_' || new.NAME;
    return new;
end
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_employee_insert
    BEFORE INSERT OR UPDATE
                         ON EMPLOYEE FOR EACH ROW
                         EXECUTE PROCEDURE external_id_insert();