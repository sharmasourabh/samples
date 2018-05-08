package srb.samples.shopping.checkout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CheckoutApplication {

  private static final Logger LOGGER = LoggerFactory.getLogger(CheckoutApplication.class);

  @Value("${spring.application.name}")
  private String appName;

  public CheckoutApplication(@Value("${spring.application.name}") final String applicationName) {
	    LOGGER.info("Starting up the {} application", applicationName);
	    this.appName = applicationName;	
  }

  public static void main(String[] args) {
    SpringApplication.run(CheckoutApplication.class, args);
  } 
}


