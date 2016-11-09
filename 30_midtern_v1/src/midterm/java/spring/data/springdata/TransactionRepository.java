package midterm.java.spring.data.springdata;

import org.springframework.data.repository.CrudRepository;

import midterm.java.spring.data.entity.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

}