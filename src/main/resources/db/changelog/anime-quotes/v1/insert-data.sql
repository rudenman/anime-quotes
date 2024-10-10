--liquibase formatted sql

--changeset Danila Rudenko:2_1
INSERT INTO anime (name)
VALUES ('Haikyuu!! Second Season');

--changeset Danila Rudenko:2_2
INSERT INTO hero (name, anime_id)
VALUES ('Ikkei Ukai', 1);

--changeset Danila Rudenko:2_3
INSERT INTO quote (content, hero_id)
VALUES ('There''s no match you can''t win, and there''s no match that you''ll win for sure.', 1);