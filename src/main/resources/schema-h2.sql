CREATE TABLE `customer` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `firstName` varchar(255) default NULL,
  `lastName` varchar(255) default NULL,
  `birthdate` timestamp,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=1;
