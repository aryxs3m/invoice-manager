package hu.procats.invoicemanager.repositories;

import hu.procats.invoicemanager.jpamodels.Invoice;
import hu.procats.invoicemanager.jpamodels.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    Optional<Invoice> findById(int id);
    Optional<Invoice> findByNumber(String number);
    List<Invoice> findAll();
}
