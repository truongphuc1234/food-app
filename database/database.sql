DROP DATABASE IF EXISTS the_food_world;
CREATE DATABASE the_food_world;
USE the_food_world;

CREATE TABLE `customer`
(
 `customer_id`       int AUTO_INCREMENT PRIMARY KEY,
 `customer_name`     varchar(45) NOT NULL ,
 `customer_phone`    varchar(45) NOT NULL ,
 `customer_birthday` date NOT NULL ,
 `customer_avatar`   text NULL ,
 `customer_address`  text NOT NULL,
);

CREATE TABLE `account`
(
 `account_id`            int AUTO_INCREMENT PRIMARY KEY,
 `account_name`          varchar(45) NOT NULL ,
 `account_password`      text NOT NULL ,
 `account_email`		 varchar(45) NOT NULL,
 `account_status`        int NOT NULL ,
 `account_register_time` datetime NOT NULL ,
 `account_login_time`    datetime NOT NULL ,
 `customer_id`           int NOT NULL ,
 FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
);

CREATE TABLE `role`
(
 `role_id`   int AUTO_INCREMENT PRIMARY KEY ,
 `role_name` varchar(45) NOT NULL ,
);

CREATE TABLE `account_role`
(
 `account_role_id` int AUTO_INCREMENT PRIMARY KEY,
 `account_id`      int NOT NULL ,
 `role_id`         int NOT NULL ,
 FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`),
 FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
);

CREATE TABLE `food_category`
(
 `food_category_id`   int AUTO_INCREMENT PRIMARY KEY,
 `food_category_name` varchar(45) NOT NULL ,
);

CREATE TABLE `food`
(
 `food_id`            int AUTO_INCREMENT PRIMARY KEY,
 `food_name`          TEXT NOT NULL ,
 `food_price`         double NOT NULL ,
 `food_status`        int NOT NULL ,  
 `food_category_id`   int NOT NULL ,
 `food_image`         text NOT NULL ,
 `food_time_post`     date NOT NULL,
 `number_order`		  int NOT NULL,

 FOREIGN KEY (`food_category_id`) REFERENCES `food_category` (`food_category_id`)
);

CREATE TABLE `rating`
(
 `rating_id`    int AUTO_INCREMENT PRIMARY KEY,
 `rating_level` int NOT NULL ,
 `food_id`      int NOT NULL ,
 `account_id`   int NOT NULL ,
 FOREIGN KEY (`food_id`) REFERENCES `food` (`food_id`),
 FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`)
);

-- ************************************** `comment`
CREATE TABLE `comment`
(
 `comment_id`      int AUTO_INCREMENT PRIMARY KEY,
 `comment_content` text NULL ,
 `comment_image`   text NULL ,
 `food_id`         int NOT NULL ,
 `account_id`      int NOT NULL ,
 `comment_time`    datetime NOT NULL ,
FOREIGN KEY (`food_id`) REFERENCES `food` (`food_id`),
 FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`)
);

CREATE TABLE `cart`
(
 `cart_id`    int AUTO_INCREMENT PRIMARY KEY,
 `account_id` int NOT NULL ,
FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`)
);

-- ************************************** `cart_food`
CREATE TABLE `cart_food`
(
 `cart_food_id` int AUTO_INCREMENT PRIMARY KEY ,
 `cart_id`      int NOT NULL ,
 `food_id`      int NOT NULL ,
 `quantity`     int NOT NULL ,
 `note`         text NULL ,
FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`),
FOREIGN KEY (`food_id`) REFERENCES `food` (`food_id`)
);

CREATE TABLE `order_bill`
(
 `order_id`         int AUTO_INCREMENT PRIMARY KEY ,
 `account_id`       int NOT NULL ,
 `total`            double NOT NULL ,
 `delivery_address` text NOT NULL ,
 `method_payment`   int NOT NULL , 

FOREIGN KEY `fkIdx_119` (`account_id`) REFERENCES `account` (`account_id`)
);

CREATE TABLE `order_food`
(
 `order_food_id` int AUTO_INCREMENT PRIMARY KEY,
 `order_id`      int NOT NULL ,
 `cart_food_id`  int NOT NULL ,
 FOREIGN KEY (`order_id`) REFERENCES `order_bill` (`order_id`),
 FOREIGN KEY (`cart_food_id`) REFERENCES `cart_food` (`cart_food_id`)
);
