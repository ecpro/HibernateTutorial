#Hibernate Notes

No EntityManager with actual transaction available for current thread Exception : Mark Class as transactional

Whenevery you are making any change in db it will always go under a transaction.

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

***When do we need @Transactional and @DirtiesContext in unit tests and what's the difference ?***










