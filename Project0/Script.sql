--DROP TABLE IF EXISTS additional_information CASCADE;

CREATE TYPE acess_levels AS ENUM
	('customer', 'bank_teller', 'branch_manager'
);
 
CREATE TABLE login_information
	(username VARCHAR(30) PRIMARY KEY,
	pass_word VARCHAR(50) NOT NULL,
	user_id SERIAL NOT NULL UNIQUE,
	active boolean NOT NULL DEFAULT TRUE,
	access_level acess_levels NOT NULL,
	email VARCHAR(50),
	APPLICATION_STATUS VARCHAR(15) NOT NULL 
);

ALTER TABLE login_information ADD COLUMN APPLICATION_STATUS VARCHAR(15) NOT NULL DEFAULT "NOT_APPROVED" ;

INSERT INTO login_information (username, pass_word, application_status, active, user_id, access_level, email) VALUES ('user','pass','approved',true,80,'customer','email');

DROP TABLE login_information;


CREATE SEQUENCE user_id_seq START 100;

SELECT nextval('user_id_seq');

CREATE TABLE additional_information
	(first_name VARCHAR (20) PRIMARY KEY,
	last_name VARCHAR (20) NOT NULL,
	street VARCHAR (30),
	city VARCHAR (15),
	state VARCHAR(3), 
	zip VARCHAR(20),
	date_of_birth DATE,
	user_id SERIAL NOT NULL
);






SELECT * FROM login_information login; JOIN additional_information ai ON (login.user_id = ai.user_id); 

DROP TABLE additional_information;

DROP TABLE account_info;

DROP TABLE transaction_details ;

CREATE TABLE account_info
	(account_balance DECIMAL DEFAULT 0.00 PRIMARY KEY,
	user_id SERIAL NOT NULL UNIQUE REFERENCES additional_information(user_id)
);


CREATE TYPE transaction_types AS ENUM
	('withdraw', 'deposit'
);

