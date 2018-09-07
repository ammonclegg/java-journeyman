package ammonclegg.java.apprentice.codesharing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author ammonclegg on 9/7/18.
 */
public class FileOpener {

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
