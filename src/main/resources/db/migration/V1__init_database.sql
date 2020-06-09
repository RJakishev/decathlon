CREATE SCHEMA decathlon;

DROP TABLE IF EXISTS event;

CREATE TABLE IF NOT EXISTS event (
    id INT PRIMARY KEY,
    name varchar(100) NULL,
    a DOUBLE NOT NULL,
    b DOUBLE NOT NULL,
    c DOUBLE NOT NULL
);

DROP TABLE IF EXISTS user;

CREATE TABLE IF NOT EXISTS user (
    id INT PRIMARY KEY,
    firstname varchar(50) NULL,
    lastname varchar(50) NULL
);

DROP TABLE IF EXISTS coefficient;

CREATE TABLE IF NOT EXISTS coefficient (
    id INT PRIMARY KEY,
    user_id INT NOT NULL REFERENCES user(id)
);

CREATE TABLE IF NOT EXISTS result (
    id INT PRIMARY KEY,
    event_id INT NOT NULL REFERENCES event(id),
    points DOUBLE NOT NULL,
    user_id INT NOT NULL REFERENCES user(id)
);
