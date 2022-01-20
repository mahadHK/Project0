--DROP TABLE IF EXISTS additional_information CASCADE;

CREATE TYPE acess_levels AS ENUM
	('customer', 'bank_teller', 'branch_manager'
);
 
ALTER TABLE login_information ALTER COLUMN ACCESS_LEVEL SET DEFAULT 'customer';

ALTER TABLE login_information ADD COLUMN APPLICATION_STATUS VARCHAR(15) NOT NULL DEFAULT "NOT_APPROVED" ;

INSERT INTO login_information (username, pass_word, application_status, active, user_id, access_level, email) VALUES ('user','pass','approved',true,80,'customer','email');

DROP TABLE login_information;


CREATE SEQUENCE user_id_seq START 100;

SELECT nextval('user_id_seq');

CREATE TABLE LOGIN_INFORMATION (
	USERNAME VARCHAR(30) PRIMARY KEY,
	PASSWORD VARCHAR(50) NOT NULL,
	ACTIVE 	 BOOLEAN NOT NULL DEFAULT TRUE,
	ACCESS_LEVEL acess_levels NOT NULL,
	EMAIL    VARCHAR(50),
	APPLICATION_STATUS VARCHAR(15) NOT NULL 
);

INSERT INTO LOGIN_INFORMATION VALUES ('Teller_01','Teller_01',TRUE,'bank_teller','email987.com','Approved');

SELECT active FROM LOGIN_INFORMATION;

SELECT * FROM ADDITIONAL_INFORMATION; WHERE USERNAME = 'Manager_01';

SELECT * FROM login_information; login INNER join additional_information addin on (login.username = addin.username);

CREATE TABLE ADDITIONAL_INFORMATION (
	USERNAME 	VARCHAR(30) PRIMARY KEY,
	FIRST_NAME  VARCHAR(20) NOT NULL,
	LAST_NAME 	VARCHAR(20) NOT NULL,
	STREET 		VARCHAR(30),
	CITY 		VARCHAR(15),
	STATE       VARCHAR(30), 
	ZIP 		VARCHAR(20)
);

INSERT INTO ADDITIONAL_INFORMATION VALUES ('Manager_01','First','Last','Street','City','State','123456');

CREATE TABLE ACCOUNT_INFO(
	USERNAME VARCHAR(30) PRIMARY KEY,
	BANK_BALANCE DECIMAL
);

INSERT INTO ACCOUNT_INFO VALUES ('Customer_02', 100);

CREATE TABLE TRANSACTION_DETAILS(
	USERNAME VARCHAR(30) ,
	TRANSACTION_ID SERIAL PRIMARY KEY,
	TRANSACTION_TYPE VARCHAR(30) NOT NULL,
	AMOUNT DECIMAL,
	TRANSACTION_STATUS VARCHAR(30)
);

SELECT * FROM TRANSACTION_DETAILS WHERE TRANSACTION_ID = 100;

UPDATE TRANSACTION_DETAILS SET transaction_status = 'Approved' WHERE transaction_id = 100;

SELECT ACCESS_LEVEL FROM login_information WHERE USERNAME = 'Manager_01';

INSERT INTO TRANSACTION_DETAILS VALUES ('Customer_01',Nextval('TRANS_ID_SEQ'),'Deposit','100','Pending');

CREATE SEQUENCE TRANS_ID_SEQ START 100;

SELECT * FROM login_information login LEFT join additional_information add on (login.username = add.username) left join ACCOUNT_INFO acc on (acc.username = login.username) WHERE login.username = 'Teller_01';

___________________________________________________________________________________________________






SELECT * FROM login_information login; JOIN additional_information ai ON (login.user_id = ai.user_id); 

DROP TABLE additional_information;

DROP TABLE account_info;

DROP TABLE transaction_details ;


CREATE TYPE transaction_types AS ENUM
	('withdraw', 'deposit'
);



INSERT INTO additional_information (first_name, last_name, street, city, state, zip,
                    date_of_birth, user_id) VALUES ('first1','last','s','c','s','z','02-12-2000',346);
                   
         INSERT INTO additional_information (first_name, last_name, street, city, state, zip,user_id) VALUES ('first12','last','s','c','s','z',346);
        
        SELECT NEXTVAL('USER_ID_SEQ') AS USER_ID;