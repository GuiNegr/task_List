ALTER TABLE log
    ADD COLUMN id_task BIGINT;

ALTER TABLE log
    ADD CONSTRAINT fk_log_task
        FOREIGN KEY (id_task)
            REFERENCES task(id);