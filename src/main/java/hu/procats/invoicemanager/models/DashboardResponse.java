package hu.procats.invoicemanager.models;

import hu.procats.invoicemanager.jpamodels.Invoice;

import java.util.List;

public class DashboardResponse {
    private List<Invoice> invoices;
    private float allDebit;
    private float allReceivables;

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public float getAllDebit() {
        return allDebit;
    }

    public void setAllDebit(float allDebit) {
        this.allDebit = allDebit;
    }

    public float getAllReceivables() {
        return allReceivables;
    }

    public void setAllReceivables(float allReceivables) {
        this.allReceivables = allReceivables;
    }
}
