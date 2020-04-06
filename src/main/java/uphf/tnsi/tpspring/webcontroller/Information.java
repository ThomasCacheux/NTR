package uphf.tnsi.tpspring.webcontroller;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
class Information {
    private Long idCompte;
    private double montant;

    public Information(Long idCompte, double montant) {
        this.idCompte = idCompte;
        this.montant = montant;
    }

    public Information() {
    }

    public Long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Long idCompte) {
        this.idCompte = idCompte;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
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