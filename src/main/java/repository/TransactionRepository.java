package repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import model.Transaction;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository<Transaction, Integer> {
}
