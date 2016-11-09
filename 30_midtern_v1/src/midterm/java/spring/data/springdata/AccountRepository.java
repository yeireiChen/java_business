package midterm.java.spring.data.springdata;

import org.springframework.data.repository.CrudRepository;

import midterm.java.spring.data.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {

}
