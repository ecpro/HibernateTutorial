# Hibernate Notes

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
- Course >-- one to many --> Review
- Student -- one to one -- Passport
- Course <-- many to many --> Student

> (one/many) to one relationships are always eager fetch by default
> (one/many) to many relationships are always lazy fetch by default

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

***JPA Inheritance Hierarchies and Mappings - How to Choose?***


***Difference between dirty read, non-repeatable read and phantom reads***


***Difference between 4 isoliation levels***

1. Read Committed
2. Read Uncommitted
3. Repeatable Reads
4. Serializable

***What is right isolation level for your application?***

***First level cache***
First level cache runs within the boundary of a transaction. If same query is fired twice within a transactional,
only the first time it converts it into actual sql query, the second time it retrives it from the first level cache.

**Aggregation v/s Composition**

Both defines relationship between objects. Aggregation is week relationship where as composition is strong.
For example, a typical flight has pilots, crew members, a plane, passangers, flight schedule, destinations, etc.
If you observe, you'll find that each of these object can exist independently. Same pilot/passanger/crew can be in differnt flights at different times. Differnt flight can have same set of destinations. A plane can be used for rescue operation or commercial operation or can be retrofitted for military or scientic use. A crew member can switch airline company. None of these objects are strongly coupled to a flight. Hence a flight is a aggregation of these objects.

On the other hand take one of the object such as plane from the above example. A plane is composed of wings, engine, fuselage, etc. None of these components have a meaningful independent existance. A jet engine can only be fitted to a plane. A car cannot have wings or a bus cannot have a fuselage. These objects only have a meaning in context of a plane. Hence a plane is a example of compostion.

Entity and Value Type Object in ORM ?

Entity types have an identity (primary key) and a separate table exists for them. Value types are just objects whose fields
translated to columns in a table.

Take a person table composed of id, name, age, pincode, street and city as columns.

<div>
    <table>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>age</th>
            <th>pincode</th>
            <th>street</th>
            <th>city</th>
        </tr>
        <tr align="center">
            <td>23</td>
            <td>Joen</td>
            <td>23</td>
            <td>411027</td>
            <td>Vishal Nager</td>
            <td>Pune</td>
        </tr>
        <tr align="center">
            <td>23</td>
            <td>Joen</td>
            <td>23</td>
            <td>411027</td>
            <td>Vishal Nager</td>
            <td>Pune</td>
        </tr>
    </table>
</div>

The corresponding java class will be as shown below.
Ofcourse, we can put pincode and street, etc directly but that would be a bad design as they are better composed into a address object.

```Java
class Person {
    String id;
    String name;
    String age;
    Address address;
}

class Address {
    String pincode;
    String street;
    String city;
}
```
Here Person class is of Entity Type and Address is of value type. With Hibernate annotation the classes will look like.

```Java
@Entity // mark Person as entity
class Person {
    @Id @GeneratedValue
    String id; // primary key

    String name;
    String age;

    @Embedded
    Address address;
}

@Embeddable
class Address {
    String pincode;
    String street;
    String city;
}
```
Do all persisent classes have their own database identity ?
- Value types does not have their own identity. Its clear from above example.

**Composite Primary Key**
- Generally we have a id column in table that represent primary key, the corresponding object field is annotated with
@Id. But what about the case when we use composite key as primary key. For Instance, if firstName and lastName together constitutes a primary key. In such situation we do the following.
```Java
@Embeddable
class PersonPrimaryKey {
    String firstName;
    String lastName;
    public PersonPrimaryKey() {}
    /* don't forget to override equals() and hashcode() methods for java side key comparision */
}

@Entity
class Person {
    @EmbeddedId
    PersonPrimaryKey key; // this is how multiple columns are made primary key in hibernate
}
```
*Composite keys are not recommended to be used as primary keys. Even Business keys such ss isbn or ssn are unique can can be used as primary key but also not recommended as they have other meaning other than uniquely identifying a record. Primary keys should not any business meaning form a good design perspective. That's why auto generated unique ids are used as primary keys, also called synthetic identifiers (no business meaning)*

**Composite Foreign Key**

















