--liquibase formatted sql

--changeset Danila Rudenko:2_1
INSERT INTO anime (name)
VALUES ('Haikyuu!! Second Season');

--changeset Danila Rudenko:2_2
INSERT INTO hero (name, anime_id)
VALUES ('Ikkei Ukai', (SELECT id FROM anime WHERE name = 'Haikyuu!! Second Season'));

--changeset Danila Rudenko:2_3
INSERT INTO quote (content, hero_id)
VALUES ('There''s no match you can''t win, and there''s no match that you''ll win for sure.', (SELECT id FROM hero WHERE name = 'Ikkei Ukai'));

--changeset Danila Rudenko:2_4
INSERT INTO anime (name)
VALUES ('Naruto');

--changeset Danila Rudenko:2_5
INSERT INTO hero (name, anime_id)
VALUES ('Naruto Uzumaki', (SELECT id FROM anime WHERE name = 'Naruto'));

--changeset Danila Rudenko:2_6
INSERT INTO quote (content, hero_id)
VALUES ('I never go back on my word. That''s my ninja way.', (SELECT id FROM hero WHERE name = 'Naruto Uzumaki'));

--changeset Danila Rudenko:2_7
INSERT INTO anime (name)
VALUES ('One Piece');

--changeset Danila Rudenko:2_8
INSERT INTO hero (name, anime_id)
VALUES ('Monkey D. Luffy', (SELECT id FROM anime WHERE name = 'One Piece'));

--changeset Danila Rudenko:2_9
INSERT INTO quote (content, hero_id)
VALUES ('I''m going to be the Pirate King!', (SELECT id FROM hero WHERE name = 'Monkey D. Luffy'));

--changeset Danila Rudenko:2_10
INSERT INTO anime (name)
VALUES ('Attack on Titan');

--changeset Danila Rudenko:2_11
INSERT INTO hero (name, anime_id)
VALUES ('Eren Yeager', (SELECT id FROM anime WHERE name = 'Attack on Titan'));

--changeset Danila Rudenko:2_12
INSERT INTO quote (content, hero_id)
VALUES ('If you win, you live. If you lose, you die. If you don''t fight, you can''t win!', (SELECT id FROM hero WHERE name = 'Eren Yeager'));

--changeset Danila Rudenko:2_13
INSERT INTO anime (name)
VALUES ('Fullmetal Alchemist: Brotherhood');

--changeset Danila Rudenko:2_14
INSERT INTO hero (name, anime_id)
VALUES ('Edward Elric', (SELECT id FROM anime WHERE name = 'Fullmetal Alchemist: Brotherhood'));

--changeset Danila Rudenko:2_15
INSERT INTO quote (content, hero_id)
VALUES ('A lesson without pain is meaningless. That''s because no one can gain without sacrificing something.', (SELECT id FROM hero WHERE name = 'Edward Elric'));

--changeset Danila Rudenko:2_16
INSERT INTO anime (name)
VALUES ('Death Note');

--changeset Danila Rudenko:2_17
INSERT INTO hero (name, anime_id)
VALUES ('Light Yagami', (SELECT id FROM anime WHERE name = 'Death Note'));

--changeset Danila Rudenko:2_18
INSERT INTO quote (content, hero_id)
VALUES ('I am Justice! I protect the innocent and those who fear evil. I''m the one who will become the god of a new world.', (SELECT id FROM hero WHERE name = 'Light Yagami'));

--changeset Danila Rudenko:2_19
INSERT INTO anime (name)
VALUES ('Dragon Ball Z');

--changeset Danila Rudenko:2_20
INSERT INTO hero (name, anime_id)
VALUES ('Goku', (SELECT id FROM anime WHERE name = 'Dragon Ball Z'));

--changeset Danila Rudenko:2_21
INSERT INTO quote (content, hero_id)
VALUES ('I am the hope of the universe. I am the answer to all living things that cry out for peace.', (SELECT id FROM hero WHERE name = 'Goku'));

--changeset Danila Rudenko:2_22
INSERT INTO anime (name)
VALUES ('Sword Art Online');

--changeset Danila Rudenko:2_23
INSERT INTO hero (name, anime_id)
VALUES ('Kirito', (SELECT id FROM anime WHERE name = 'Sword Art Online'));

--changeset Danila Rudenko:2_24
INSERT INTO quote (content, hero_id)
VALUES ('I''d rather trust and regret than doubt and regret.', (SELECT id FROM hero WHERE name = 'Kirito'));

--changeset Danila Rudenko:2_25
INSERT INTO anime (name)
VALUES ('Tokyo Ghoul');

--changeset Danila Rudenko:2_26
INSERT INTO hero (name, anime_id)
VALUES ('Ken Kaneki', (SELECT id FROM anime WHERE name = 'Tokyo Ghoul'));

--changeset Danila Rudenko:2_27
INSERT INTO quote (content, hero_id)
VALUES ('I''m not the protagonist of a novel or anything... I''m just a college student who likes to read, like you could find anywhere. But... if, for argument''s sake, you were to write a story with me in the lead role, it would certainly be... a tragedy.', (SELECT id FROM hero WHERE name = 'Ken Kaneki'));
