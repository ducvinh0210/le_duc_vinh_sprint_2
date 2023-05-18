drop database if exists sprint_4;
create database sprint_4;
use sprint_4;

-- add user-- 
insert into	user(username, password,is_delete) values('levinh','$2a$12$dZ1jfF6JVUKPgnPP.8S1m.PXTF0/vNuj7qwSmqLXwTUXgrAru6iKy',false);
insert into	user(username, password,is_delete) values('thivi','$2a$12$dZ1jfF6JVUKPgnPP.8S1m.PXTF0/vNuj7qwSmqLXwTUXgrAru6iKy',false);
insert into	user(username, password,is_delete) values('hongvy','$2a$12$dZ1jfF6JVUKPgnPP.8S1m.PXTF0/vNuj7qwSmqLXwTUXgrAru6iKy',false);
insert into	user(username, password,is_delete) values('phanvan','$2a$12$dZ1jfF6JVUKPgnPP.8S1m.PXTF0/vNuj7qwSmqLXwTUXgrAru6iKy',false);
insert into	user(username, password,is_delete) values('namle','$2a$12$dZ1jfF6JVUKPgnPP.8S1m.PXTF0/vNuj7qwSmqLXwTUXgrAru6iKy',false);
insert into	user(username, password,is_delete) values('manhcuong','$2a$12$dZ1jfF6JVUKPgnPP.8S1m.PXTF0/vNuj7qwSmqLXwTUXgrAru6iKy',false);

-- add role
insert into	role(name, is_delete) values('ROLE_ADMIN', false);
insert into	role(name, is_delete) values('ROLE_CUSTOMER', false);

-- add user-role
INSERT INTO user_role (username, role_id, is_delete) VALUES ('thivi', '1',false);
INSERT INTO user_role (username, role_id,is_delete) VALUES ('levinh', '2',false);
INSERT INTO user_role (username, role_id,is_delete) VALUES ('hongvy', '2',false);
INSERT INTO user_role (username, role_id,is_delete) VALUES ('phanvan', '2',false);
INSERT INTO user_role (username, role_id,is_delete) VALUES ('namle', '2',false);
INSERT INTO user_role (username, role_id,is_delete) VALUES ('manhcuong', '2',false);

-- add size

insert into size(name, is_delete) values('size 38', false);
insert into size(name, is_delete) values('size 39', false);
insert into size(name, is_delete) values('size 40', false);
insert into size(name, is_delete) values('size 41', false);
insert into size(name, is_delete) values('size 42', false);
 
 -- add customer
insert into customer (name, date_of_birth, gender, id_card, email, address, phone_number, username, is_delete) values ('Lê Đức Vịnh', '12-12-2000', 1, '0939383289', 'leducvinh@gmail.com', 'Hòa Khánh Nam, Đà Nẵng', '0907197155', 'levinh', false);
insert into customer (name, date_of_birth, gender, id_card, email, address, phone_number, username, is_delete) values ('Phạm Thị Vi', '12-10-2000', 1, '0328383838', 'phamthivi@gmail.com', 'Hòa Khánh Bắc, Đà Nẵng', '0907197155', 'thivi', false);
insert into customer (name, date_of_birth, gender, id_card, email, address, phone_number, username, is_delete) values ('Phan Thị Vân', '12-02-1998', 1, '0383838372', 'phanthivan@gmail.com', 'Hòa Khánh Nam, Đà Nẵng', '0907197155', 'phanvan', false);
insert into customer (name, date_of_birth, gender, id_card, email, address, phone_number, username, is_delete) values ('Lê Thị Hồng Vy', '12-12-1997', 1, '0348723783', 'lethihongvy@gmail.com', 'Hòa Khánh Nam, Đà Nẵng', '0907197155', 'hongvy', false);

-- add shoe type
insert into	shoe_type(name, is_delete) values('Nam', false);
insert into	shoe_type(name, is_delete) values('Nữ', false);


-- add shoe
insert into shoe (name, price, discount, manufacturer, image, describes, shoe_type_id, is_delete) values ('adidas Ultra TechneKow', '1200000', '0', 'adidas', 'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/a432c57fbdb24f75b9faaf1e00a2acac_9366/Giay_Forum_Low_The_Grinch_Multi_HP6772_04_standard.jpg', 'Nếu bạn chỉ được phép có một đôi giày sneaker thì có lẽ đó chính là đôi giày này. Đôi giày adidas Grand Court này có 3 Sọc kinh điển giúp phong cách sneaker của bạn luôn gọn gàng và chỉn chu.', '1', false);
insert into shoe (name, price, discount, manufacturer, image, describes, shoe_type_id, is_delete) values ('GIÀY CỔ THẤP FORUM', '1200000', '0', 'adidas', 'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/a432c57fbdb24f75b9faaf1e00a2acac_9366/Giay_Forum_Low_The_Grinch_Multi_HP6772_04_standard.jpg', 'Nếu bạn chỉ được phép có một đôi giày sneaker thì có lẽ đó chính là đôi giày này. Đôi giày adidas Grand Court này có 3 Sọc kinh điển giúp phong cách sneaker của bạn luôn gọn gàng và chỉn chu.', '1', false);
insert into shoe (name, price, discount, manufacturer, image, describes, shoe_type_id, is_delete) values ('adidas Ultra TechneKow', '1200000', '0', 'adidas', 'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/a432c57fbdb24f75b9faaf1e00a2acac_9366/Giay_Forum_Low_The_Grinch_Multi_HP6772_04_standard.jpg', 'Nếu bạn chỉ được phép có một đôi giày sneaker thì có lẽ đó chính là đôi giày này. Đôi giày adidas Grand Court này có 3 Sọc kinh điển giúp phong cách sneaker của bạn luôn gọn gàng và chỉn chu', '1', false);

insert into shoe_size (quantity, shoe_id, size_id, is_delete) values (60, 1, 1, false);
insert into shoe_size (quantity, shoe_id, size_id, is_delete) values (70, 2, 2, false);
insert into shoe_size (quantity, shoe_id, size_id, is_delete) values (80, 3, 3, false);
insert into shoe_size (quantity, shoe_id, size_id, is_delete) values (90, 1, 4, false);

-- add order_detail
insert into order_detail (date_payment, quantity, customer_id, shoe_size_id, is_pay, is_delete) values ('2023-4-04', '1', 1, 1, false, false);
insert into order_detail (date_payment, quantity, customer_id, shoe_size_id, is_pay,is_delete) values ('2023-06-02', '1', 2, 2, false, false);
insert into order_detail (date_payment, quantity, customer_id, shoe_size_id, is_pay, is_delete) values ('2023-05-15', '1', 3, 3, false, false);
insert into order_detail (date_payment, quantity, customer_id, shoe_size_id, is_pay, is_delete) values ('2023-06-28', '1', 4, 4, false, false);










