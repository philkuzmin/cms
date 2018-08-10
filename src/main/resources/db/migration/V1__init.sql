CREATE TABLE users (
    id bigserial primary key,
    login varchar(50) unique not null,
    password varchar(100) not null
);

CREATE TABLE posts (
    id bigserial primary key,
    user_id bigint references users(id),
    header varchar(100),
    content text
);