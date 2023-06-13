package com.adel.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories", uniqueConstraints = @UniqueConstraint(columnNames = "category_name"))
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @NotBlank(message = "Category must have a name!")
    @Column(name = "category_name")
    private String name;

//    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "category")
//    private List<Product> products = new ArrayList<>();

    @Override
    public String toString() {
        return name;
    }
}

