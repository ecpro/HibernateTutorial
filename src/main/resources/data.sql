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