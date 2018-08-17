DROP DATABASE IF EXISTS vdb;

CREATE DATABASE IF NOT EXISTS vdb DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

use vdb;


CREATE TABLE IF NOT EXISTS SetupOutput_H_5(
  i int not null,
  j int not null,
  H_ij blob,
  primary key(i,j)
);


CREATE TABLE IF NOT EXISTS SetupOutput_H_500(
  i int not null,
  j int not null,
  H_ij blob,
  primary key(i,j)
);


CREATE TABLE IF NOT EXISTS SetupOutput_H_6549(
  i int not null,
  j int not null,
  H_ij blob,
  primary key(i,j)
); 