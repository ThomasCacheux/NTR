package uphf.tnsi.tpspring.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uphf.tnsi.tpspring.entity.*;
import uphf.tnsi.tpspring.repository.CustomerRepository;
import uphf.tnsi.tpspring.repository.OperationRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OperationRepository operationRepository;

    @GetMapping(value = "/getSolde/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getSolde(@PathVariable Long id) {
        Customer customer = customerRepository.findByIdCustomer(id);
        if(customer != null){
            ArrayList list = new ArrayList();
            list.add(customer.getSolde());
            Response message = new Response(list,"ok", true);
            return  new ResponseEntity<Response>(message, HttpStatus.OK);

        }else{
            Response message = new Response(null,id.toString()+" user invalid", false);
            return  new ResponseEntity<Response>(message, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/getOp/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getOp(@PathVariable Long id) {
        Customer customer = customerRepository.findByIdCustomer(id);
        if(customer != null){
            List<Operation> operationArrayList = operationRepository.findAllByIdCustomer(id);
            Response message = new Response(operationArrayList,"ok", true);
            return  new ResponseEntity<Response>(message, HttpStatus.OK);

        }else{
            Response message = new Response(null,id.toString()+" user invalid", false);
            return  new ResponseEntity<Response>(message, HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/crediter", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE}
            , produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Message> crediter(
            @RequestBody Information crediter) {
        Customer customer = customerRepository.findByIdCustomer(crediter.getIdCompte());
        if (customer != null) {
            customer.setSolde(customer.getSolde() + crediter.getMontant());
            customerRepository.save(customer);
            Operation operation = new Operation(("+ " + crediter.getMontant()), crediter.getIdCompte());
            operationRepository.save(operation);
            Message message = new Message(("Credit effectué sur " + crediter.getIdCompte() + " Solde : " + customer.getSolde()), true);
            return new ResponseEntity<>(message, HttpStatus.OK);

        } else {
            Message message = new Message(("Invalid customer"), false);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/debiter", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE}
            , produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Message> debiter(
            @RequestBody Information debiter) {
        Customer customer = customerRepository.findByIdCustomer(debiter.getIdCompte());
        if (customer != null) {
            customer.setSolde(customer.getSolde() - debiter.getMontant());
            customerRepository.save(customer);
            Operation operation = new Operation(("- " + debiter.getMontant()), debiter.getIdCompte());
            operationRepository.save(operation);
            Message message = new Message(("Debit effectué sur " + debiter.getIdCompte() + " Solde : " + customer.getSolde()), true);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            Message message = new Message(("Invalid customer"), false);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }



}
