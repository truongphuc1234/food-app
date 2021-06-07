DROP DATABASE IF EXISTS food_app;
CREATE DATABASE food_app;
USE food_app;

CREATE TABLE `customer`
(
    `customer_id`       INT AUTO_INCREMENT PRIMARY KEY,
    `customer_name`     VARCHAR(45) NOT NULL,
    `customer_phone`    VARCHAR(45) NOT NULL,
    `customer_birthday` DATE        NOT NULL,
    `customer_avatar`   TEXT        NULL,
    `customer_address`  TEXT        NOT NULL
);

CREATE TABLE `account_status`
(
    `account_status_id`   INT AUTO_INCREMENT PRIMARY KEY,
    `account_status_name` VARCHAR(10)
);

CREATE TABLE `account`
(
    `account_id`            INT AUTO_INCREMENT PRIMARY KEY,
    `account_name`          VARCHAR(45) NOT NULL,
    `account_password`      TEXT        NOT NULL,
    `account_email`         VARCHAR(45) NOT NULL,
    `account_status_id`     INT         NOT NULL,
    `account_register_time` DATETIME    NOT NULL,
    `account_login_time`    DATETIME    NOT NULL,
    `customer_id`           INT         NOT NULL,
    FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
    FOREIGN KEY (`account_status_id`) REFERENCES `account_status` (`account_status_id`)
);

CREATE TABLE `role`
(
    `role_id`   INT AUTO_INCREMENT PRIMARY KEY,
    `role_name` VARCHAR(45) NOT NULL
);

CREATE TABLE `account_role`
(
    `account_role_id` INT AUTO_INCREMENT PRIMARY KEY,
    `account_id`      INT NOT NULL,
    `role_id`         INT NOT NULL,
    FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`),
    FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
);

CREATE TABLE `category`
(
    `category_id`   INT AUTO_INCREMENT PRIMARY KEY,
    `category_name` VARCHAR(45) NOT NULL
);

CREATE TABLE `food_status`
(
    `food_status_id`   INT AUTO_INCREMENT PRIMARY KEY,
    `food_status_name` VARCHAR(15) NOT NULL
);

CREATE TABLE `food`
(
    `food_id`          INT AUTO_INCREMENT PRIMARY KEY,
    `food_name`        TEXT     NOT NULL,
    `food_price`       DOUBLE   NOT NULL,
    `food_status_id`   INT      NOT NULL,
    `food_image`       TEXT     NOT NULL,
    `food_time_post`   DATETIME NOT NULL,
    `food_description` TEXT     NOT NULL,
    `food_update_time` DATETIME NOT NULL,
    `discount`         DOUBLE,

    FOREIGN KEY (`food_status_id`) REFERENCES `food_status` (`food_status_id`)
);


CREATE TABLE `food_category`
(
    `food_category_id` INT AUTO_INCREMENT PRIMARY KEY,
    `food_id`          INT NOT NULL,
    `category_id`      INT NOT NULL,
    FOREIGN KEY (`food_id`) REFERENCES `food` (`food_id`),
    FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
);

CREATE TABLE `rating`
(
    `rating_id`    INT AUTO_INCREMENT PRIMARY KEY,
    `rating_level` INT NOT NULL,
    `food_id`      INT NOT NULL,
    `account_id`   INT NOT NULL,
    FOREIGN KEY (`food_id`) REFERENCES `food` (`food_id`),
    FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`)
);

CREATE TABLE `comment`
(
    `comment_id`      INT AUTO_INCREMENT PRIMARY KEY,
    `comment_content` TEXT     NULL,
    `comment_image`   TEXT     NULL,
    `food_id`         INT      NOT NULL,
    `account_id`      INT      NOT NULL,
    `comment_time`    DATETIME NOT NULL,
    FOREIGN KEY (`food_id`) REFERENCES `food` (`food_id`),
    FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`)
);

CREATE TABLE `cart`
(
    `cart_id`    INT AUTO_INCREMENT PRIMARY KEY,
    `account_id` INT NOT NULL,
    FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`)
);

