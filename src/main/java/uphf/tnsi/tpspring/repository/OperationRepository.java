package uphf.tnsi.tpspring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uphf.tnsi.tpspring.entity.Operation;

import java.util.List;

@Repository
public interface OperationRepository extends CrudRepository<Operation,Long> {
    List<Operation>  findAll();
    Operation findByIdOperation(int idOperation);
}
