package uphf.tnsi.tpspring.webcontroller;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer  {
    @Id
    private Long idCustomer;
    private String name;
    private String password;
    private Double Solde;

    public  Customer(){

    }

    public Customer(Long idCustomer, String name, String password, Double solde) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.password = password;
        Solde = solde;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getSolde() {
        return Solde;
    }

    public void setSolde(Double solde) {
        Solde = solde;
    }
}
