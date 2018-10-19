package ammonclegg.java.apprentice.codesharing;

import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author ammonclegg on 9/7/18.
 */
public class XMLWriter extends FileOpener implements Writer {
  private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(XMLWriter.class);
  private FileOpener fileOpener;
  private String filename;

  public XMLWriter(String filename) {
    fileOpener = new FileOpener();
    if (filename.endsWith(".xml")) {
      this.filename = filename;
    }
    else {
      LOGGER.warn("File did not end in '.xml'. Appending '.xml'.");
      this.filename = filename + ".xml";
    }
  }

  private void writeLineComposition(String line) throws IOException {
    LOGGER.debug("Writing line using composition. Line: {}", line);
    fileOpener.appendToFile(filename, line);
  }

  private void writeLineInheritance(String line) throws IOException {
    LOGGER.debug("Writing line using inheritance. Line: {}", line);
    appendToFile(filename, line);
  }

  /**
   * Writes a message to a file
   * @param message The message to write
   * @param sender The sender of the message
   * @param recipient The recipient of the message
   * @throws IOException
   */
  public void writeMessage(String message, String sender, String recipient) throws IOException {
    LOGGER.info("Writing file in xml format.");
    FileOpener.writeToFileStatic(filename, "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    writeLineInheritance("<message>");
    writeLineComposition("\t<to>" + recipient + "</to>");
    writeLineComposition("\t<from>" + sender + "</from>");
    writeLineInheritance("\t<body>" + message + "</body>");
    writeLineComposition("</message>");
  }
}
