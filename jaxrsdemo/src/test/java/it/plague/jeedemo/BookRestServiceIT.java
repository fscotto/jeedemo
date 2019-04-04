package it.plague.jeedemo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.net.URI;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import org.junit.Test;

public class BookRestServiceIT {

  private static URI uri = UriBuilder.fromUri("http://localhost/jaxrsdemo-1.0-SNAPSHOT/rs/book")
      .port(8080).build();

  private static Client client = ClientBuilder.newClient();

  @Test
  public void shouldNotCreateANullBook() {
    // POSTs a null book
    Response response = client.target(uri)
        .request()
        .post(Entity.entity(null, MediaType.APPLICATION_XML));
    assertThat(response.getStatus(), is(Status.BAD_REQUEST.getStatusCode()));
  }

  @Test
  public void shouldNOtFindTheBookID() {
    // GETs a book with an unknown ID
    Response response = client.target(uri)
        .path("unknownID")
        .request()
        .get();
    assertThat(response.getStatus(), is(Status.NOT_FOUND.getStatusCode()));
  }

  @Test
  public void shouldCreateAndDeleteABook() {
    Book book = Book.builder()
        .title("H2G2")
        .price(12.5f)
        .description("Science book")
        .isbn("1-84023-742-2")
        .nbOfPage(354)
        .illustrations(false)
        .build();

    // POSTs a book
    Response response = client.target(uri)
        .request()
        .post(Entity.entity(book, MediaType.APPLICATION_XML));
    assertThat(response.getStatus(), is(Status.CREATED.getStatusCode()));
    URI bookUri = response.getLocation();

    // With the location, GETs the book
    response = client.target(bookUri)
        .request()
        .get();
    book = response.readEntity(Book.class);
    assertThat(response.getStatus(), is(Status.OK.getStatusCode()));
    assertEquals("H2G2", book.getTitle());

    // Gets the book id and DELETEs it
    String bookId = bookUri.toString().split("/")[6];
    response = client.target(uri)
        .path(bookId)
        .request()
        .delete();
    assertThat(response.getStatus(), is(Status.NO_CONTENT.getStatusCode()));

    // GETs the book and checks it has been deleted
    response = client.target(bookUri)
        .request()
        .get();
    assertThat(response.getStatus(), is(Status.NOT_FOUND.getStatusCode()));
  }

}
