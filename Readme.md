# Micronaut Data JPA demo project

The micronaut data jpa framework has problems to create queries if the properties using in the queries are 
named starting with `in`.

This project demonstrates the problem in the `SomeEntityRepository`. The gradle build fails:

```
micronaut-data-query-demo\src\main\java\micronaut\data\query\demo\repository\SomeEntityRepository.java:15: error: Unable to implement Repository method: SomeEntityRepository.findByInternetNumberInList(List internetNumbers). No property name specified in clause: In
    Flowable<SomeEntity> findByInternetNumberInList(List<Long> internetNumbers);
```

## Prerequisites

To build (and see the problem) you need:

- JDK 11
- Gradle 6