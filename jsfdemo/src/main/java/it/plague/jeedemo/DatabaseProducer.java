package it.plague.jeedemo;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DatabaseProducer {

  @Produces
  @PersistenceContext(unitName = "jsfdemoPU")
  private EntityManager em;
}
