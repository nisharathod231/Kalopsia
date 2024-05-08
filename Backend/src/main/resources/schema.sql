USE kalopsia;
CREATE TABLE product IF NOT EXISTS (
	id int AUTO_INCREMENT,
    name varchar(255),
    image varchar(255),
    old_price double,
    new_price double,
    category varchar(255),
    primary key (id)
);

CREATE TABLE user IF NOT EXISTS (
    id integer auto_increment,
    email varchar(255),
    name varchar(255),
    password varchar(255),
    primary key (id)
);
