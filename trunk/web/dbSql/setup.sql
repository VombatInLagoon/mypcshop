/***********************************************************
* Create the database named murach and all of its tables
************************************************************/

DROP DATABASE IF EXISTS pcshop;

CREATE DATABASE pcshop;

USE pcshop;


CREATE TABLE COMPONENT(
	COMPONENT_ID INT NOT NULL AUTO_INCREMENT, 
	NAME VARCHAR(50),
	PRICE INT NOT NULL,
	STOCK_NUM INT NOT NULL,
	DESCRIPTION VARCHAR(200),
	PRIMARY KEY(COMPONENT_ID)
);

-- PRODUCT CONTAINS ALL PRODUCTS IN THE SHOP


CREATE TABLE PRODUCT(
	PRODUCT_ID INT  NOT NULL AUTO_INCREMENT,
	BRAND VARCHAR(30),
	DESCRIPTION VARCHAR(200),
	PRICE   DOUBLE,
        MB	INT ,
	CPU	INT ,
	RAM	INT,
	VGA	INT,
	MONITOR	INT,
	HDD	INT,	
	OPTIC	INT,
	PRIMARY KEY(PRODUCT_ID)
        
	
);

-- This is a junction table to support many-to-many relations between 
-- product and component tables

CREATE TABLE COMP_PROD(
    COMPONENT_ID int NOT NULL,
    PRODUCT_ID int NOT NULL,
    CONSTRAINT PK_COMP_PROD PRIMARY KEY
    (
        COMPONENT_ID,
        PRODUCT_ID
    ),
    FOREIGN KEY (COMPONENT_ID) REFERENCES COMPONENT (COMPONENT_ID),
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (PRODUCT_ID)
);
	




-- ONE ORDER MAY CONTAIN A LOT OF PRODUCTS

CREATE TABLE ORDERS(
	ORDER_ID INT NOT NULL AUTO_INCREMENT,
	BUYER_NAME VARCHAR(100),
	SHIPPING_ADRESS VARCHAR(100),
	SHIPPING_ZIPCODE VARCHAR(10),
	SHIPPING_CITY VARCHAR(30),
	PRIMARY KEY(ORDER_ID)
);

-- EACH DISTINCT ORDER ITEMS	


CREATE TABLE ORDER_ITEMS(
	ORDER_ITEM_ID INT NOT NULL AUTO_INCREMENT,
	ORDER_ID INT,
	PRODUCT_ID INT,
	QUANTITY INT,
	PRIMARY KEY(ORDER_ITEM_ID),
	FOREIGN KEY(ORDER_ID) REFERENCES ORDERS(ORDER_ID),
	FOREIGN KEY(PRODUCT_ID) REFERENCES PRODUCT(PRODUCT_ID)
);


create table USERS(
       USER_NAME varchar(15) not null primary key,
       USER_PASS varchar(15) not null,
       NAME varchar(100) not null,
       STREET_ADDRESS varchar(100)not null,
       ZIP_CODE varchar(10) not null,
       CITY varchar(30) not null,
       COUNTRY varchar(30) not null
);

create table USER_ROLES(
	USER_NAME varchar(15) not null,
	ROLE_NAME varchar(15) not null,
	primary key (USER_NAME, ROLE_NAME)
);






-- Populate the Component Table

INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('MB', 8000,5,'ASUS - P5G41T-M LX');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('MB', 9500,5,'ASUS - P5P41T LE');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('MB', 10000,5,'ASUS - P8H67');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('MB', 9800,5,'GIGABYTE - H55M-S2V');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('MB', 12000,5,'MSI - H55M-E33');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('MB', 8000,5,'MSI - P55A-G55');

INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('RAM', 3000,20,'KINGSTON - 2GB - 1333');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('RAM', 9000,20,'CORSAIR - Dual Channel 4GB - 1600-(2x2GB)');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('RAM', 6000,20,'KINGSTON - Dual Channel 4GB - 1333-(2x2GB)');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('RAM', 13000,20,'CORSAIR - 4GB - 1600');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('RAM', 15500,20,'CRUCIAL- Triple Channel 6GB - 1333-(3x2GB)');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('RAM', 3000,20,'KINGMAX - 2GB - 1333');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('RAM', 6000,20,'KINGMAX - Dual Channel 4GB - 1333-(2x2GB)');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('RAM', 17500,20,'GEIL - Triple Channel 6GB - 1600-(3x2GB)');

INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('VGA', 8000,7,'ASUS - GeForce GT 210 - 1GB');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('VGA', 20000,7,'ASUS - GeForce GTS 250 - 1GB');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('VGA', 74000,7,'ASUS - GeForce GTX 580');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('VGA', 10000,7,'MSI - ATI RADEON R4670 - 1GB');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('VGA', 34500,7,'MSI - ATI RADEON R5850 - 1GB');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('VGA', 44000,7,'MSI - GeForce GTX 470 Twin Frozr');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('VGA', 11500,7,'CLUB - ATI RADEON HD 5550 - 2GB');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('VGA', 21000,7,'EliteGroup - GeForce GTS 250 - 2GB');

INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('CPU', 7800,9,'Intel Dual-Core E5700 - 3.0GHz - 45 nm');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('CPU', 13500,9,'Intel Core i3 - 3.06GHz - 540');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('CPU', 26000,9,'Intel Core i5 - 2.80GHz - 760');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('CPU', 32000,9,'Intel Core i7 - 2.80GHz - 930');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('CPU', 7200,9,'AM3 - Athlon II X2 250 - 3.0GHz');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('CPU', 9400,9,'AM3 - Athlon II X3 450 - 3.2GHz');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('CPU', 12500,9,'AM3 - Phenom II X4 840 - 3.2GHz');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('CPU', 19500,9,'Intel Core 2 Quad Q8400 - 2.66GHz - 45 nm');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('CPU', 136000,9,'Intel Core i7 Extreme Edition 990X - 3.46GHz');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('CPU', 38000,9,'Intel Core i7 - 3.2GHz - 960');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('CPU', 12300,9,'AM3 - Athlon II X4 640 - 3.0GHz');

INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('HDD', 5300,11,'Seagate - SATA 2 - 500 GB +');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('HDD', 12000,11,'Seagate-SATA 2 - 2 TeraByte / 64mb buffer');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('HDD', 14500,11,'Seagate - 2000 GB - 2 TeraByte');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('HDD', 13000,11,'Western Digital - SATA 2 - 2 TeraByte / 64mb buffer');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('HDD', 12500,11,'Western Digital -SATA 3 - 1 TeraByte - 64MB / WD Caviar Black');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('HDD', 5100,11,'Hitachi-SATA 2 - 500 GB +');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('HDD', 7200,11,'Hitachi-SATA 2 - 1 TeraByte');

INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('MONITOR',18900,30,'LG LCD W1953SE');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('MONITOR',29500,30,'LG W2252S');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('MONITOR',23500,30,'LG E2050S');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('MONITOR',21900,30,'Samsung LED EX2065X Pluse');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('MONITOR',13200,30,'Samsung P23500');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('MONITOR',17900,30,'Samsung B1955N');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('MONITOR',21000,30,'AOC LED e2036V');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('MONITOR',28100,30,'AOC LED V22 Pluse');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('MONITOR',25400,30,'AOC 2436Vwa');

INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('OPTIC',3200,45,'ASUS - 24X - SATA');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('OPTIC',6500,45,'External MSI - DVD RW');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('OPTIC',2800,45,'SONY - 24x - SATA');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('OPTIC',8000,45,'External LG - DVD RW Slim');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('OPTIC',6500,45,'External SAMSUNG - DVD RW');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('OPTIC',21000,45,'Pioneer Blu-ray burner');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('OPTIC',5000,45,'Plextor PX-880 SATA');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('OPTIC',7500,45,'External ASUS - DVD RW');
INSERT INTO COMPONENT(NAME, PRICE,STOCK_NUM,DESCRIPTION) VALUES('OPTIC',2800,45,'LITEON - 24X - SATA');






insert into USERS(USER_NAME, USER_PASS, NAME, STREET_ADDRESS, ZIP_CODE, CITY, COUNTRY) 
     VALUES('tomcat','tacmot','Tom Cat','Apache Road', '34 567', 'Petaluma', 'USA');
insert into USERS(USER_NAME, USER_PASS, NAME, STREET_ADDRESS, ZIP_CODE, CITY, COUNTRY) 
     VALUES('gyro','glurk','Gyro Gearloose','Duck Road', '78 901', 'Ducksbury', 'USA');
insert into USERS(USER_NAME, USER_PASS, NAME, STREET_ADDRESS, ZIP_CODE, CITY, COUNTRY) 
     VALUES('admin', 'glurk','System user', 'Polacksbacken', '752 37', 'Uppsala', 'Sweden');

insert into USER_ROLES(USER_NAME, ROLE_NAME) VALUES('tomcat','tomcat');
insert into USER_ROLES(USER_NAME, ROLE_NAME) VALUES('gyro', 'tomcat');
insert into USER_ROLES(USER_NAME, ROLE_NAME) VALUES('admin','manager');
insert into USER_ROLES(USER_NAME, ROLE_NAME) VALUES('admin','admin');
insert into USER_ROLES(USER_NAME, ROLE_NAME) VALUES('admin','tomcat');
insert into USER_ROLES(USER_NAME, ROLE_NAME) VALUES('tomcat','manager-script');
