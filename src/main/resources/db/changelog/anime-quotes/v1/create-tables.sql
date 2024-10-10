--liquibase formatted sql

--changeset Danila Rudenko:1_1
CREATE SEQUENCE IF NOT EXISTS anime_seq START 1 INCREMENT BY 50;

--changeset Danila Rudenko:1_2
CREATE TABLE IF NOT EXISTS anime
(
    id   INT PRIMARY KEY DEFAULT nextval('anime_seq'),
    name VARCHAR(255) NOT NULL UNIQUE
);

--changeset Danila Rudenko:1_3
CREATE SEQUENCE IF NOT EXISTS hero_seq START 1 INCREMENT BY 50;

--changeset Danila Rudenko:1_4
CREATE TABLE IF NOT EXISTS hero
(
    id       INT PRIMARY KEY DEFAULT nextval('hero_seq'),
    name     VARCHAR(255) NOT NULL,
    anime_id INT          NOT NULL
);

--changeset Danila Rudenko:1_5
CREATE SEQUENCE IF NOT EXISTS quote_seq START 1 INCREMENT BY 50;

--changeset Danila Rudenko:1_6
CREATE TABLE IF NOT EXISTS quote
(
    id           INT PRIMARY KEY DEFAULT nextval('quote_seq'),
    content      VARCHAR(5000) NOT NULL,
    hero_id INT           NOT NULL
);

--changeset Danila Rudenko:1_7
ALTER TABLE hero
    ADD CONSTRAINT hero_anime_fk
        FOREIGN KEY (anime_id)
            REFERENCES anime (id)
            ON DELETE CASCADE;

--changeset Danila Rudenko:1_8
ALTER TABLE quote
    ADD CONSTRAINT quote_hero_fk
        FOREIGN KEY (hero_id)
            REFERENCES hero (id)
            ON DELETE CASCADE;

--changeset Danila Rudenko:1_9 endDelimiter:/
CREATE OR REPLACE FUNCTION delete_hero_parent()
    RETURNS TRIGGER AS
$$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM hero WHERE anime_id = OLD.anime_id AND id != OLD.id) THEN
        DELETE FROM anime WHERE id = OLD.anime_id;
    END IF;
    RETURN OLD;
END;
$$
LANGUAGE 'plpgsql';
/


--changeset Danila Rudenko:1_10
CREATE OR REPLACE TRIGGER hero_parent_deleting_trigger
    AFTER DELETE
    ON hero
    FOR EACH ROW
    EXECUTE FUNCTION delete_hero_parent();

--changeset Danila Rudenko:1_11 endDelimiter:/
CREATE OR REPLACE FUNCTION delete_quote_parent()
    RETURNS TRIGGER AS
$$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM quote WHERE hero_id = OLD.hero_id AND id != OLD.id) THEN
        DELETE FROM hero WHERE id = OLD.hero_id;
    END IF;
    RETURN OLD;
END;
$$
LANGUAGE 'plpgsql';
/


--changeset Danila Rudenko:1_12
CREATE OR REPLACE TRIGGER quote_parent_deleting_trigger
    AFTER DELETE
    ON quote
    FOR EACH ROW
EXECUTE FUNCTION delete_quote_parent();