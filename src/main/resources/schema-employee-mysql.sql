CREATE TABLE `employee` (
  `id` mediumint(8) unsigned NOT NULL,
  `firstName` varchar(255) default NULL,
  `lastName` varchar(255) default NULL,
  `birthdate` varchar(255),
  `salary` DECIMAL(10,2),
  `department` varchar(10) default NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=1;