CREATE TABLE `cart_item`
(
    `cart_item_id` INT AUTO_INCREMENT PRIMARY KEY,
    `cart_id`      INT      NOT NULL,
    `food_id`      INT      NOT NULL,
    `quantity`     INT      NOT NULL,
    `note`         TEXT,
    `create_date`  DATETIME NOT NULL,
    FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`),
    FOREIGN KEY (`food_id`) REFERENCES `food` (`food_id`)
);
CREATE TABLE `order_status`
(
    `order_status_id`   INT AUTO_INCREMENT PRIMARY KEY,
    `order_status_name` VARCHAR(20)
);


CREATE TABLE method_payment
(
    `method_payment_id`   INT AUTO_INCREMENT PRIMARY KEY,
    `method_payment_name` VARCHAR(50)
);

CREATE TABLE `order`
(
    `order_id`          INT AUTO_INCREMENT PRIMARY KEY,
    `account_id`        INT         NOT NULL,
    `total`             DOUBLE      NOT NULL,
    `delivery_address`  TEXT        NOT NULL,
    `method_payment_id` INT         NOT NULL,
    `order_status_id`   INT         NOT NULL,
    `customer_name`     VARCHAR(45) NOT NULL,
    `customer_phone`    VARCHAR(45) NOT NULL,
    `customer_birthday` DATE        NOT NULL,
    `customer_email`    VARCHAR(45) NOT NULL,
    `create_at`         DATETIME    NOT NULL,
    `modify_at`         DATETIME    NOT NULL,
    `tax`               DOUBLE      NOT NULL,
    `shipping_fee`      DOUBLE      NOT NULL,
    `note`              TEXT,
    FOREIGN KEY (`order_status_id`) REFERENCES `order_status` (`order_status_id`),
    FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`),
    FOREIGN KEY (`method_payment_id`) REFERENCES `method_payment` (`method_payment_id`)
);

