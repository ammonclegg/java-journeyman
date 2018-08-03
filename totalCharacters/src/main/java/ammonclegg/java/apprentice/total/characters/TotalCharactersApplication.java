package ammonclegg.java.apprentice.total.characters;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.exit;

/**
 * @author ammonclegg on 8/3/18.
 */
@SpringBootApplication
public class TotalCharactersApplication implements CommandLineRunner {

  @Autowired
  private TotalCharactersService totalCharactersService;

  /**
   * Starts the project up.
   *
   * @param args a non-null String array of command line args.
   */
  public static void main(String[] args)  {
    SpringApplication.run(TotalCharactersApplication.class, args);
  }

  @Override
  public void run(String... args) {
    try {
      totalCharactersService.run();
    }
    catch (OutOfMemoryError e) {
      System.out.println("Ran out of memory!");
    }
  }
}
