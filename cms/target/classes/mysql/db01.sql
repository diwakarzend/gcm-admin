CREATE TABLE `cms_database`.`user_table` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NULL DEFAULT NULL,
  `user_name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `status` BIT(1) NOT NULL DEFAULT b'1',
  `phone` VARCHAR(45) NOT NULL,
  `created_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`));

  ALTER TABLE `cms_database`.`user_table` 
ADD UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC) VISIBLE,
ADD UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE;
;
