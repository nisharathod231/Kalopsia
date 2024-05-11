USE kalopsia;

DROP TABLE IF EXISTS product;

CREATE TABLE IF NOT EXISTS product (
	id int AUTO_INCREMENT,
    name varchar(255),
    image varchar(255),
    old_price double,
    new_price double,
    category varchar(255),
    primary key (id)
);

INSERT INTO product values (1, 'Sapphire Whisper Platinum Ensemble', 'https://whitesatin.ca/wp-content/uploads/2021/11/Sara-Gabriel-Hazel-Bracelet-300x500.webp', 89.0, 67.0, 'Accessories');

CREATE TABLE IF NOT EXISTS user (
    id integer auto_increment,
    email varchar(255),
    name varchar(255),
    password varchar(255),
    primary key (id)
);
