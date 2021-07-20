package hu.procats.invoicemanager;

import hu.procats.invoicemanager.dtos.*;
import hu.procats.invoicemanager.jpamodels.Invoice;
import hu.procats.invoicemanager.jpamodels.User;
import hu.procats.invoicemanager.models.*;
import hu.procats.invoicemanager.repositories.InvoiceRepository;
import hu.procats.invoicemanager.services.MyUserDetailsService;
import hu.procats.invoicemanager.util.FrontendException;
import hu.procats.invoicemanager.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import java.io.*;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SystemResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private Environment environment;

    @RequestMapping({ "/ping" })
    public String ping()
    {
        return "Ping.";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e)
        {
            throw new FrontendException("A felhasználónév vagy jelszó helytelen.");
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        Map<String, String> userMap = new HashMap<>();
        userMap.put("userName", userDetails.getUsername());

        return ResponseEntity.ok(new AuthenticationResponse(jwt, userMap));
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public User register(@RequestBody UserDTO userDTO) throws Exception {

        try {
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(userDTO.getUsername());

            throw new FrontendException("A felhasználónév foglalt.");
        } catch (UsernameNotFoundException e)
        {
            // Ha nincs ilyen username, akkor regelünk
            User user = new User();
            user.setUserName(userDTO.getUsername());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setActive(true);
            user.setRoles("ROLE_USER");
            userRepository.save(user);
            return user;
        }
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    public Invoice newInvoice(@RequestBody InvoiceDTO invoiceDTO) throws Exception {
        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber(invoiceDTO.getInvoiceNumber());
        invoice.setSellerName(invoiceDTO.getSellerName());
        invoice.setSellerTaxNumber(invoiceDTO.getSellerTaxNumber());
        invoice.setBuyerName(invoiceDTO.getBuyerName());
        invoice.setBuyerTaxNumber(invoiceDTO.getBuyerTaxNumber());
        invoice.setCreatedAt(invoiceDTO.getCreatedAt());
        invoice.setPaymentDue(invoiceDTO.getPaymentDue());
        invoice.setGrossTotal(invoiceDTO.getGrossTotal());

        invoiceRepository.save(invoice);
        return invoice;
    }

    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.PUT)
    public Invoice newInvoice(@PathVariable(value="id") int id, @RequestBody InvoiceDTO invoiceDTO) throws Exception {
        Invoice invoice = new Invoice();
        invoice.setId(id);
        invoice.setInvoiceNumber(invoiceDTO.getInvoiceNumber());
        invoice.setSellerName(invoiceDTO.getSellerName());
        invoice.setSellerTaxNumber(invoiceDTO.getSellerTaxNumber());
        invoice.setBuyerName(invoiceDTO.getBuyerName());
        invoice.setBuyerTaxNumber(invoiceDTO.getBuyerTaxNumber());
        invoice.setCreatedAt(invoiceDTO.getCreatedAt());
        invoice.setPaymentDue(invoiceDTO.getPaymentDue());
        invoice.setGrossTotal(invoiceDTO.getGrossTotal());

        invoiceRepository.save(invoice);
        return invoice;
    }

    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.GET)
    public @ResponseBody Invoice getInvoiceById(@PathVariable(value="id") int id) throws Exception
    {
        Optional<Invoice> invoice = invoiceRepository.findById(id);;
        if (invoice.isPresent())
        {
            return invoice.get();
        }
        else
        {
            throw new FrontendException("Ilyen azonosítóval nem található számla.");
        }
    }

    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.DELETE)
    public AckResponse deleteInvoiceById(@PathVariable(value="id") int id) throws Exception
    {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        if (invoice.isPresent())
        {
            invoiceRepository.delete(invoice.get());
            return new AckResponse("Számla törölve.");
        }
        else
        {
            throw new FrontendException("Ilyen azonosítóval nem található számla.");
        }
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.GET)
    public List<Invoice> getAllInvoices()
    {
        return invoiceRepository.findAll();
    }

    @RequestMapping(value = "/getdashboard", method = RequestMethod.POST)
    public DashboardResponse getDashboard(@RequestBody DashboardDTO dashboardDTO) throws Exception
    {
        DashboardResponse dashboardResponse = new DashboardResponse();

        List<Invoice> invoiceList = invoiceRepository.findByInvoicesTypeOrderByPaymentDueDesc(0);

        if (!dashboardDTO.getSearch().isEmpty())
        {
            switch (dashboardDTO.getColumn())
            {
                case "sellerName":
                    invoiceList = invoiceList
                            .stream().filter(invoice -> invoice.getSellerName().contains(dashboardDTO.getSearch()))
                            .collect(Collectors.toList());
                    break;

                case "sellerTaxNumber":
                    invoiceList = invoiceList
                            .stream().filter(invoice -> invoice.getSellerTaxNumber().contains(dashboardDTO.getSearch()))
                            .collect(Collectors.toList());
                    break;

                case "buyerName":
                    invoiceList = invoiceList
                            .stream().filter(invoice -> invoice.getBuyerName().contains(dashboardDTO.getSearch()))
                            .collect(Collectors.toList());
                    break;

                case "buyerTaxNumber":
                    invoiceList = invoiceList
                            .stream().filter(invoice -> invoice.getBuyerTaxNumber().contains(dashboardDTO.getSearch()))
                            .collect(Collectors.toList());
                    break;

                case "=grossTotal":
                    invoiceList = invoiceList
                            .stream().filter(invoice -> invoice.getGrossTotal() == Float.parseFloat(dashboardDTO.getSearch()))
                            .collect(Collectors.toList());
                    break;

                case "<grossTotal":
                    invoiceList = invoiceList
                            .stream().filter(invoice -> invoice.getGrossTotal() > Float.parseFloat(dashboardDTO.getSearch()))
                            .collect(Collectors.toList());
                    break;

                case ">grossTotal":
                    invoiceList = invoiceList
                            .stream().filter(invoice -> invoice.getGrossTotal() < Float.parseFloat(dashboardDTO.getSearch()))
                            .collect(Collectors.toList());
                    break;

                default:
                    throw new FrontendException("A megadott oszlopra nem lehet keresni.");
            }
        }

        dashboardResponse.setInvoices(invoiceList);

        try {
            dashboardResponse.setAllDebit(invoiceRepository.allDebitSum());
        } catch (Exception e) {
            dashboardResponse.setAllDebit(0);
        }

        try {
            dashboardResponse.setAllReceivables(invoiceRepository.allReceivablesSum());
        } catch (Exception e) {
            dashboardResponse.setAllReceivables(0);
        }

        return dashboardResponse;
    }

    @RequestMapping(value = "/postpaid", method = RequestMethod.POST)
    public Invoice postPaid(@RequestBody PostPaidDTO postPaidDTO) throws Exception
    {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(postPaidDTO.getId());
        if (optionalInvoice.isPresent())
        {
            Invoice invoice = optionalInvoice.get();
            invoice.setPaid(true);
            invoiceRepository.save(invoice);

            return invoice;
        }
        else
        {
            throw new FrontendException("Ilyen azonosítóval nem található számla.");
        }
    }

    @RequestMapping(value = "/postattachment", method = RequestMethod.POST)
    public AckResponse postAttachment(@RequestBody AttachmentDTO attachmentDTO) throws Exception
    {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(attachmentDTO.getId());

        if (optionalInvoice.isPresent())
        {
            String filePath = environment.getProperty("invoiceapp.attachments-folder") + UUID.randomUUID();
            File file = new File(filePath);
            try {
                OutputStream outputStream = new FileOutputStream(file);
                outputStream.write(Base64.getDecoder().decode(attachmentDTO.getFile()));
                outputStream.flush();
                outputStream.close();

                Invoice invoice = optionalInvoice.get();
                invoice.setAttachmentFile(filePath);
                invoiceRepository.save(invoice);

                return new AckResponse("Csatolmány rögzítve.");
            } catch (IOException e) {
                throw new FrontendException("Szerveroldali hiba miatt a csatolmány rögzítése sikertelen volt.");
            }
        }
        else
        {
            throw new FrontendException("Nincs számla a rendszerben a megadott azonosítóval.");
        }
    }

    @GetMapping(value = "/getattachment/{id}")
    public StreamingResponseBody getAttachment(@PathVariable int id) throws Exception {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);

        if (optionalInvoice.isPresent())
        {
            File f = new File(optionalInvoice.get().getAttachmentFile());
            InputStream in;
            if (f.exists()) {
                in = new BufferedInputStream(new FileInputStream(f));
            } else {
                throw new FrontendException("A számla csatolmánya nem található.");
            }
            return os -> FileCopyUtils.copy(in, os);
        }
        else
        {
            throw new FrontendException("Nincs számla a rendszerben a megadott azonosítóval.");
        }
    }


}
