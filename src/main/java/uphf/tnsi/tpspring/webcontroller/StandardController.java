package uphf.tnsi.tpspring.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uphf.tnsi.tpspring.entity.Customer;
import uphf.tnsi.tpspring.entity.Operation;
import uphf.tnsi.tpspring.repository.CustomerRepository;
import uphf.tnsi.tpspring.repository.OperationRepository;

import java.util.List;

@Controller
public class StandardController {
    @Autowired
    OperationRepository operationRepository;
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/login")
    public String login(Model model){
        return "auth";
    }

    @RequestMapping("/loginFailed")
    public String loginError(Model model){
        model.addAttribute("error", "true");
        return "auth";
    }

    @GetMapping("/compte/{id}")
    public String compte(Model model,  @PathVariable Long id){
        List<Operation> operations = operationRepository.findAllByIdCustomer(id);
        Customer customer = customerRepository.findByIdCustomer(id);
        model.addAttribute("operations", operations);
        model.addAttribute("customer",customer);
        return "compteinfos";
    }

    @GetMapping("/compte")
    public String compte(Model model){
        return "compteinfos";
    }
}
