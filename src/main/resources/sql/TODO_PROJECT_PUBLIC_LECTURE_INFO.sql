create table LECTURE_INFO
(
    ID           VARCHAR(8)  not null
        primary key,
    LECTURE_NAME VARCHAR(16) not null,
    RESPONSIBLE  VARCHAR(32) not null,
    DEPARTMENT   VARCHAR(16) not null,
    GRADE        VARCHAR(4)  not null,
    SESSION      VARCHAR(4),
    TYPE         VARCHAR(4)
);

