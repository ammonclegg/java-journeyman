package ammonclegg.java.apprentice.filesorter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

/**
 * @author ammonclegg on 8/3/18.
 */
@SpringBootApplication
public class FileSorterApplication implements CommandLineRunner {
  private static final Logger LOGGER = LoggerFactory.getLogger(FileSorterApplication.class);

  @Autowired
  private FileSorterService fileSorterService;

  /**
   * Starts the project up.
   *
   * @param args a non-null String array of command line args.
   */
  public static void main(String[] args)  {
    SpringApplication.run(FileSorterApplication.class, args);
  }

  @SuppressWarnings("squid:S106")
  @Override
  public void run(String... args) throws Exception {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter the input file: ");

    String inputFile = scanner.nextLine();

    System.out.println("Enter the output file name: ");

    String outputFile = scanner.nextLine();

    System.out.println("(true/false) Sort in reverse order? ");

    boolean reverse = scanner.nextBoolean();

    LOGGER.info("Sorting lines. inputFile={}, outputFile={}, reverse={}", inputFile, outputFile, reverse);
    fileSorterService.sortFile(inputFile, outputFile, reverse);
  }
}
