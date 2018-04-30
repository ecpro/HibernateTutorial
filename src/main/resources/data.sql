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
values(1, 'Ram', 20001);
insert into student(id, name, passport_id)
values(2, 'Shyam', 20002);
insert into student(id, name, passport_id)
values(3, 'Ghanshayam', 20003);
insert into student(id, name, passport_id)
values(4, 'Tukaram', 20004);




