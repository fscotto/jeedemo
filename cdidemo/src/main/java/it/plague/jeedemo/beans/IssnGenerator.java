package it.plague.jeedemo.beans;

import it.plague.jeedemo.annotations.EightDigits;
import it.plague.jeedemo.annotations.Loggable;
import java.util.Random;
import java.util.logging.Logger;
import javax.inject.Inject;

@EightDigits
public class IssnGenerator implements NumberGenerator {

  @Inject
  private Logger logger;

  @Override
  @Loggable
  public String generateNumber() {
    String issn = " 8- " + Math.abs(new Random().nextInt());
    logger.info("Generated ISSN: " + issn);
    return issn;
  }
}
