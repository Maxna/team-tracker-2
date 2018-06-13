SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS teams (
    id int PRIMARY KEY auto_increment,
    description VARCHAR,
    team VARCHAR,
);

CREATE TABLE IF NOT EXISTS members (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    teamId INTEGER
);