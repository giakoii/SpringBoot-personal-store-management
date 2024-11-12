package project.personal.personalstoremanagementproject.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @author giakhoi
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "email_template")
public class EmailTemplate extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Integer id;

    @Column(name = "title", nullable = false, columnDefinition = "NVARCHAR(MAX)")
    String mailTitle;

    @Column(name = "body", nullable = false, columnDefinition = "NVARCHAR(MAX)")
    String mailBody;

    @Column(name = "screen_name", nullable = false)
    String screenName;
}