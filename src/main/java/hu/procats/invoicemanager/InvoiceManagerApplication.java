package hu.procats.invoicemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class InvoiceManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceManagerApplication.class, args);
	}

}
