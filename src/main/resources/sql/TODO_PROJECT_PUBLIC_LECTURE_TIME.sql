create table LECTURE_TIME
(
    ID              VARCHAR(8) not null
        primary key,
    LECTURE_INFO_ID VARCHAR(8) not null
        references LECTURE_INFO (ID)
            on update cascade,
    START_DATE      DATE       not null,
    START_TIME      TIME       not null,
    START_WEEK      VARCHAR(8) not null,
    TIMES           VARCHAR(4) not null,
    LECTURE_INFO    VARCHAR(8) not null
);

