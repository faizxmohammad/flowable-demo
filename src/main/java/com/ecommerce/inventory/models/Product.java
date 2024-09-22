package com.ecommerce.inventory.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;


@Builder
@Data
@Entity
@Table(name = "Product")
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "barcode", nullable = false)
    private String barcode;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "product", cascade = CascadeType.MERGE)
    @ToString.Exclude
    private StockQuantity stockQuantity;

}
