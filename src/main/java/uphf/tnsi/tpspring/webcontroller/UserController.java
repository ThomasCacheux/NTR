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

    @GetMapping(value = "/getSolde/{idCustomer}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getSolde(@PathVariable String idCustomer) {
        Long id = Long.valueOf(0);
        try{
            id = Long.parseLong(idCustomer);
        }catch (Exception e){

            Response message = new Response(null," id user invalid", false);
            return  new ResponseEntity<Response>(message, HttpStatus.NOT_FOUND);
        }

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

    @GetMapping(value = "/getOp/{idCustomer}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getOp(@PathVariable String idCustomer) {
        Long id = Long.valueOf(0);
        try{
            id = Long.parseLong(idCustomer);
        }catch (Exception e){

            Response message = new Response(null," id user invalid", false);
            return  new ResponseEntity<Response>(message, HttpStatus.NOT_FOUND);
        }

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
        Long id = Long.valueOf(0);
        double montant = 0.0;
        try{
            id = Long.parseLong(crediter.getIdCompte());
            montant = Double.parseDouble(crediter.getMontant());
        }catch (Exception e){

            Message message = new Message(" montant or id user invalid", false);
            return  new ResponseEntity<Message>(message, HttpStatus.NOT_FOUND);
        }

        Customer customer = customerRepository.findByIdCustomer(id);
        if (customer != null) {
            customer.setSolde(customer.getSolde() + montant);
            customerRepository.save(customer);
            Operation operation = new Operation(("+ " + montant), id);
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
        Long id = Long.valueOf(0);
        double montant = 0.0;
        try{
            id = Long.parseLong(debiter.getIdCompte());
            montant = Double.parseDouble(debiter.getMontant());
        }catch (Exception e){

            Message message = new Message(" montant or id user invalid", false);
            return  new ResponseEntity<Message>(message, HttpStatus.NOT_FOUND);
        }
        Customer customer = customerRepository.findByIdCustomer(id);
        if (customer != null) {
            customer.setSolde(customer.getSolde() - montant);
            customerRepository.save(customer);
            Operation operation = new Operation(("- " + montant), id);
            operationRepository.save(operation);
            Message message = new Message(("Debit effectué sur " + debiter.getIdCompte() + " Solde : " + customer.getSolde()), true);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            Message message = new Message(("Invalid customer"), false);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }



}
