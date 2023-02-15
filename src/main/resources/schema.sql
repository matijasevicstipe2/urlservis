create table if not exists accounts(
    id identity,
    account_id varchar(20) not null unique,
    password varchar(1000) not null,
    user_role varchar(100) not null,
    primary key(id)
);

create table if not exists url(
    id long generated always as identity,
    account_id long not null,
    long_url varchar(500) not null,
    redirect_type integer not null,
    created_date date not null,
    number_of_clicks long not null,
    primary key (id),
    foreign key (account_id) references accounts(id)
);
