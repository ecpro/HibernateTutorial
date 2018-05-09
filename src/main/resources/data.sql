insert into course(id, name, created_date, last_updated_date)
values(10001, 'Data Structures', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date)
values(10002, 'Algorithms', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date)
values(10003, 'Concurrency', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date)
values(10004, 'Multithreading', sysdate(), sysdate());


insert into review(id, rating, description, course_id)
values(30001, 5, 'Good course', 10001);
insert into review(id, rating, description, course_id)
values(30002, 4, 'Excellent course', 10001);
insert into review(id, rating, description, course_id)
values(30003, 5, 'Brilliant course', 10002);
insert into review(id, rating, description, course_id)
values(30004, 5, 'Not so good course', 10003);

insert into passport(id, number)
values(20001, 'A3454');
insert into passport(id, number)
values(20002, 'S6532');
insert into passport(id, number)
values(20003, 'Q4267');
insert into passport(id, number)
values(20004, 'W6426');

insert into student(id, name, passport_id)
values(40001, 'Ram', 20001);
insert into student(id, name, passport_id)
values(40002, 'Shyam', 20002);
insert into student(id, name, passport_id)
values(40003, 'Ghanshayam', 20003);
insert into student(id, name, passport_id)
values(40004, 'Tukaram', 20004);


insert into student_course(student_id, course_id)
values(40001, 10001);
insert into student_course(student_id, course_id)
values(40001, 10002);
insert into student_course(student_id, course_id)
values(40002, 10004);
insert into student_course(student_id, course_id)
values(40003, 10001);
insert into student_course(student_id, course_id)
values(40004, 10003);


insert into person(id, name, age, pincode, street, city)
values(1, 'John', 33, '411027', 'Vishal Nagar', 'Pune');


insert into guide(id, name)
values(101, 'Josh');
insert into guide(id, name)
values(102, 'Gosh');

insert into tourist(id, name, guide_id)
values (201, 'Tour1', 102);
insert into tourist(id, name, guide_id)
values (202, 'Tour2', 102);
insert into tourist(id, name, guide_id)
values (203, 'Tour3', 101);
insert into tourist(id, name, guide_id)
values (204, 'Tour4', 102);
insert into tourist(id, name, guide_id)
values (205, 'Tour5', null);
insert into tourist(id, name, guide_id)
values (206, 'Tour6', null);




