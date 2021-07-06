CREATE DATABASE IF NOT EXISTS IncomeManagement;
USE IncomeManagement;

CREATE TABLE IF NOT EXISTS UserAccount
(
    id              int NOT NULL AUTO_INCREMENT,
    userAccountName VARCHAR(255),
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS Category
(
    id           int NOT NULL AUTO_INCREMENT,
    categoryName VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Transaction
(
    id               int         NOT NULL AUTO_INCREMENT,
    transactionName  VARCHAR(255),
    transDate        DATE        NOT NULL,

    transValue      FLOAT(9, 2) NOT NULL,

    userAccountInId  int,
    userAccountOutId int,

    categoryId       int         NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (userAccountInId) REFERENCES UserAccount (id),
    FOREIGN KEY (userAccountOutId) REFERENCES UserAccount (id),
    FOREIGN KEY (categoryId) REFERENCES Category (id),

    # No mínimo uma conta.
    CONSTRAINT MinimunAcc CHECK (userAccountInId IS NOT NULL or userAccountOutId IS NOT NULL)
);

CREATE VIEW ACCOUNT_DESPESAS as
SELECT ua.id              as ACCID,
       ua.userAccountName AS ACCNAME,
       tr.transValue     AS VALOR_SAIDA
FROM UserAccount ua
         LEFT JOIN Transaction tr
                   on tr.userAccountOutId = ua.id;


create view ACCOUNT_ENTRADAS as
SELECT ua.id              as ACCID,
       ua.userAccountName AS ACCNAME,
       tr.transValue     AS VALOR_ENTRADA
FROM UserAccount ua
         LEFT JOIN Transaction tr
                   on tr.userAccountInId = ua.id;