CREATE TABLE transaction_details
	(transaction_type transaction_types PRIMARY KEY,
	user_id SERIAL NOT NULL UNIQUE REFERENCES account_info(user_id),
	amount DECIMAL,
	transaction_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO login_information VALUES
	('themahad', 'password', 1, true, 'branch_manager', 'mahad@hotmail.com');

INSERT INTO additional_information VALUES
	('Mahad', 'Khalid', '124 Davidson st', 'Buffalo', 'NY', '14785', '1994-04-22', 1);

ALTER TABLE additional_information DROP CONSTRAINT additional_information_pkey;

ALTER TABLE additional_information ADD PRIMARY KEY (user_id);

INSERT INTO additional_information VALUES
	(0.00, 1, 'Mahad', 'Khalid');

INSERT INTO additional_information (first_name, last_name, street, city, state, zip,
                    date_of_birth, user_id) VALUES ('first1','last','s','c','s','z','02-12-2000',346);
                   
         INSERT INTO additional_information (first_name, last_name, street, city, state, zip,user_id) VALUES ('first12','last','s','c','s','z',346);
        
        SELECT NEXTVAL('USER_ID_SEQ') AS USER_ID;

--CREATE TABLE customer (
--	customer_id SERIAL PRIMARY KEY,
--	customer_first_name VARCHAR (20) NOT NULL,
--	customer_last_name VARCHAR (20),
--	customer_street VARCHAR (30),
--	customer_city VARCHAR (15),
--	customer_state VARCHAR(3), 
--	customer_zip VARCHAR(20),
--	customer_email VARCHAR(50),
--	date_of_birth DATE,
--	bank_number INTEGER NOT NULL,
--	login_id INTEGER UNIQUE NOT NULL
--);
--
--CREATE TABLE bank_teller (
--	teller_id SERIAL PRIMARY KEY,
--	bank_number INTEGER NOT NULL,
--	manager_id SERIAL NOT NULL, 
--	teller_first_name VARCHAR(20),
--	teller_last_name VARCHAR(20),
--	teller_phone VARCHAR(11),
--	teller_email VARCHAR(50),
--	login_id INTEGER NOT NULL UNIQUE
--);
--
--CREATE TABLE branch_manager (
--	manager_id SERIAL PRIMARY KEY,
--	bank_number INTEGER NOT NULL,
--	manager_phone VARCHAR(11), 
--	manager_first_name VARCHAR(20),
--	manager_last_name VARCHAR(20),
--	manager_email VARCHAR(50),
--	login_id INTEGER NOT NULL UNIQUE
--);
--
--CREATE TABLE login (
--	login_id SERIAL PRIMARY KEY NOT NULL REFERENCES branch_manager(login_id) REFERENCES bank_teller(login_id) REFERENCES customer(login_id),
--	username VARCHAR NOT NULL UNIQUE,
--	pass_word VARCHAR(50) NOT NULL
--);



--INSERT INTO hero_names (hero_name, power_description)
-- 	VALUES ('hawkeye', 'impressive biceps');
--
--SELECT * FROM avengers RIGHT JOIN hero_names ON hero_names.hero_name = avengers.hero_name;
--
--
--INSERT INTO homes (home_name,
--	home_str_num,
--	home_str,
--	home_city,
--	home_region,
--	home_zip, 
--	home_country) VALUES ('helicarrier', null, 'bottom of the potomac', 'washington', 'dc', null, 'USA');

--SELECT * FROM avengers FULL JOIN homes ON homes.home_name = avengers.hero_home;
--
----Subquery/Aliases 
--
--SELECT * FROM (SELECT * FROM avengers LEFT JOIN homes ON homes.home_name = avengers.hero_home) 
--	AS avhome INNER JOIN hero_names ON hero_names.hero_name = avhome.hero_name; 
--
--
----Scalar and AGGREGATE FUNCTIONS
--
--SELECT UPPER (hero_name) FROM avengers; -- scalar
--
--SELECT AVG (power_level) FROM avengers; -- aggregate
--
----GROUP BY/ORDER BY
--
--SELECT hero_name, SUM(power_level) FROM avengers GROUP BY hero_name; 
--
--SELECT UPPER (hero_name) FROM avengers ORDER BY hero_name ASC; 
--
---- UNION (this is a silly example)
--
--SELECT hero_name, first_name FROM avengers UNION SELECT home_name, home_str FROM homes; 
--
----Triggers/Functions
--
--ALTER TABLE homes ADD COLUMN residents INTEGER;
--
--CREATE OR REPLACE FUNCTION increase_residents() RETURNS TRIGGER AS 
--$$
--BEGIN 
--	UPDATE homes SET residents = (SELECT residents FROM homes WHERE NEW.hero_home = homes.home_name)+1
--	WHERE homes.home_name = NEW.hero_home; 
--	RETURN NEW; 
--END
--$$
--LANGUAGE plpgsql; 
--
--CREATE TRIGGER increment_residents AFTER INSERT ON avengers FOR EACH ROW EXECUTE PROCEDURE increase_residents();
--
--DROP TABLE IF EXISTS homes CASCADE; 
--
--CREATE TABLE homes (
--	home_name VARCHAR (50) PRIMARY KEY,
--	home_str_num VARCHAR (10),
--	home_str VARCHAR (30),
--	home_city VARCHAR (30),
--	home_region VARCHAR (30),
--	home_zip VARCHAR(10), 
--	home_country VARCHAR(20), 
--	residents INTEGER DEFAULT 0 
--);
--
--INSERT INTO homes (
--	home_name,
--	home_str_num,
--	home_str,
--	home_city,
--	home_region,
--	home_zip, 
--	home_country) VALUES ('shield apartment', '435', 'somestreet apt b', 'Brooklyn', 'NY', '20117', 'USA'),
--	('stark tower', '1', 'tony street', 'Manhattan', 'NY', '10087', 'USA');
--
--DROP TABLE IF EXISTS avengers; 
--
--CREATE TABLE avengers (
--	hero_id SERIAL PRIMARY KEY, --SERIAL IS a postgres SPECIFIC DATATYPE that assigns an INTEGER value AT record creation automatical AND will INCREMENT automatically FOR the NEXT record. 
--	hero_name VARCHAR(30) NOT NULL REFERENCES hero_names(hero_name),
--	power_level INTEGER, 
--	first_name VARCHAR(50),
--	last_name VARCHAR(50),
--	hero_home VARCHAR(50) REFERENCES homes(home_name)
--);
--
--CREATE TRIGGER increment_residents AFTER INSERT ON avengers FOR EACH ROW EXECUTE PROCEDURE increase_residents();
--
--
--INSERT INTO avengers (
--	hero_name,
--	power_level, 
--	first_name,
--	last_name,
--	hero_home)
--	VALUES ('captain america', 25, 'steve', 'rogers', 'shield apartment'),
--	('iron man', 256, 'tony', 'stark', 'stark tower'),
--	('hulk', 80001, 'bruce', 'banner', 'stark tower'),
--	('captain america', 35, 'sam', 'wilson', null);