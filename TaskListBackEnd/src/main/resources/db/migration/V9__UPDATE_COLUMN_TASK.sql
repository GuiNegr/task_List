ALTER TABLE card_model
    ADD CONSTRAINT id
        PRIMARY KEY (id);


ALTER TABLE task
        ADD COLUMN id_card BIGINT;

ALTER TABLE task
    ADD CONSTRAINT fk_id_card
        FOREIGN KEY (id_card)
            references card_model(id);