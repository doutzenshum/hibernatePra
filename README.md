# hibernatePra
the first module is bad
--客户表

CREATE TABLE customer (

id int(20) NOT NULL auto_increment,

name varchar(100) ,

PRIMARY KEY (id)

)

--地址表

CREATE TABLE address (

id int(20) NOT NULL auto_increment,

province varchar(50) ,

city varchar(50) ,

postcode varchar(50) ,

detail varchar(50) ,

PRIMARY KEY (id)

)
