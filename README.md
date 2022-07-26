# Suspendable CrudRepositories for Spring-Boot

This repo contains wrapper classes for Spring-Boot CrudRepositories with a suspendable API for use with Kotlin
Coroutines.
This is achieved my moving the execution context of the repository calls to a different `CoroutineDispatcher`.

Copy-paste the classes from this repository to your project.

Usage as follows for both `SuspendableCrudRepository` and `SuspendableJpaRepository`:

```kotlin
// Define classic blocking repository first.
// Use this only as autowired bean in your actual repository component further below.
interface BlockingMyEntityRepository : CrudRepository<MyEntity, Long>

// Define the repository you are actually going to use 
// as a @Component annotated class extending from SuspendableCrudRepository.
@Component
class MyEntityRepository(
    @Autowired
    override val internalRepo: BlockingMyEntityRepository,
) : SuspendableCrudRepository<MyEntity, Long> {
    override val dispatcher: CoroutineDispatcher = Dispatchers.IO // Define your custom Dispatcher here or inject as bean as well.
}
```

If you require custom methods or methods annotated with `@Query`, you will have to define them in `BlockingMyEntityRepository` as usual and add a `suspend override fun ...(...)` with the save method signature to `MyEntityRepository` that uses `withContext(dispatcher)` to call the blocking method in `BlockingMyEntityRepository`, just as done everywhere in `SuspendableCrudRepository`.
