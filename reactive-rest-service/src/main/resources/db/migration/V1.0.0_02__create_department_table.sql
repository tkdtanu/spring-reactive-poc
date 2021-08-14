CREATE TABLE `department` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `user_id` INT,
    `name` VARCHAR(100) NOT NULL,
    `location` VARCHAR(100),
    PRIMARY KEY(`id`)
    );