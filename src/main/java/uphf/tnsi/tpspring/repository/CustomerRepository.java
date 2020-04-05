package uphf.tnsi.tpspring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uphf.tnsi.tpspring.entity.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {
    List<Customer> findAll();
    Customer findByIdCustomer(Long idCustomer);

}
