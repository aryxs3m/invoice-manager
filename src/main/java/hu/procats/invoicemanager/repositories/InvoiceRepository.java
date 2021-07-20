package hu.procats.invoicemanager.repositories;

import hu.procats.invoicemanager.jpamodels.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    Optional<Invoice> findById(int id);

    List<Invoice> findAll();

    List<Invoice> findByPaidOrderByPaymentDueDesc(boolean paid);

    @Query(value = "SELECT SUM(i.gross_total) FROM invoice i WHERE i.invoices_type = '0' AND i.paid = 0", nativeQuery = true)
    Float allDebitSum();

    @Query(value = "SELECT SUM(i.gross_total) FROM invoice i WHERE i.invoices_type = '1' AND i.paid = 0", nativeQuery = true)
    Float allReceivablesSum();
}
