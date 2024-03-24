create table if not exists book (
    id serial primary key,
    title varchar(100),
    quantity int
);

create table if not exists transaction_order (
    id uuid primary key ,
    username varchar(100),
    book_id int references book(id),
    created_date timestamp
);