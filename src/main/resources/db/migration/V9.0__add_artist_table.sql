
CREATE TABLE artist(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    genre VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE concert
ADD COLUMN artist_id BIGINT NOT NULL,
ADD COLUMN date DATE DEFAULT NULL,
DROP COLUMN artist,
DROP COLUMN genre,
ADD FOREIGN KEY (artist_id) REFERENCES artist(id);


CREATE TABLE audit_trail(
    id BIGINT NOT NULL AUTO_INCREMENT,
    sale_id BIGINT,
    account_id BIGINT NOT NULL,
    CONSTRAINT pk_audit_trail PRIMARY KEY(id),
    CONSTRAINT fk_audit_account_id FOREIGN KEY (account_id) REFERENCES account(id),
    CONSTRAINT fk_audit_sale_id FOREIGN KEY(sale_id) REFERENCES sale(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);