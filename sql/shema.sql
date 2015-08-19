-- create table
CREATE TABLE client (
  uid        SERIAL PRIMARY KEY,
  nick       VARCHAR(200),
  last_name  VARCHAR(200),
  address_id INT NOT NULL REFERENCES address (uid),
  sex_id     INT NOT NULL REFERENCES sex (uid),
  pet_id     INT NOT NULL REFERENCES pet (uid)
);
CREATE TABLE address (
  uid       SERIAL PRIMARY KEY,
  city      VARCHAR(200),
  street    VARCHAR(300),
  house     INT NOT NULL,
  apartment INT
);
CREATE TABLE pet (
  uid       SERIAL PRIMARY KEY,
  client_id INT NOT NULL REFERENCES client (uid),
  nick      VARCHAR(200),
  class_id  INT NOT NULL REFERENCES class (uid),
  sex_id    INT NOT NULL REFERENCES sex (uid)
);
CREATE TABLE sex (
  uid    SERIAL PRIMARY KEY,
  gender CHAR(10)
);
CREATE TABLE class (
  uid  SERIAL PRIMARY KEY,
  type VARCHAR(200)
);

-- add new client
INSERT INTO client (nick) VALUES ('Alexey');

-- add new pet
INSERT INTO pet (client_id, nick) VALUES (1, 'sparky');
INSERT INTO pet (client_id, nick) VALUES (2, 'boby');

-- select pet with client
SELECT
  pet.nick,
  client.nick
FROM pet AS pet
  INNER JOIN client AS client ON client.uid = pet.client_id;

-- select client by nick
SELECT *
FROM client AS client
WHERE client.nick = 'Alexey';

-- update pet
UPDATE pet AS pet
SET nick = 'bob'
WHERE pet.nick = 'boby';

-- delete pet by nick
DELETE FROM pet AS pet
WHERE pet.nick = 'bob'