create database student_management;

use student_management;

CREATE TABLE admin(
	id int NOT NULL auto_increment,
    username varchar(40) NOT NULL,
    password varchar(40) NOT NULL,
    primary key (id)
);

select * from admin;

create table student(
	id int NOT NULL auto_increment,
    name varchar(150) not null,
    date_of_birth date not null,
    gender varchar(10) not null,
    email varchar(100) not null,
    phone varchar(15) not null,
    father_name varchar(150) not null,
    mother_name varchar(150) not null,
    address1 text not null,
    address2 text not null,
    image_path varchar(200) not null,
    primary key(id)
);

create table course(
	id int not null auto_increment,
    student_id int default null,
    semester int default null,
    course1 varchar(200) not null,
    course2 varchar(200) not null,
    course3 varchar(200) not null,
    course4 varchar(200) not null,
    course5 varchar(200) not null,
    primary key (id),
    key fk_student_id ( student_id),
    constraint fk_student_id foreign key ( student_id) references student (id) on delete cascade
);
create table score(
	id int not null auto_increment,
    student_id int default null,
    semester int default null,
    course1 varchar(200) not null,
    score1 double not null,
    course2 varchar(200) not null,
    score2 double not null,
    course3 varchar(200) not null,
    score3 double not null,
    course4 varchar(200) not null,
    score4 double not null,
    course5 varchar(200) not null,
    score5 double not null,
    primary key (id),
    key fk_std_id ( student_id),
    constraint fk_std_id foreign key ( student_id) references student (id) on delete cascade
);