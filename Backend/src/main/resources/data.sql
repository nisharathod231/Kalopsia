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

INSERT INTO product values (1, 'Elegance Evening Gown', 'https://www.followthefashion.org/wp-content/uploads/2023/05/Gigi-Hadid-Givency.jpg', 899.99, 1200, 'her');
INSERT INTO product values (2, 'Royal Velvet Gala Jumpsuit', 'https://img.bekiamoda.com/looks/2000/2315-p1.jpg', 950, 655, 'her');
INSERT INTO product values (3, 'Exquisite Lace Affair Maxi Dress', 'https://i.pinimg.com/474x/ea/12/e2/ea12e2deb52f4e1e8ed59767c84fb785.jpg', 1100, 750, 'her');
INSERT INTO product values (4, 'Regal Red Carpet Glamour Gown', 'https://images.hola.com/fashion/imagenes/tendencias/2016021862382/look-de-la-semana-gigi-hadid/0-238-773/look_semana_gigi_hadid_6-a.jpg?tx=w_300', 1500, 1200, 'her');
INSERT INTO product values (5, 'Luxury Weekend Retreat Ensemble', 'https://images.hola.com/fashion/imagenes/tendencias/2016021862382/look-de-la-semana-gigi-hadid/0-238-775/look_semana_gigi_hadid_8-a.jpg?tx=w_300', 600, 459, 'her');
INSERT INTO product values (6, 'Opulent Bohemian Festival Dress', 'https://i.pinimg.com/474x/87/5c/1b/875c1b347a9146aa49e81d407ec64ab9.jpg', 800, 550, 'her');
INSERT INTO product values (7, 'Vintage Glamour Embellished Cocktail Dress', 'https://i.pinimg.com/474x/2e/26/94/2e2694b16c66bf8add366659b139a8e6.jpg', 1000, 799, 'her');
INSERT INTO product values (8, 'Edgy Luxe Leather Street Style Jacket', 'https://i.pinimg.com/474x/dc/fe/ec/dcfeeca69458e491dbb786be91d398f6.jpg', 950, 695, 'her');
INSERT INTO product values (9, 'Sophisticated Platinum Office Ensemble', 'https://i.pinimg.com/736x/e3/80/ad/e380ada15b3236c55ab321e9b65bd22b.jpg', 1200, 890, 'her');
INSERT INTO product values (10, 'Dazzling Twilight Sparkle Necklace', 'https://whitesatin.ca/wp-content/uploads/2021/11/Sara-Gabriel-Ana-Necklace-300x500.jpg', 1200, 899, 'Accessories');
INSERT INTO product values (11, 'Celestial Velvet Luminary Necklace', 'https://whitesatin.ca/wp-content/uploads/2021/11/Sara-Gabriel-Angela-Necklace-Front-300x500.jpg', 950, 655, 'Accessories');
INSERT INTO product values (12, 'Enchanting Lace Euphoria Necklace', 'https://whitesatin.ca/wp-content/uploads/2021/11/Chloe-Pendant-300x500.webp', 1100, 750, 'Accessories');
INSERT INTO product values (13, 'Regal Crimson Stardust Necklace', 'https://whitesatin.ca/wp-content/uploads/2021/11/Sara-Gabriel-Dylan-Necklace-300x500.jpg', 1500, 1200, 'Accessories');
INSERT INTO product values (14, 'Luxury Retreat Moonlit Necklace', 'https://whitesatin.ca/wp-content/uploads/2021/11/Sara-Gabriel-Wallace-Hair-Ribbon-300x500.jpg', 600, 459, 'Accessories');
INSERT INTO product values (15, 'Opulent Bohemian Starfall Necklace', 'https://whitesatin.ca/wp-content/uploads/2021/11/Sara-Gabriel-Joy-Mini-Hair-Ribbon-300x500.jpg', 800, 550, 'Accessories');
INSERT INTO product values (16, 'Vintage Glamour Ethereal Diamond Cascade', 'https://whitesatin.ca/wp-content/uploads/2021/11/Sara-Gabriel-Lindsay-Hair-Chain-300x500.jpg', 1000, 799, 'Accessories');
INSERT INTO product values (17, 'Edgy Luxe Midnight Serpent Necklace', 'https://whitesatin.ca/wp-content/uploads/2021/11/Sara-Gabriel-Alex-Earring-300x500.jpg', 950, 695, 'Accessories');
INSERT INTO product values (18, 'Sapphire Whisper Platinum Ensemble', 'https://whitesatin.ca/wp-content/uploads/2021/11/Sara-Gabriel-Hazel-Bracelet-300x500.webp', 1200, 890, 'Accessories');

CREATE TABLE IF NOT EXISTS user (
    id integer auto_increment,
    phone_number varchar(255) UNIQUE,
    name varchar(255),
    password varchar(255),
    primary key (id)
);
