package ammonclegg.java.apprentice.codesharing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


/**
 * @author ammonclegg on 9/7/18.
 */
@SpringBootApplication
public class CodeSharingApplication implements CommandLineRunner {
  private static final Logger LOGGER = LoggerFactory.getLogger(CodeSharingApplication.class);

  private static final String FILENAME = "code-sharing-example";

  /**
   * Starts the project up.
   *
   * @param args a non-null String array of command line args.
   */
  public static void main(String[] args) {
    SpringApplication.run(CodeSharingApplication.class, args);
  }

  @Override
  @SuppressWarnings({"squid:S106", "squid:S1181"})
  public void run(String... args) {
    System.out.println("Writing to files via different mechanisms");

    LineWriter lineWriter = new LineWriter(FILENAME);
    XMLWriter xmlWriter = new XMLWriter(FILENAME);

    try {
      lineWriter.writeMessage("Hello world", "Ammon", "Joe");
    }
    catch (IOException e) {
      LOGGER.error("Encountered error while writing to file", e);
    }

    try {
      xmlWriter.writeMessage("Hello world too", "Ammon", "Joe");
    }
    catch (IOException e) {
      LOGGER.error("Encountered error while writing to file", e);
    }

    System.out.println(String.format("Wrote out to files %s.txt and %s.xml", FILENAME, FILENAME));

  }
}
