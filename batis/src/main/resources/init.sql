CREATE DATABASE IF NOT EXISTS test CHARACTER SET utf8;

DROP TABLE IF EXISTS `addresses`;
CREATE TABLE `addresses`
(
  addr_id INT(11) NOT NULL AUTO_INCREMENT,
  street VARCHAR(50) NOT NULL,
  city VARCHAR(50) NOT NULL,
  state VARCHAR(50) NOT NULL,
  zip VARCHAR(10) DEFAULT NULL,
  country VARCHAR(50) NOT NULL,
  PRIMARY KEY (addr_id)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `students`;
CREATE TABLE `students`
(
  stud_id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  phone VARCHAR(15) DEFAULT NULL,  
  dob DATE DEFAULT NULL,
  bio LONGTEXT DEFAULT NULL,
  pic BLOB DEFAULT NULL,
  addr_id INT(11) DEFAULT NULL,  
  PRIMARY KEY (stud_id),
  CONSTRAINT fk_students_addr FOREIGN KEY (addr_id) REFERENCES addresses (addr_id)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tutors`;
CREATE TABLE `tutors`
(
  tutor_id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  phone VARCHAR(15) DEFAULT NULL,  
  dob DATE DEFAULT NULL,
  bio LONGTEXT DEFAULT NULL,
  pic BLOB DEFAULT NULL,
  addr_id INT(11) DEFAULT NULL,
  PRIMARY KEY (tutor_id),
  CONSTRAINT fk_tutors_addr FOREIGN KEY (addr_id) REFERENCES addresses (addr_id)  
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses`
(
  course_id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(512) DEFAULT NULL,
  start_date DATE DEFAULT NULL,
  end_date DATE DEFAULT NULL,
  tutor_id INT(11) NOT NULL,
  PRIMARY KEY (course_id),
  CONSTRAINT fk_course_tutor FOREIGN KEY (tutor_id) REFERENCES tutors (tutor_id)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `course_enrollment`;
CREATE TABLE `course_enrollment`
(
  course_id INT(11) NOT NULL,
  stud_id INT(11) NOT NULL,
  PRIMARY KEY (course_id,stud_id),
  CONSTRAINT fk_enrollment_stud FOREIGN KEY (stud_id) REFERENCES students (stud_id),
  CONSTRAINT fk_enrollment_course FOREIGN KEY (course_id) REFERENCES courses (course_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `user_pics`;
CREATE TABLE `user_pics`
(
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) DEFAULT NULL,
  pic BLOB,
  bio LONGTEXT,
  PRIMARY KEY (id)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


LOCK TABLES `addresses` WRITE;
INSERT INTO `addresses` (addr_id,street,city,state,zip,country) VALUES 
 (1,'4891 Pacific Hwy','San Diego','CA','92110','San Diego'),
 (2,'2400 N Jefferson St','Perry','FL','32347','Taylor'),
 (3,'710 N Cable Rd','Lima','OH','45825','Allen'),
 (4,'5108 W Gore Blvd','Lawton','OK','32365','Comanche');
UNLOCK TABLES;

LOCK TABLES `students` WRITE;
INSERT INTO `students` (stud_id,name,email,phone,dob,bio,pic,addr_id) VALUES 
 (1,'Timothy','timothy@gmail.com','123-123-1234','1988-04-25',NULL,NULL,3),
 (2,'Douglas','douglas@gmail.com','789-456-1234','1990-08-15',NULL,NULL,4);
UNLOCK TABLES;

LOCK TABLES `tutors` WRITE;
INSERT INTO `tutors` (tutor_id,name,email,phone,dob,bio,pic,addr_id) VALUES 
 (1,'John','john@gmail.com','111-222-3333','1980-05-20',NULL,NULL,1),
 (2,'Paul','paul@gmail.com','123-321-4444','1981-03-15',NULL,NULL,2);
UNLOCK TABLES;

LOCK TABLES `courses` WRITE;
INSERT INTO `courses` (course_id,name,description,start_date,end_date,tutor_id) VALUES 
 (1,'Quickstart Core Java','Core Java Programming','2013-03-01','2013-04-15',1),
 (2,'Quickstart JavaEE6','Enterprise App Development using JavaEE6','2013-04-01','2013-08-30',1),
 (3,'MyBatis3 Premier','MyBatis 3 framework','2013-06-01','2013-07-15',2);
UNLOCK TABLES;

LOCK TABLES `course_enrollment` WRITE; 
INSERT INTO `course_enrollment` (course_id,stud_id) VALUES 
 (1,1),
 (1,2),
 (2,2);
UNLOCK TABLES;