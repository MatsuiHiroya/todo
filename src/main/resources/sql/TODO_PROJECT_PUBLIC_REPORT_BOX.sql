create table REPORT_BOX
(
    ID              VARCHAR(8)   not null
        primary key,
    LECTURE_TIME_ID VARCHAR(8)   not null
        references LECTURE_TIME (ID)
            on update cascade,
    BOX_NAME        VARCHAR(100) not null,
    LIMIT_TIME      TIMESTAMP    not null,
    START_TIME      TIMESTAMP    not null,
    CONTENT         VARCHAR(500) not null
);

