package ammonclegg.java.apprentice.errorhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/**
 * @author ammonclegg on 8/3/18.
 */
@SpringBootApplication
public class ErrorApplication implements CommandLineRunner {
  private static final Logger LOGGER = LoggerFactory.getLogger(ErrorApplication.class);

  /**
   * Starts the project up.
   *
   * @param args a non-null String array of command line args.
   */
  public static void main(String[] args)  {
    SpringApplication.run(ErrorApplication.class, args);
  }

  @Override
  @SuppressWarnings({"squid:S106", "squid:S1181"})
  public void run(String... args) {

    System.out.println("Demonstrating error handling");

    try {
      testChecked();
    }
    catch (IOException e) {
      LOGGER.info("Caught IOException.", e);
    }

    try {
      testUnchecked();
    }
    catch (RuntimeException e) {
      LOGGER.info("Caught RuntimeException", e);
    }

    try {
      testError();
    }
    catch (Error e) {
      LOGGER.info("Caught Error", e);
    }

  }

  @SuppressWarnings("squid:S106")
  private void testChecked() throws IOException {
    System.out.println("Throwing checked IOException");
    throw new IOException("Test");
  }

  @SuppressWarnings({"squid:S3518","squid:S106"})
  private void testUnchecked() {
    System.out.println("Throwing unchecked RuntimeException");
    int item = 20/0;
    LOGGER.info("item={}", item); // Never should reach here, should throw exception.
  }

  @SuppressWarnings("squid:S106")
  private void testError() {
    System.out.println("Throwing Error UnknownError");
    throw new UnknownError("Test");
  }
}
