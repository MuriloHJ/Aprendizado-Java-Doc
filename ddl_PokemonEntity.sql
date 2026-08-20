CREATE TABLE pokemon_entity
(
    id          INT     NOT NULL,
    name        VARCHAR(255),
    description VARCHAR(255),
    state       SMALLINT,
    level       INT     NOT NULL,
    actual_ps   INT     NOT NULL,
    max_ps      INT     NOT NULL,
    shiny       BOOLEAN NOT NULL,
    type        SMALLINT,
    stage       INT     NOT NULL,
    CONSTRAINT pk_pokemonentity PRIMARY KEY (id)
);