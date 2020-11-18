create table ACCOUNT
(
    ID         VARCHAR(8)  not null
        primary key,
    NAME       VARCHAR(32) not null,
    ROLL       VARCHAR(4)  not null,
    GRADE      VARCHAR(4)  not null,
    CLASS      VARCHAR(4)  not null,
    DEPARTMENT VARCHAR(16) not null,
    PASSWORD   VARCHAR(32) not null
);

