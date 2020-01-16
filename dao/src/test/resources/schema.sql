create table tags
(
id int(10) not null,
name varchar(30),
primary key (id)
);

CREATE TABLE certificates
(
    id int(10) not null,
    name varchar(30) not null,
    description VARCHAR(30) not null,
    price int(10) not null,
    create_date date,
    last_update_date date,
    duration int(10) not null,
    tag_id int(10),
    primary key (id),
    foreign key (tag_id) references tags (id) on delete cascade
);



