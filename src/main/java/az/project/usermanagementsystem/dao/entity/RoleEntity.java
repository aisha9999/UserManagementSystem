package az.project.usermanagementsystem.dao.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role_table")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role_name",nullable = false, unique = true)
    private ERole name;

    @CreationTimestamp
    @Column(name = "r_created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "r_updated_at")
    private LocalDateTime updatedAt;
}
