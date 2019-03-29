package it.plague.jeedemo.beans;

import it.plague.jeedemo.annotations.Loggable;
import it.plague.jeedemo.annotations.ThirteenDigits;
import java.util.Random;
import java.util.logging.Logger;
import javax.inject.Inject;

@ThirteenDigits
public class IsbnGenerator implements NumberGenerator {

  @Inject
  private Logger logger;

  @Override
  @Loggable
  public String generateNumber() {
    String isbn = " 13-84356- " + Math.abs(new Random().nextInt());
    logger.info("Generated ISBN: " + isbn);
    return isbn;
  }
}
