package com.adel.ecommerce.model;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Product name can not be empty!")
    private String name;
    @Min(value = 1, message = "Price must be positive and not null!")
    private int price;
    @Min(value = 1, message = "Quantity must be more than 0!")
    private int quantity;
    @Column(columnDefinition = "text")
    @NotBlank(message = "Product must have description!")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "product")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Image> images = new ArrayList<>();

    private Long previewImageId;
    private LocalDateTime dateOfCreated;
    @PrePersist
    private void init(){
        dateOfCreated=LocalDateTime.now();
    }
    public void addImageToProduct(Image image){
        image.setProduct(this);
        images.add(image);
    }

}

