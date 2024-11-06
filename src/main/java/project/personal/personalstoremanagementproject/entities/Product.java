package project.personal.personalstoremanagementproject.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "Products")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product extends BaseEntity{

    @Id
    @Column(name = "PRODUCT_ID")
    String productId;

    @Column(name = "PRODUCT_NAME", nullable = false, length = 255)
    String productName;

    @Column(name = "CATEGORY_ID")
    String categoryId;

    @Column(name = "PRICE", nullable = false, precision = 10)
    double price;

    @Column(name = "STOCK_QUANTITY", nullable = false)
    Integer stockQuantity;

    @Column(name = "DESCRIPTION", columnDefinition = "NVARCHAR(MAX)")
    String description;
}