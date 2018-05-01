#Hibernate Notes

No EntityManager with actual transaction available for current thread Exception : Mark Class as transactional

Whenever you are making any change in db it will always go under a transaction.

***When does hibernate sends updates to database ?***

```Java
@Transactional
public void save() {
    em.persists(course1);
    em.persists(course2); // this just will generate id from  a sequence and data is not inserted in db at this moment

    // em.flush(); // if you use this method then it will save current state of course1 and course2 to db but not commit. Commit will always be at the end.

    // further changes in course1
    // further changes in course2

} // now data is saved and committed if every thing goes right else rollback
```

***Do readonly methods require a transaction?***
Yes sometimes we do. Take for example two entities/table : User and Comments (one to many relationship here)
Assume we are using lazy fetching here to fetch comments.

```Java
public List<Comments> readOnlyTransactionExample() {
    User user = entityManager.find(User.class, 20001L); // creates and ends one transaction here
    List<Comments> comments = user.getComments(); // will throw exception related to invalid session as no transaction exists
    return comments;
}
```
If we mark the above method with @Transactional then user.getComments() is also in the transaction and a db call is made to
populate the comments. (In eager fetch comments are populated during user fetch itself)

***When do we need @Transactional and @DirtiesContext in unit tests and what's the difference ?***

----------
In examples we are using tables as Course, Student, Review and Passport
- Course and Review have one to many relationship
- Student and Passport have one to one relationship
- Course and Student have one to many relationship
- Student and Course have one to many relationship

> One to one relationships are always eager fetch

*Try to learn more about lazy fetch in hibernate*

**Persistance Context**

At the start of each transaction a persistance context is created which keeps track of all the state changes.
The context vanishes at the end of transaction. For example:

```Java
    @Autowired
    EntityMananger em;

@Transactional // ---- > creates one persistance context for the whole method. This is equivalent to a session.
public void multipleDBOperation() {
    Student student = em.find(Student.class, 40001L); //operation 1
    //persistance context (student)

    Passport passport = student.getPassport(); // operation 2
    //persistance context (student, passport)

    student.setName("Piyush"); // operation 3
    // persistance context (student updated value, passport)

    passport.setNumber("E4234"); // operation 4
    // //persistance context (student updated value, passport updated value)

}
// after the method ends execution persistance context vanishes.
//Without @Transactional each opeation creates a new transaction with their own persistance context which vanishes after each individual operation.
```

















