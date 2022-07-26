package adaptme.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.data.repository.query.FluentQuery
import java.util.*
import java.util.function.Function

interface SuspendableJpaRepository<T : Any, ID : Any, R : JpaRepository<T, ID>> {

    val internalRepo: R
    val dispatcher: CoroutineDispatcher

    suspend fun <S : T> save(entity: S): S = withContext(dispatcher) {
        internalRepo.save(entity)
    }

    suspend fun <S : T> saveAll(entities: MutableIterable<S>): MutableList<S> = withContext(dispatcher) {
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

    suspend fun findAll(): MutableList<T> = withContext(dispatcher) {
        internalRepo.findAll()
    }

    suspend fun findAll(sort: Sort): MutableList<T> = withContext(dispatcher) {
        internalRepo.findAll(sort)
    }

    suspend fun <S : T> findAll(example: Example<S>): MutableList<S> = withContext(dispatcher) {
        internalRepo.findAll(example)
    }

    suspend fun <S : T> findAll(example: Example<S>, sort: Sort): MutableList<S> = withContext(dispatcher) {
        internalRepo.findAll(example, sort)
    }

    suspend fun findAll(pageable: Pageable): Page<T> = withContext(dispatcher) {
        internalRepo.findAll(pageable)
    }

    suspend fun <S : T> findAll(example: Example<S>, pageable: Pageable): Page<S> = withContext(dispatcher) {
        internalRepo.findAll(example, pageable)
    }

    suspend fun findAllById(ids: MutableIterable<ID>): MutableList<T> = withContext(dispatcher) {
        internalRepo.findAllById(ids)
    }

    suspend fun count(): Long = withContext(dispatcher) {
        internalRepo.count()
    }

    suspend fun <S : T> count(example: Example<S>): Long = withContext(dispatcher) {
        internalRepo.count(example)
    }

    suspend fun deleteById(id: ID) = withContext(dispatcher) {
        internalRepo.deleteById(id)
    }

    suspend fun delete(entity: T) = withContext(dispatcher) {
        internalRepo.delete(entity)
    }

    suspend fun deleteAllById(ids: MutableIterable<ID>) = withContext(dispatcher) {
        internalRepo.deleteAllById(ids)
    }

    suspend fun deleteAll(entities: MutableIterable<T>) = withContext(dispatcher) {
        internalRepo.deleteAll(entities)
    }

    suspend fun deleteAll() = withContext(dispatcher) {
        internalRepo.deleteAll()
    }

    suspend fun <S : T> findOne(example: Example<S>): Optional<S> = withContext(dispatcher) {
        internalRepo.findOne(example)
    }

    suspend fun <S : T> exists(example: Example<S>): Boolean = withContext(dispatcher) {
        internalRepo.exists(example)
    }

    suspend fun <S : T, R : Any> findBy(
        example: Example<S>,
        queryFunction: Function<FluentQuery.FetchableFluentQuery<S>, R>
    ): R = withContext(dispatcher) {
        internalRepo.findBy(example, queryFunction)
    }

    suspend fun flush() = withContext(dispatcher) {
        internalRepo.flush()
    }

    suspend fun <S : T> saveAndFlush(entity: S): S = withContext(dispatcher) {
        internalRepo.saveAndFlush(entity)
    }

    suspend fun <S : T> saveAllAndFlush(entities: MutableIterable<S>): MutableList<S> = withContext(dispatcher) {
        internalRepo.saveAllAndFlush(entities)
    }

    suspend fun deleteAllInBatch(entities: MutableIterable<T>) = withContext(dispatcher) {
        internalRepo.deleteAllInBatch(entities)
    }

    suspend fun deleteAllInBatch() = withContext(dispatcher) {
        internalRepo.deleteAllInBatch()
    }

    suspend fun deleteAllByIdInBatch(ids: MutableIterable<ID>) = withContext(dispatcher) {
        internalRepo.deleteAllByIdInBatch(ids)
    }

    suspend fun getById(id: ID): T = withContext(dispatcher) {
        internalRepo.getById(id)
    }

}
