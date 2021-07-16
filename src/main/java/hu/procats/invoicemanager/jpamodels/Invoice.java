package hu.procats.invoicemanager.jpamodels;

import com.sun.istack.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "Invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Számla sorszám nem lehet üres")
    private String number;

    @NotBlank(message = "Eladó neve nem lehet üres")
    private String sellerName;

    @NotBlank(message = "Eladó adószáma nem lehet üres")
    private String sellerTaxNumber;

    @NotBlank(message = "Vevő neve nem lehet üres")
    private String buyerName;

    @NotBlank(message = "Vevő adószáma nem lehet üres")
    private String buyerTaxNumber;

    @NotBlank(message = "Számla kelte nem lehet üres")
    private Date created_at;

    @NotBlank(message = "Fizetési határidő nem lehet üres")
    private Date payment_due;

    @Nullable
    private Date paid_at;

    @NotBlank(message = "Bruttó végösszeg nem lehet üres")
    private float grossTotal;
}
