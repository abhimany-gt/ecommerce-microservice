DROP TABLE IF EXISTS product;
create table product(id int AUTO_INCREMENT primary key,code varchar(50),name varchar(200),description varchar(500),price double,inStock boolean);
INSERT INTO product (code,name,description,price,inStock) VALUES
  ('123A', 'BOOK-1', 'Cook-Book',100.00,true),
  ('123B', 'BOOK-2', 'Play-Book',110.00,true);