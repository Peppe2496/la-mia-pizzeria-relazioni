package org.learning.springlamiapizzeriacrud.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "ingredients")
public class Ingredient {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    private String name ;
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
}
