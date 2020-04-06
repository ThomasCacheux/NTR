package uphf.tnsi.tpspring.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uphf.tnsi.tpspring.entity.Customer;
import uphf.tnsi.tpspring.entity.Information;
import uphf.tnsi.tpspring.entity.Message;
import uphf.tnsi.tpspring.entity.Operation;
import uphf.tnsi.tpspring.repository.CustomerRepository;
import uphf.tnsi.tpspring.repository.OperationRepository;

@RestController
public class UserController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OperationRepository operationRepository;

    @GetMapping(value = "/getSolde/{id}")
    public double getSolde(@PathVariable Long id) {
     return customerRepository.findByIdCustomer(id).getSolde();
    }

    @RequestMapping(value = "/crediter", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE}
            , produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Message> crediter(
            @RequestBody Information crediter) {
        Customer customer = customerRepository.findByIdCustomer(crediter.getIdCompte());
        customer.setSolde(customer.getSolde() + crediter.getMontant());
        customerRepository.save(customer);
        Operation operation = new Operation(("+ " + crediter.getMontant()),crediter.getIdCompte());
        operationRepository.save(operation);
        Message message = new Message(("Credit effectué sur " + crediter.getIdCompte() + " Solde : " + customer.getSolde()), true);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/debiter", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE}
            , produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Message> debiter(
            @RequestBody Information debiter) {
        Customer customer = customerRepository.findByIdCustomer(debiter.getIdCompte());
        customer.setSolde(customer.getSolde() - debiter.getMontant());
        customerRepository.save(customer);
        Operation operation = new Operation(("-" + debiter.getMontant()),debiter.getIdCompte());
        operationRepository.save(operation);
        Message message = new Message(("Debit effectué sur " + debiter.getIdCompte() + " Solde : " + customer.getSolde()), true);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    //Tests


    @RequestMapping(value = "/testp", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE}
            , produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Message> testPerson(
            @RequestBody Information crediter) {
        System.out.println(crediter.toString());
        Message test = new Message("test", true);
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

}
