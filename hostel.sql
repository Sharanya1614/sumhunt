-- hostel_food_delivery.hostel definition

CREATE TABLE `hostel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


-- hostel_food_delivery.mst_meal_type definition

CREATE TABLE `mst_meal_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(100) NOT NULL,
  `delivery_charge` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `meal_type_un` (`type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


-- hostel_food_delivery.mst_covid_status definition

CREATE TABLE `mst_covid_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


-- hostel_food_delivery.student definition

CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stud_code` varchar(50) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `institution_name` varchar(100) DEFAULT NULL,
  `yearofstudy` varchar(50) DEFAULT NULL,
  `host_id` int(11) DEFAULT NULL,
  `room_number` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `student_un` (`stud_code`),
  KEY `student_host_id_fk` (`host_id`),
  CONSTRAINT `student_host_id_fk` FOREIGN KEY (`host_id`) REFERENCES `hostel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


-- hostel_food_delivery.orders definition

CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stud_id` int(11) DEFAULT NULL,
  `meal_id` int(11) DEFAULT NULL,
  `order_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `orders_stud_id_fk` (`stud_id`),
  KEY `orders_meal_id_fk` (`meal_id`),
  CONSTRAINT `orders_meal_id_fk` FOREIGN KEY (`meal_id`) REFERENCES `mst_meal_type` (`id`),
  CONSTRAINT `orders_stud_id_fk` FOREIGN KEY (`stud_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- hostel_food_delivery.payment definition

CREATE TABLE `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stud_id` int(11) DEFAULT NULL,
  `number_of_meal` int(11) DEFAULT NULL,
  `total_payment` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `payment_stud_id_fk` (`stud_id`),
  CONSTRAINT `payment_stud_id_fk` FOREIGN KEY (`stud_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- hostel_food_delivery.quarantine definition

CREATE TABLE `quarantine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stud_id` int(11) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `covid_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `quarantine_stud_id_fk` (`stud_id`),
  KEY `quarantine_covid_status_fk` (`covid_status`),
  CONSTRAINT `quarantine_covid_status_fk` FOREIGN KEY (`covid_status`) REFERENCES `mst_covid_status` (`id`),
  CONSTRAINT `quarantine_stud_id_fk` FOREIGN KEY (`stud_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;



INSERT INTO hostel_food_delivery.hostel (name) VALUES
	 ('Hostel1'),
	 ('Hostel2');
	 
INSERT INTO hostel_food_delivery.mst_covid_status (status) VALUES
	 ('confirmed'),
	 ('negative'),
	 ('awaiting result');

INSERT INTO hostel_food_delivery.mst_meal_type (type_name,delivery_charge) VALUES
	 ('breakfast',20.00),
	 ('lunch',20.00),
	 ('hi-tea',20.00),
	 ('dinner',20.00);
	 
INSERT INTO hostel_food_delivery.quarantine (stud_id,start_date,end_date,covid_status) VALUES
	 (1,'2021-06-30 10:00:00.0',NULL,1),
	 (3,'2021-06-30 15:00:00.0','2021-07-03 08:00:00.0',2),
	 (2,'2021-07-04 08:00:00.0',NULL,1);
	 
INSERT INTO hostel_food_delivery.student (stud_code,name,institution_name,yearofstudy,host_id,room_number) VALUES
	 ('s1','sharan','psg','2nd year',1,'101'),
	 ('s2','priya','psg','2nd year',1,'102'),
	 ('s3','suji','psg','2nd',2,'103'),
	 ('s4','Gokul','cit','3rd',2,'110');