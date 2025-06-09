drop table if exists score;
create table score (
    player varchar(64) not null,
    game varchar(64) not null,
    points integer not null,
    playedOn timestamp not null
);

drop table if exists comment;
create table comment (
    game varchar(64) not null,
    player varchar(64) not null,
    comment varchar(255) not null,
    "commentedOn" timestamp not null
);
