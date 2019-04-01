package it.plague.jeedemo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedQuery(name = "Book.findAll", query = "select b from Book b")
public class Book implements Serializable {

  @Id
  @GeneratedValue
  private Long id;
  @NotNull
  @Column(nullable = false)
  private String title;
  private Float price;
  @Size(max = 2000)
  @Column(length = 2000)
  private String description;
  private String isbn;
  private Integer nbOfPage;
  private Boolean illustrations;
}
