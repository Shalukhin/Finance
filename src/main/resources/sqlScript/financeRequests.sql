CREATE DATABASE IF NOT EXISTS finance;

CREATE TABLE IF NOT EXISTS `finance`.`user` (
`id` INT NOT NULL AUTO_INCREMENT, 
`login` VARCHAR(45) NULL DEFAULT NULL, 
`password` VARCHAR(45) NULL DEFAULT NULL, 
`role` VARCHAR(45) NULL DEFAULT NULL, 
PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `finance`.`item`(
`id` INT NOT NULL auto_increment, 
`idUser` INT NOT NULL, 
`date` VARCHAR(45) NULL, 
`amountCoins` BIGINT(20) NULL, 
`kind` VARCHAR(45) NULL, 
PRIMARY KEY (`id`), 
CONSTRAINT `rel1` FOREIGN KEY(`idUser`) REFERENCES `finance`.`user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE);

INSERT INTO `finance`.`user` VALUES ('1', 'Vasiliy', '112358', 'admin');

INSERT INTO `finance`.`user`(`id`, `login`, `password`, `role`) VALUES ('2', 'Jena', '123456', 'user');

INSERT INTO `finance`.`user` VALUES ('3', 'mama', '654321', 'user');  

INSERT INTO `finance`.`item` VALUES ('1', '1', '2019.12.06', '5000', 'food'); 

INSERT INTO `finance`.`item` VALUES ('2', '1', '2019.12.11', '10000', 'computer'); 

INSERT INTO `finance`.`item` VALUES ('3', '1', '2019.12.15', '900', 'food'); 

INSERT INTO `finance`.`item` VALUES ('4', '2', '2019.12.08', '2300', 'family'); 

INSERT INTO `finance`.`item` VALUES ('5', '2', '2019.12.15', '500', 'cat'); 

INSERT INTO `finance`.`item` VALUES ('6', '2', '2019.12.15', '4000', 'food'); 

UPDATE item SET `amountCoins`='11000' WHERE `id`='2';

UPDATE item SET `amountCoins`='3000' WHERE `kind`='food' AND `idUser`='2';

DELETE FROM user WHERE `id`=3;