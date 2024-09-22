package com.ecommerce.inventory.models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Builder
@Data
@Entity
@Table(name = "stock_quantity")
@NoArgsConstructor
@AllArgsConstructor
public class StockQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    private Product product;

    @Column(name = "stock_quantity", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer quantity;

}
