SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS teams (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    description VARCHAR,
    completed BOOLEAN
);

CREATE TABLE IF NOT EXISTS members (
    ind int PRIMARY KEY auto_increment,
    name VARCHAR,
    teamId INTEGER
);