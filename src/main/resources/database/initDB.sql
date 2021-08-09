CREATE TABLE IF NOT EXISTS UsersScore
(
    id    SERIAL PRIMARY KEY ,
    name  VARCHAR(200) NOT NULL ,
    score integer NOT NULL
);
CREATE TABLE IF NOT EXISTS UsersList
(
    id    SERIAL PRIMARY KEY ,
    name  VARCHAR(200) unique NOT NULL ,
    password VARCHAR(200) NOT NULL ,
    role VARCHAR(20) NOT NULL ,
    status VARCHAR(20) NOT NULL
);