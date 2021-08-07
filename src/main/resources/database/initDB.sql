CREATE TABLE IF NOT EXISTS UsersScore
(
    id    SERIAL PRIMARY KEY ,
    name  VARCHAR(200) NOT NULL ,
    score integer NOT NULL
);