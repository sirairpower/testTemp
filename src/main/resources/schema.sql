SET MODE MYSQL;

DROP TABLE IF EXISTS tmp;
CREATE TABLE tmp
(
    key VARCHAR (50) NOT NULL,
    value VARCHAR(200) DEFAULT NULL,
    desc  VARCHAR(200) DEFAULT NULL,
    PRIMARY KEY (key)
);

DROP TABLE IF EXISTS member;
CREATE TABLE member
(
    id       VARCHAR(50)  NOT NULL,
    email    VARCHAR(200) NOT NULL,
    password VARCHAR(200) NOT NULL,
    nickname VARCHAR(200) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_email (email)
);
