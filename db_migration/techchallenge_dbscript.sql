CREATE TABLE `techchallenge`.`speech` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `actual_text` VARCHAR(100) NULL,
  `subject_text` VARCHAR(50) NULL,
  `created_date` DATE NULL,
  `modified_date` DATE NULL,
  PRIMARY KEY (`id`));