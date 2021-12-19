create table aircraft
(
    id bigserial not null unique,
    producer varchar,
    model varchar,
    primary key (id)
);
