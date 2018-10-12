package ammonclegg.java.apprentice.badencapsulation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author ammonclegg on 10/12/18.
 */


/**
 * @author ammonclegg on 9/7/18.
 */
@SpringBootApplication
public class BadEncapsulationApplication implements CommandLineRunner {
  private static final Logger LOGGER = LoggerFactory.getLogger(BadEncapsulationApplication.class);

  private static final String FILENAME = "code-sharing-example";

  /**
   * Starts the project up.
   *
   * @param args a non-null String array of command line args.
   */
  public static void main(String[] args) {
    SpringApplication.run(BadEncapsulationApplication.class, args);
  }

  @Override
  @SuppressWarnings({"squid:S106", "squid:S1181"})
  public void run(String... args) {
    System.out.println("An example of bad encapsulation");

    System.out.println("Creating a ball");

    Ball ball = new Ball(1, 2, 3, 4);

    System.out.println("Ball: " + ball);

    try {
      System.out.println("Attempting to set the radius to -1 using the setter");
      ball.setRadius(-1);
      System.out.println("Ball: " + ball);
    }
    catch (RuntimeException e) {
      System.out.println(e);
      System.out.println("Ball: " + ball);
    }

    try {
      System.out.println("Attempting to set the radius to -1 without using the setter.");
      ball.radius = -1;
      System.out.println("Ball: " + ball);
    }
    catch (RuntimeException e) {
      System.out.println(e);
      System.out.println("Ball: " + ball);
    }
  }
}
