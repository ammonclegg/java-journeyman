package ammonclegg.java.apprentice.codesharing;

import java.io.IOException;

/**
 * @author ammonclegg on 9/21/18.
 */
public interface Writer {

  /**
   * Writes a message to a file
   * @param message The message to write
   * @param sender The sender of the message
   * @param recipient The recipient of the message
   * @throws IOException
   */
  void writeMessage(String message, String sender, String recipient) throws IOException;
}
