create SCHEMA IF NOT EXISTS `solodovnykov` DEFAULT CHARACTER SET utf8;
USE `solodovnykov`;

drop table IF EXISTS phone;
drop table IF EXISTS guest;
drop table IF EXISTS room;
drop table IF EXISTS hotel;
drop table IF EXISTS country;
drop table IF EXISTS review;


create TABLE IF NOT EXISTS `solodovnykov`.`review`
(
    `id`   INT        NOT NULL AUTO_INCREMENT,
    `text` TEXT(1000) NOT NULL,
    `date` DATE       NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;



create TABLE IF NOT EXISTS `solodovnykov`.`country`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;



create TABLE IF NOT EXISTS `solodovnykov`.`hotel`
(
    `id`         INT         NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(45) NOT NULL,
    `review_id`  INT         NOT NULL,
    `country_id` INT         NOT NULL,
    PRIMARY KEY (`id`, `review_id`, `country_id`),
    INDEX `fk_hotel_review1_idx` (`review_id` ASC) VISIBLE,
    INDEX `fk_hotel_country1_idx` (`country_id` ASC) VISIBLE,
    CONSTRAINT `fk_hotel_review1`
        FOREIGN KEY (`review_id`)
            REFERENCES `solodovnikov_new_2`.`review` (`id`),
    CONSTRAINT `fk_hotel_country1`
        FOREIGN KEY (`country_id`)
            REFERENCES `solodovnikov_new_2`.`country` (`id`)
)
    ENGINE = InnoDB;


create TABLE IF NOT EXISTS `solodovnykov`.`room`
(
    `id`              INT           NOT NULL AUTO_INCREMENT,
    `max_persons`     TINYINT(5)    NOT NULL,
    `price_per_night` DECIMAL(5, 2) NOT NULL,
    `status`          TINYINT(1)    NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


create TABLE IF NOT EXISTS `solodovnykov`.`guest`
(
    `id`               INT         NOT NULL AUTO_INCREMENT,
    `first_name`       VARCHAR(45) NOT NULL,
    `last_name`        VARCHAR(45) NOT NULL,
    `state_of_account` TINYINT(1)  NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;



create TABLE IF NOT EXISTS `solodovnykov`.`phone`
(
    `id`     INT         NOT NULL AUTO_INCREMENT,
    `number` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;

insert into review (id, text, date)
values (1, 'Some text 1', '2020-10-10'),
       (2, 'Some text 2', '2020-10-11'),
       (3, 'Some text 3', '2020-10-12'),
       (4, 'Some text 4', '2020-10-13'),
       (5, 'Some text 5', '2020-10-14'),
       (6, 'Some text 6', '2020-10-15'),
       (7, 'Some text 7', '2020-10-16'),
       (8, 'Some text 8', '2020-10-17'),
       (9, 'Some text 9', '2020-10-18');

insert into country (id, name)
values (1, 'Country 1'),
       (2, 'Country 2'),
       (3, 'Country 3'),
       (4, 'Country 4'),
       (5, 'Country 5'),
       (6, 'Country 6'),
       (7, 'Country 7'),
       (8, 'Country 8'),
       (9, 'Country 9');

insert into hotel (id, name, review_id, country_id)
values (1, 'Hotel 1', 1, 1),
       (2, 'Hotel 2', 2, 2),
       (3, 'Hotel 3', 3, 3),
       (4, 'Hotel 4', 4, 4),
       (5, 'Hotel 5', 5, 5),
       (6, 'Hotel 6', 6, 6),
       (7, 'Hotel 7', 7, 7),
       (8, 'Hotel 8', 8, 8),
       (9, 'Hotel 9', 9, 9);

insert into room (id, max_persons, price_per_night, status)
values (1, 100, 100, 1),
       (2, 101, 101, 1),
       (3, 102, 102, 1),
       (4, 103, 103, 1),
       (5, 104, 104, 1),
       (6, 105, 105, 1),
       (7, 106, 106, 1),
       (8, 107, 107, 1),
       (9, 108, 108, 1);

insert into guest (id, first_name, last_name, state_of_account)
values (1, 'Name 1', 'Last Name 1', 1),
       (2, 'Name 2', 'Last Name 2', 1),
       (3, 'Name 3', 'Last Name 3', 1),
       (4, 'Name 4', 'Last Name 4', 1),
       (5, 'Name 5', 'Last Name 5', 1),
       (6, 'Name 6', 'Last Name 6', 1),
       (7, 'Name 7', 'Last Name 7', 1),
       (8, 'Name 8', 'Last Name 8', 1),
       (9, 'Name 9', 'Last Name 9', 1);

insert into phone (id, number)
values (1, '11111111111'),
       (2, '22222222222'),
       (3, '33333333333'),
       (4, '44444444444'),
       (5, '55555555555'),
       (6, '66666666666'),
       (7, '77777777777'),
       (8, '88888888888'),
       (9, '99999999999');
