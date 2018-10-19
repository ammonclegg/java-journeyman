package ammonclegg.java.apprentice.codesharing;

import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author ammonclegg on 9/7/18.
 */
public class FileOpener {
  private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(FileOpener.class);

  protected void writeToFile(String filename, String data) throws IOException {
    try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {
      writer.write(data);
      writer.newLine();
    }
  }

  protected void appendToFile(String filename, String data) throws IOException {
    try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
      writer.append(data);
      writer.newLine();
    }
  }


  public static void writeToFileStatic(String filename, String data) throws IOException {
    LOGGER.debug("Writing file using static method.");
    try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {
      writer.write(data);
      writer.newLine();
    }
  }

  public static void appendToFileStatic(String filename, String data) throws IOException {
    try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
      writer.append(data);
      writer.newLine();
    }
  }
}
