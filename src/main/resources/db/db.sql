CREATE TABLE IF NOT EXISTS user(
id INT(11)PRIMARY KEY NOT NULL AUTO_INCREMENT,
city_lab VARCHAR(30),
user_name VARCHAR(30)NOT NULL,
birthday DATE,
barcode VARCHAR(30),
blood_analize_date DATE,
test_date DATE,
released_date DATE,
short_data_container VARCHAR(2000)NOT NULL
);

