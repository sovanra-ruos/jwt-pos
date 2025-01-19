package co.itc.pos.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sales")
@Getter
@Setter
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private double total;
    private double paid;
    private double change;
    private boolean active;
    private float discount;
    private String paymentMethod;

    @ManyToOne
    private SaleItem saleItem;

}
