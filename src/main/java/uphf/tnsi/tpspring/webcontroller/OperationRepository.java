package uphf.tnsi.tpspring.webcontroller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends CrudRepository<Operation,Long> {
    List<Operation>  findAll();
    Operation findByIdOperation(int idOperation);
}
