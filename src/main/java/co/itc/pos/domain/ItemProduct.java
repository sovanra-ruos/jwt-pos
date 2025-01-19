package co.itc.pos.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sale_item_products")
@Getter
@Setter
@NoArgsConstructor
public class ItemProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sale_item_id")
    private SaleItem saleItem;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int qty;

}
