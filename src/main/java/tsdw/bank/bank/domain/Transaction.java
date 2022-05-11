package tsdw.bank.bank.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "transaction", uniqueConstraints = { @UniqueConstraint(columnNames = {"payment_id"})})
public
class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @NotNull
    private Payment payment;

    @NotNull
    private Boolean accepted;

    private String description;

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + id + "'" +
            ", payment='" + payment + "'" +
            ", accepted='" + accepted + "'" +
            ", description='" + description + "'" +
            "}";
    }

    public Payment getPayment() {
        return this.payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Boolean isAccepted() {
        return this.accepted;
    }

    public Boolean getAccepted() {
        return this.accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Transaction(Payment payment, Boolean accepted, String description) {
        this.payment = payment;
        this.accepted = accepted;
        this.description = description;
    }

}