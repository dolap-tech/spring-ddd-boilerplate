create table product
(
    id bigint not null
        constraint pk_product_id
            primary key,
    title varchar(50) not null,
    description varchar(200) not null,
    price numeric(19,2) not null,
    owner_id bigint not null,
    status int not null,
    created_date timestamp(6) not null
);

create index idx_product_owner_id
	on product (owner_id);

create sequence seq_product;
