package ammonclegg.campaign.tracker.exceptions;

/**
 * @author ammonclegg on 8/23/19.
 */
public class BadTypeException extends Exception {

  // This is an exception meant to represent invalid types in the processing of game objects

  public BadTypeException(String message) {
    super(message);
  }

  public BadTypeException(String message, Throwable cause)
  {
    super(message, cause);
  }
}
