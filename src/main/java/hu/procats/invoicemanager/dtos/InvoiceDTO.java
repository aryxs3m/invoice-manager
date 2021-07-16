package hu.procats.invoicemanager.dtos;

import java.util.Date;

public class InvoiceDTO {
    private String number;
    private String sellerName;
    private String sellerTaxNumber;
    private String buyerName;
    private String buyerTaxNumber;
    private Date created_at;
    private Date payment_due;
    private float grossTotal;

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

    public float getGrossTotal() {
        return grossTotal;
    }

    public void setGrossTotal(float grossTotal) {
        this.grossTotal = grossTotal;
    }
}
