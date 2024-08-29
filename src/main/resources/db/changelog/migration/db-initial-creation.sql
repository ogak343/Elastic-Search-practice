create table category(
    id bigserial primary key,
    name varchar,
    description varchar
);

create table product(
    id bigserial primary key,
    name varchar not null ,
    description varchar,
    category_id bigint not null references category(id),
    price bigint not null,
    quantity int not null
);