drop table if exists todolist;

CREATE TABLE todolist
(
    id INT(8) unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(2048),
    done TINYINT(1) DEFAULT '0' NOT NULL
);
CREATE UNIQUE INDEX id ON todolist (id);

