package org.example.personalstoremanagementproject.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tbl_Order")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    @Id
    @Column(name = "order_id", nullable = false, length = 50)
    String orderId;

    @Column(name = "order_date", nullable = false, length = 50)
    LocalDateTime orderDate;

    @Column(name = "total_price", nullable = false, length = 50)
    Double totalPrice;

    @Column(name = "item_quantity", nullable = false, length = 50)
    Integer itemQuantity;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<Items> items;

}
