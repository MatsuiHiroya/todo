create table CREDIT_STATUS
(
    ID              VARCHAR(8) not null
        references ACCOUNT (ID)
            on update cascade,
    LECTURE_INFO_ID VARCHAR(8) not null
        references LECTURE_INFO (ID)
            on update cascade,
    primary key (ID, LECTURE_INFO_ID)
);

