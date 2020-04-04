package uphf.tnsi.tpspring.webcontroller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {
    List<Customer> findAll();
    Customer findByIdCustomer(Long idCustomer);

}