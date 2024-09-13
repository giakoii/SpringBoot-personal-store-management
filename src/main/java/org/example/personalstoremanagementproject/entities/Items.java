package org.example.personalstoremanagementproject.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tbl_Items")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Items {

    @Id
    @Column(name = "item_id", nullable = false, length = 50)
    String itemId;

    @Column(name = "item_name", nullable = false, length = 50)
    String itemName;

    @Column(name = "item_price", nullable = false, length = 50)
    Double itemPrice;

    @Column(name = "item_quantity", nullable = false, length = 50)
    Integer itemQuantity;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false)
    Order order;
}
