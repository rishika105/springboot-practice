Springboot

\------------

Apache Tomcat - it is a free, open-source web server and servlet container that runs Java-based web applications



Maven - dependency management and build tool



Dependencies

Spring web - can create web server. uses apache tomcat as the default embedded container.



Beans - java objects maintained by the spring container



Annotations - tells spring/Ioc (inversion of control) container what to do. 

ex:

@Component

Spring framework/ IOC scans this and make a bean and store it.

@SpringbootApplication - entry point / only once in one appl.



Lombok - Project Lombok is a Java library that uses annotations to automatically generate boilerplate code like getters, setters, and constructors at compile time



Spring Data JPA is a powerful Spring framework module that drastically reduces boilerplate code by abstracting the data access layer on top of JPA (Jakarta Persistence API) and Hibernate ORM - means Instead of writing raw SQL or managing complex entity managers manually, you simply define interface repositories, and Spring Boot generates the underlying database implementation automatically at runtime



JPA - it is a specification that facilitates object relationship mapping in JPA



Hibernate - it is an implementation of JPA and it generates SQL queries

(we have other implementations too)



JDBC - (Java Database connection) sql queries are executed by jdbc which connects to the database





Internal working of springboot

\------------------------------

when you click run -> IOC container runs it tries to create application context -> this context has bunch of beans(it supplies it when needed) + add some external configurations you have provided like (environment variables or properties files)



\- uses component scanning to create beans (@component create automatic bean ..@service, @controller comes under it) 

\- we can also create bean by using @bean annotation

\- it recursively scans all files comes under that package





\-what makes spring framework a springboot appl.

@EnableAutoConfiguration -> auto download files and do all config

@SpringbootApplication -> ctrl + click to see details





How does a web server works in springboot

\------------------------------------------

client --http req-->

&#x20;tomcat(webserver) --map to the servelet-->

&#x20;DispatcherServlet ( it creates http servlet request and response objects, then delegates the request to handlemapping like @getmapping("/") , the controller method is invoked -->

controller(call, validation through dto) ->service(business logic) -> repo ->db -->

httpMessageConvertor --> JSON serialization (convert java object(POJO) to json)



Rest APIs

\----------



@Controller: Marks a class as a web controller capable of handling UI views (like JSP or HTML templates).



@RestController: A specialized version that combines @Controller and @ResponseBody. It skips view resolution and writes data (like JSON or XML strings) directly into the HTTP response body—ideal for REST APIs.



@RequestMapping: Maps specific web URLs onto entire classes or individual methods. Shortcut variations include @GetMapping, @PostMapping, @PutMapping, and @DeleteMapping



@Entity annotation in Spring Boot (via Jakarta Persistence/JPA) specifies that a Java class is a persistent entity mapped directly to a database table





Lombok Annotations

\-------------------------

@Getter / @Setter: Generates standard getter and setter methods for fields or entire classes.



@ToString: Generates a default toString() method listing class fields.



@EqualsAndHashCode: Generates equals() and hashCode() implementations from the object's fields.



@NoArgsConstructor, @AllArgsConstructor, and @RequiredArgsConstructor: Generates constructors with no arguments, all arguments, or required fields ( final and not null fields), respectively.



@Data: Acts as a convenient shortcut bundle that combines @Getter, @Setter, @ToString, @EqualsAndHashCode, and @RequiredArgsConstructor.



@Builder: Implements the builder pattern for flexible and readable object creation.



@Slf4j: Automatically generates an SLF4J logger field named log inside your class





application-properties

\-------------------------



\--spring.jpa.hibernate.ddl-auto=validate

&#x20;It only validates that the columns and tables defined in your Java entities exist in the database. If Hibernate detects a critical mismatch, it will throw a SchemaManagementException (or SchemaValidationException) and prevent the application from starting

\# if entity and request structure match it does not stop the operation. used in prod.





| Value         | What it does                                                                   |

| ------------- | ------------------------------------------------------------------------------ |

| `none`        | Does nothing to the database schema                                            |

| `validate`    | Checks whether DB tables match your entities. \*\*Does not change anything\*\*     |

| `update`      | Updates the existing schema to match entities. Usually preserves existing data |

| `create`      | Drops existing tables/schema and creates them again                            |

| `create-drop` | Creates tables when app starts and drops them when app stops                   |





Database related things

\-----------------------

1\. when you are joining columns like @JoinColumn(name = "id", nullable = false)

if both column name have name id and the table you are joining from outside to here(means the one you are referencing to)

is a primary key with same name to current table column u wnat to join. no need to provide referencedColumnName.



