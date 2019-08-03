drop table if EXISTS todo;

CREATE TABLE if NOT EXISTS todo (
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    title TEXT NOT NULL,
    detail TEXT,
    status TEXT NOT NULL
) DEFAULT CHARSET=utf8;

drop table if EXISTS account;

CREATE TABLE if NOT EXISTS account (
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    userName TEXT NOT NULL,
    password TEXT NOT NULL,
    isEnabled boolean NOT NULL,
    isAdmin boolean NOT NULL
) DEFAULT CHARSET=utf8;

