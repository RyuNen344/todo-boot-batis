drop table if EXISTS todo;

CREATE TABLE todo (
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    title TEXT NOT NULL,
    detail TEXT,
    status TEXT NOT NULL
);