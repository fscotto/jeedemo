package it.plague.jeedemo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Startup
@Singleton
@DataSourceDefinition(
    className = "org.apache.derby.jdbc.EmbeddedDataSource",
    name = "java:global/jdbc/jaxrsoPU",
    user = "APP",
    password = "APP",
    databaseName = "testdb",
    properties = {"connectionAttributes=;create=true"})
public class DatabasePopulator {

  @PersistenceContext(name = "jaxrsPU")
  private EntityManager em;

  private Book h2g2;
  private Book lord;

  @PostConstruct
  private void populateDB() {
    h2g2 = Book.builder()
        .title("Beginning Java EE 7")
        .price(35f)
        .description("Great book")
        .isbn("1-8763-9125-7")
        .nbOfPage(605)
        .illustrations(true)
        .build();
    lord = Book.builder()
        .title("The Lord of the Rings")
        .price(50.4f)
        .description("SciFi")
        .isbn("1-84023-742-2")
        .nbOfPage(1216)
        .illustrations(true)
        .build();

    em.persist(h2g2);
    em.persist(lord);
  }

  @PreDestroy
  private void clearDB() {
    em.remove(h2g2);
    em.remove(lord);
  }

}