⚠️ Important: If `VENDOR\_ID` in the other table is \*\*not the primary key\*\*, then you should specify:



```java

@JoinColumn(

&#x20;   name = "VENDOR\_ID",

&#x20;   referencedColumnName = "VENDOR\_ID",

&#x20;   nullable = false

)





2\. Use @JsonIgnore - json parsing is done by Jackson behind the scenes and if we use this to include this in json.

Note: commonly happen in jpa relationships in manytoone and onetomany use @jsonignore on one side to avoid the infinite recursion while dealing with entities. to avoid completely use dto.



3\. The @Transactional annotation in Spring Boot simplifies database transaction management by wrapping a method or class in a database transaction automatically. It enforces the ACID properties, ensuring an "all-or-nothing" behavior. Means a method if runs fully with all the transactions being success

or completely rollbacks it.



4\. @Transient - used by jpa/hibernate to not store the field in database





\------------------------------

\## Comparing @Configuration vs @Component

While both register beans into the container, they serve distinct architectures:



| Feature | @Configuration | @Component |

|---|---|---|

| Primary Intent | Explicitly defines third-party or custom beans via factory methods. | Automatically detects your custom classes via component scanning. |

| Method Target | Used at the Class level, explicitly pairing with @Bean methods. | Used at the Class level (@Service, @Repository, @Controller). |

| Inter-Bean Calls | Proxied (Full Mode): Multiple method calls safely return the same singleton. | Lite Mode: Direct method calls bypass proxies and create new instances. |



\--------------------------------------



Internal Working of JPA Repository

\----------------------------------



\-so the repo inside that implements all the methods is SimpleJPARepository using the entity manager(it does all the data operation in hibernate).

\-hibernate entity lifecycle



entity(patient p1 = new Patient() ) -> transient -(save, persist, update)-->persistent



transient- temporary state

persistent context - save in memory context before commit synchroznized with db



Entity name PatientTable converted to patient\_table automatically 

use @Table(name = "")



JPA Query Methods

\-----------------



(((Spring Data JPA derived query methods)))

&#x20;  -------------------------------------



You write a method name in your Repository interface, and Spring Data JPA automatically generates the database query from the method name. You don't need to write SQL or JPQL manually.

basic pattern - ReturnType findBy + Property + Condition + OrderBy + Property + Direction

Important: These method names refer to Java entity property names, not necessarily database column



Keyword	Meaning

\---------------

findBy				Find records

And				Both conditions must match

Or				Either condition can match

IgnoreCase			Ignore uppercase/lowercase differences

True / False			Match a boolean property

OrderBy				Sort results

Desc / Asc			Descending / ascending order

Between				Within a range

LessThan / GreaterThan		Compare values

Containing			Contains a string

StartingWith / EndingWith	String starts or ends with a value

In				Match any value in a collection

IsNull / IsNotNull		Check for null values

Top / First			Limit the number of results

Distinct			Return distinct results



ex: // name = ? AND age > ?

List<User> findByNameAndAgeGreaterThan(String name, Integer age);

// name contains a word

findByNameContainingIgnoreCase("raj") can match Rajesh, RAJ Kumar, and Suraj.

// Return the 5 youngest users

List<User> findTop5ByOrderByAgeAsc();





can use complex queries like :---



Order entity has buyer, status, totalAmount, and createdAt properties.

List<Order> findByBuyerAndStatusAndTotalAmountGreaterThanOrderByCreatedAtDesc(

&#x20;   User buyer,

&#x20;   OrderStatus status,

&#x20;   BigDecimal amount

);

Finds orders for a specific buyer, with a specific status and amount above the given value, newest first.



(((JPQL and Native Queries)))

&#x20;  -----------------------

JPQL - Jakarta Persistence Query language is an object-oriented query language. While it looks and feels heavily inspired by standard SQL, JPQL fundamentally differs because it queries JPA entity classes and their Java field attributes, rather than database tables and columns. When executed, the underlying persistence provider (like Hibernate or EclipseLink) automatically translates the JPQL query into the appropriate native SQL dialect for whichever database you are using





🔄 JPQL vs. Native SQL





&#x09;	Feature	JPQL							Native SQL

Query Target	Java Entity Classes (e.g., User)				Database Tables (e.g., tbl\_users)

Portability	Database-agnostic. Runs seamlessly if you switch databases.	Tied to a specific database dialect (MySQL, PostgreSQL, etc.).

Validation	Checked and validated at application startup.			Evaluated at runtime when executed.

Relationships	Traverses relationships naturally using object paths 		Requires manual database JOIN clauses on foreign keys.

&#x09;	(e.g., u.address.city).	















