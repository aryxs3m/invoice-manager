package hu.procats.invoicemanager.jpamodels;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "Invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "Számla sorszám nem lehet üres")
    private String number;

    @NotEmpty(message = "Eladó neve nem lehet üres")
    private String sellerName;

    @NotEmpty(message = "Eladó adószáma nem lehet üres")
    private String sellerTaxNumber;

    @NotEmpty(message = "Vevő neve nem lehet üres")
    private String buyerName;

    @NotEmpty(message = "Vevő adószáma nem lehet üres")
    private String buyerTaxNumber;

    @NotNull(message = "Számla kelte nem lehet üres")
    private Date created_at;

    @NotNull(message = "Fizetési határidő nem lehet üres")
    private Date payment_due;

    @Column(nullable = true)
    private Date paid_at;

    @NotNull(message = "Bruttó végösszeg nem lehet üres")
    private float grossTotal;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerTaxNumber() {
        return sellerTaxNumber;
    }

    public void setSellerTaxNumber(String sellerTaxNumber) {
        this.sellerTaxNumber = sellerTaxNumber;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerTaxNumber() {
        return buyerTaxNumber;
    }

    public void setBuyerTaxNumber(String buyerTaxNumber) {
        this.buyerTaxNumber = buyerTaxNumber;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getPayment_due() {
        return payment_due;
    }

    public void setPayment_due(Date payment_due) {
        this.payment_due = payment_due;
    }

    public Date getPaid_at() {
        return paid_at;
    }

    public void setPaid_at(Date paid_at) {
        this.paid_at = paid_at;
    }

    public float getGrossTotal() {
        return grossTotal;
    }

    public void setGrossTotal(float grossTotal) {
        this.grossTotal = grossTotal;
    }
}
