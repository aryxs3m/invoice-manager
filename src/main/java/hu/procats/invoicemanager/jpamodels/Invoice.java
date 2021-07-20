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
    private String invoiceNumber;

    @NotEmpty(message = "Eladó neve nem lehet üres")
    private String sellerName;

    @NotEmpty(message = "Eladó adószáma nem lehet üres")
    private String sellerTaxNumber;

    @NotEmpty(message = "Vevő neve nem lehet üres")
    private String buyerName;

    @NotEmpty(message = "Vevő adószáma nem lehet üres")
    private String buyerTaxNumber;

    @NotNull(message = "Számla kelte nem lehet üres")
    private Date createdAt;

    @NotNull(message = "Fizetési határidő nem lehet üres")
    private Date paymentDue;

    @NotNull
    private Boolean paid = false;

    @NotNull
    private int invoicesType = 0;

    @Column(nullable = true)
    private Date paidDate;

    @NotNull(message = "Bruttó végösszeg nem lehet üres")
    private float grossTotal;

    @Column(nullable = true)
    private String attachmentFile;

    public String getAttachmentFile() {
        return attachmentFile;
    }

    public void setAttachmentFile(String attachmentFile) {
        this.attachmentFile = attachmentFile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getPaymentDue() {
        return paymentDue;
    }

    public void setPaymentDue(Date paymentDue) {
        this.paymentDue = paymentDue;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public int getInvoicesType() {
        return invoicesType;
    }

    public void setInvoicesType(int invoicesType) {
        this.invoicesType = invoicesType;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public float getGrossTotal() {
        return grossTotal;
    }

    public void setGrossTotal(float grossTotal) {
        this.grossTotal = grossTotal;
    }
}
