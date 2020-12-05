create table NOTICE
(
    ID          BIGINT auto_increment  not null primary key,
    ACCOUNT_ID  VARCHAR(8)  not null    references ACCOUNT (ID) on update cascade,
    TODO_CONFIG BOOLEAN not null default true,
    DUE_DATE    INTEGER not null default 7
);

