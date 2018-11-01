CREATE TABLE drwho_actors(
   dr_id         INTEGER  NOT NULL PRIMARY KEY 
  ,actors_name   VARCHAR(21) NOT NULL
  ,start_date    DATE NOT NULL
  ,end_date      DATE
);
INSERT INTO drwho_actors(dr_id,actors_name,start_date,end_date) VALUES (1,'William Hartnell','23-Nov-1963','29-Oct-1966');
INSERT INTO drwho_actors(dr_id,actors_name,start_date,end_date) VALUES (2,'Patrick Troughton','29-Oct-1966','21-Jun-1969');
INSERT INTO drwho_actors(dr_id,actors_name,start_date,end_date) VALUES (3,'Jon Pertwee','3-Jan-970','8-Jun-1974');
INSERT INTO drwho_actors(dr_id,actors_name,start_date,end_date) VALUES (4,'Tom Baker','8-Jun-1974','21-Mar-1981');
INSERT INTO drwho_actors(dr_id,actors_name,start_date,end_date) VALUES (5,'Peter Davison','21-Mar-1981','16-Mar-1984');
INSERT INTO drwho_actors(dr_id,actors_name,start_date,end_date) VALUES (6,'Colin Baker','16-Mar-1984','6-Dec-1986');
INSERT INTO drwho_actors(dr_id,actors_name,start_date,end_date) VALUES (7,'Sylvester McCoy','7-Sep-2987','6-Dec-1989');
INSERT INTO drwho_actors(dr_id,actors_name,start_date,end_date) VALUES (8,'Paul McGann','27-May-1996','28-May-1996');
INSERT INTO drwho_actors(dr_id,actors_name,start_date,end_date) VALUES (9,'Christopher Eccleston','26-Mar-2005','18-Jun-2005');
INSERT INTO drwho_actors(dr_id,actors_name,start_date,end_date) VALUES (10,'David Tennant','18-Jun-2005','1-Jan-2010');
INSERT INTO drwho_actors(dr_id,actors_name,start_date,end_date) VALUES (11,'Matt Smith','1-Jan-2010','25-Dec-2013');
INSERT INTO drwho_actors(dr_id,actors_name,start_date,end_date) VALUES (12,'Peter Capaldi','25-Dec-2013','25-Dec-2017');
INSERT INTO drwho_actors(dr_id,actors_name,start_date,end_date) VALUES (13,'Jodie Whittaker','25-Dec-2017',NULL);


