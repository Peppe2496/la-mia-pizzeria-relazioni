package org.learning.springlamiapizzeriacrud.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.net.URL;

public class pizza {

   @Id
   private Integer id;

   @Column(nullable = false)
   private String name;

    @Column
    private String description;

    private BigDecimal price;

    private URL imagine;

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

    public URL getImagine() {
        return imagine;
    }

    public void setImagine(URL imagine) {
        this.imagine = imagine;
    }
}
