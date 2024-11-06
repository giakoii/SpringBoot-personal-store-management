package project.personal.personalstoremanagementproject.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import project.personal.personalstoremanagementproject.utils.constants.ConstantEnum;

import java.time.LocalDateTime;

@Entity
@Table(name = "Orders")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order extends BaseEntity{

    @Id
    @Column(name = "ORDER_ID")
    String orderId;

    @Column(name = "USER_ID")
    String userId;

    @Column(name = "ORDER_DATE", columnDefinition = "DATETIME DEFAULT GETDATE()")
    LocalDateTime orderDate;

    @Column(name = "TOTAL_AMOUNT", nullable = false, precision = 10)
    double totalAmount;

    @Column(name = "STATUS", length = 50)
    ConstantEnum.Status status;

}