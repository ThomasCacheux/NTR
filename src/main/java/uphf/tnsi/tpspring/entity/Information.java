package uphf.tnsi.tpspring.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Information {
    private String idCompte;
    private String montant;

    public Information(String idCompte, String montant) {
        this.idCompte = idCompte;
        this.montant = montant;
    }
    public Information(){

    }

    public String getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(String idCompte) {
        this.idCompte = idCompte;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    @Override
    public String toString() {
        return "Information{" +
                "idCompte=" + idCompte +
                ", montant=" + montant +
                '}';
    }
}