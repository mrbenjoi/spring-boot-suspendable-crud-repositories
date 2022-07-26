package adaptme.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull
import java.util.*

interface SuspendableCrudRepository<T : Any, ID : Any, R : CrudRepository<T, ID>> {

    val internalRepo: R
    val dispatcher: CoroutineDispatcher

    suspend fun <S : T> save(entity: S): S = withContext(dispatcher) {
        internalRepo.save(entity)
    }

    suspend fun <S : T> saveAll(entities: MutableIterable<S>): MutableIterable<S> = withContext(dispatcher) {
        internalRepo.saveAll(entities)
    }

    suspend fun findById(id: ID): Optional<T> = withContext(dispatcher) {
        internalRepo.findById(id)
    }

    suspend fun findByIdOrNull(id: ID): T? = withContext(dispatcher) {
        internalRepo.findByIdOrNull(id)
    }

    suspend fun existsById(id: ID): Boolean = withContext(dispatcher) {
        internalRepo.existsById(id)
    }

    suspend fun findAll(): MutableIterable<T> = withContext(dispatcher) {
        internalRepo.findAll()
    }

    suspend fun findAllById(ids: Iterable<ID>): MutableIterable<T> = withContext(dispatcher) {
        internalRepo.findAllById(ids)
    }

    suspend fun count(): Long = withContext(dispatcher) {
        internalRepo.count()
    }

    suspend fun deleteById(id: ID) = withContext(dispatcher) {
        internalRepo.deleteById(id)
    }

    suspend fun delete(entity: T) = withContext(dispatcher) {
        internalRepo.delete(entity)
    }

    suspend fun deleteAllById(ids: Iterable<ID>) = withContext(dispatcher) {
        internalRepo.deleteAllById(ids)
    }

    suspend fun deleteAll(entities: Iterable<T>) = withContext(dispatcher) {
        internalRepo.deleteAll(entities)
    }

    suspend fun deleteAll() = withContext(dispatcher) {
        internalRepo.deleteAll()
    }

}
