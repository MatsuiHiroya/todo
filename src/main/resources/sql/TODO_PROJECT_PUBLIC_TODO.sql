create table TODO
(
    ID           VARCHAR(8)   not null
        primary key,
    TODO_NAME    VARCHAR(100) not null,
    TODO_CONTENT VARCHAR(500) not null,
    LIMIT_TIME   TIMESTAMP    not null,
    ACCOUNT_ID   VARCHAR(8)   not null
        references ACCOUNT (ID)
            on update cascade,
    TYPE         VARCHAR(8)   not null
);

