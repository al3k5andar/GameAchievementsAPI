create table if not exists Game(
    id varchar(255) not null,
    display_name varchar(100) not null,
    primary key (id)
);

create table if not exists Achievement(
    id varchar(255) not null,
    display_name varchar(100) not null,
    description text(500) default null,
    icon varchar(255) default null,
    display_order int not null,
    created_at timestamp not null,
    updated_at timestamp not null,
    game_id varchar(255) default null,
    primary key (id)
);

alter table Achievement add foreign key(game_id) references Game(id);