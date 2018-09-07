package ammonclegg.java.apprentice.codesharing;

import java.io.IOException;

/**
 * @author ammonclegg on 9/7/18.
 */
public class LineWriter extends FileOpener {

  private FileOpener fileOpener;
  private String filename;

  public LineWriter(String filename) {
    fileOpener = new FileOpener();
    if (filename.endsWith(".txt")) {
      this.filename = filename;
    }
    else {
      this.filename = filename + ".txt";
    }
  }

  private void writeLineComposition(String line) throws IOException {
    fileOpener.appendToFile(filename, line);
  }

  private void writeLineInheritance(String line) throws IOException {
    appendToFile(filename, line);
  }

  public void writeMessage(String message, String sender, String recipient) throws IOException {
    FileOpener.writeToFileStatic(filename, "To: " + recipient);
    writeLineComposition("From: " + sender);
    writeLineInheritance(message);
  }
}
