package ammonclegg.java.apprentice.codesharing;

import java.io.IOException;

/**
 * @author ammonclegg on 9/7/18.
 */
public class XMLWriter extends FileOpener {
  private FileOpener fileOpener;
  private String filename;

  public XMLWriter(String filename) {
    fileOpener = new FileOpener();
    if (filename.endsWith(".xml")) {
      this.filename = filename;
    }
    else {
      this.filename = filename + ".xml";
    }
  }

  private void writeLineComposition(String line) throws IOException {
    fileOpener.appendToFile(filename, line);
  }

  private void writeLineInheritance(String line) throws IOException {
    appendToFile(filename, line);
  }

  public void writeMessage(String message, String sender, String recipient) throws IOException {
    FileOpener.writeToFileStatic(filename, "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    writeLineInheritance("<message>");
    writeLineComposition("\t<to>" + recipient + "</to>");
    writeLineComposition("\t<from>" + sender + "</from>");
    writeLineInheritance("\t<body>" + message + "</body>");
    writeLineComposition("</message>");
  }
}
