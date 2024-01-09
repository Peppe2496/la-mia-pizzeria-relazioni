package org.learning.springlamiapizzeriacrud.model;

import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(name = "pizza")
public class Pizza {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @Column(nullable = false)
   private String name;

    @Column(nullable = false)
    private String description;

  @Column(nullable = false)
  private BigDecimal price;

    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setImagine(String url) {
        this.url = url;
    }
}