CREATE TABLE `order_item`
(
    `order_item_id` INT AUTO_INCREMENT PRIMARY KEY,
    `order_id`      INT    NOT NULL,
    `food_id`       INT    NOT NULL,
    `price`         DOUBLE NOT NULL,
    `discount`      DOUBLE NOT NULL,
    `quantity`      INT    NOT NULL,
    FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`),
    FOREIGN KEY (`food_id`) REFERENCES `food` (`food_id`)
);

INSERT INTO `category` (`category_name`)
VALUES ('Food'),
       ('Cakes'),
       ('Street Food'),
       ('Hotpot'),
       ('Rice box'),
       ('Drink'),
       ('Dessert'),
       ('Pizza'),
       ('Sushi'),
       ('Vegetable'),
       ('Homemade'),
       ('Chicken'),
       ('Noodles');

INSERT INTO `food_status` (`food_status_name`)
VALUES ('Available'),
       ('Out Of Stock'),
       ('Not available');


INSERT INTO `food` (`food_name`, `food_price`, `food_status_id`, `food_image`, `food_time_post`, `food_description`,
                    `food_update_time`, `discount`)
VALUES ('Bún đậu thập cẩm', 30000, 1,
        'https://images.foody.vn/res/g90/892174/s570x570/38618d95-9f99-44aa-9ee7-ee0adeb1d96d.jpg',
        '2021-05-28 00:00:00', 'Bún, đậu, thịt heo luộc, chả cốm.', '2021-05-29 00:00:00', 0),
       ('Tokboki truyền thống', 40000, 1,
        'https://images.foody.vn/res/g96/954538/s570x570/6c88fbf2-236b-45d0-8ff4-c60eb683-67523edd-210413100156.jpeg',
        '2021-05-28 02:00:00', 'abc', '2021-05-30 00:00:00', 5),
       ('Nước ép cóc', 50000, 1,
        'https://images.foody.vn/res/g96/954538/s570x570/c67b04c6-f105-4482-947b-7e31bf2ed526.jpg',
        '2021-05-28 04:00:00', 'abc', '2021-05-31 00:00:00', 10),
       ('Mỳ tương đen', 60000, 1,
        'https://images.foody.vn/res/g96/954538/s570x570/210c2176-e76f-4208-a4e0-06c0a9f859a8.jpg',
        '2021-05-28 06:00:00', 'abc', '2021-06-01 00:00:00', 15),
       ('Lẩu tokboki hải sản', 70000, 1,
        'https://images.foody.vn/res/g96/954538/s570x570/4de4a2ad-9b0d-423d-bf3f-0e2961b05423.jpg',
        '2021-05-28 08:00:00', 'abc', '2021-06-02 00:00:00', 20),
       ('Chả cá Hàn Quốc sốt cay', 80000, 1,
        'https://images.foody.vn/res/g96/954538/s570x570/7fe19346-84c2-4edc-8907-dc44f1c9ad46.jpg',
        '2021-05-28 10:00:00', 'v', '2021-06-03 00:00:00', 30),
       ('Há cảo hấp', 90000, 1,
        'https://images.foody.vn/res/g96/954538/s570x570/5adb7cf9-0a04-494b-8135-b32cddd09c49.jpg',
        '2021-05-28 12:00:00', 'v', '2021-06-04 00:00:00', 0),
       ('Kimbap phomai', 100000, 1,
        'https://images.foody.vn/res/g96/954538/s570x570/6a28734b-c287-4874-95a7-cf19e71b6ee9.jpg',
        '2021-05-28 14:00:00', 'abc', '2021-06-05 00:00:00', 5),
       ('Ram cuốn cải', 110000, 1,
        'https://images.foody.vn/res/g96/954538/s570x570/e6318c67-438d-410b-9839-3e9629b92896.jpg',
        '2021-05-28 16:00:00', 'abc', '2021-06-06 00:00:00', 5),
       ('Nem chua rán', 120000, 1,
        'https://images.foody.vn/res/g96/954538/s570x570/78613cf0-c7f3-410a-a921-debbdc5b6928.jpg',
        '2021-05-28 18:00:00', 'v', '2021-06-07 00:00:00', 7),
       ('Đùi gà rán', 130000, 1,
        'https://images.foody.vn/res/g96/954538/s570x570/cf555bc0-20b6-4f24-8a29-c600700f42db.jpg',
        '2021-05-28 20:00:00', 'abc', '2021-06-08 00:00:00', 10),
       ('Sữa chua sinh tố dâu', 140000, 1,
        'https://images.foody.vn/res/g96/954538/s570x570/d22b837c-81c3-4223-a101-fc9f2821238d.jpg',
        '2021-05-28 22:00:00', 'abc', '2021-06-09 00:00:00', 20),
       ('Sữa chua thạch', 150000, 1,
        'https://images.foody.vn/res/g96/954538/s570x570/18637f39-c998-4732-8a2e-cdaa1878647b.jpg',
        '2021-05-29 00:00:00', 'abc', '2021-06-10 00:00:00', 0),
       ('Sữa chua trân châu', 160000, 1,
        'https://images.foody.vn/res/g96/954538/s570x570/c2a6271c-94dd-4d70-a254-a88fdab50105.jpg',
        '2021-05-29 02:00:00', 'abc', '2021-06-11 00:00:00', 0);

INSERT INTO `food_category` (food_id, category_id)
VALUES (1, 1),
       (1, 2),
       (2, 2),
       (2, 3),
       (3, 4),
       (4, 5),
       (5, 2),
       (6, 3),
       (7, 4),
       (8, 4),
       (9, 2),
       (10, 1),
       (11, 1),
       (12, 2),
       (12, 2),
       (13, 2);

INSERT INTO `customer` (customer_name, customer_phone, customer_birthday, customer_avatar,
                        customer_address)
VALUES ('Phuc', '0935507895', '1992-12-08',
        'https://thuthuatnhanh.com/wp-content/uploads/2018/07/anh-avatar-dep-doc-dao-nhat-4.jpg',
        '195 Nguyen Luong Bang, Lien Chieu, Daang');

INSERT INTO `account_status` (account_status_name)
VALUES ('Enable'),
       ('Disable');

INSERT INTO `account`(account_name, account_password, account_email, account_status_id, account_register_time,
                      account_login_time, customer_id)
VALUES ('phuc', '$2y$12$sq6D3qRV4/G7FSCIFI7Q0uVVM1GjKy4ubMGRY.xX/o5A0j1d/k4EC', 'truongphucdn@gmail.com', 1,
        '1992-12-08', '2021-12-02', 1);

INSERT INTO `role` (role_name)
VALUES ('ADMIN'),
       ('USER');

INSERT INTO `account_role` (account_id, role_id)
VALUES (1, 2);

INSERT INTO rating (rating_level, food_id, account_id)
VALUES (3, 1, 1),
       (2, 3, 1),
       (1, 4, 1),
       (5, 5, 1),
       (2, 6, 1),
       (3, 7, 1),
       (4, 8, 1);

