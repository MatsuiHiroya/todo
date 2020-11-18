create table NOTICE
(
    ID          VARCHAR(8)  not null
        primary key,
    ACCOUNT_ID  VARCHAR(8)  not null
        references ACCOUNT (ID)
            on update cascade,
    TODO_CONFIG VARCHAR(16) not null,
    DUE_DATE    TIMESTAMP   not null
);

