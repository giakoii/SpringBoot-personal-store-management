package project.personal.personalstoremanagementproject.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "Categories")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category extends BaseEntity{

    @Id
    @Column(name = "CATEGORY_ID")
    Integer categoryId;

    @Column(name = "CATEGORY_NAME", nullable = false, length = 255)
    String categoryName;

    @Column(name = "DESCRIPTION", columnDefinition = "NVARCHAR(MAX)")
    String description;

}