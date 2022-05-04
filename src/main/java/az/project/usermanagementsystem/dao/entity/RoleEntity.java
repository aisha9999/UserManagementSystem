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
@ToString
@Entity
@Table(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role_name",nullable = false, unique = true)
    private ERole name;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamp default now()",nullable = false)
    private LocalDateTime createdAt= LocalDateTime.now();

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "timestamp default now()", nullable = false)
    private LocalDateTime updatedAt=LocalDateTime.now();

    public RoleEntity(Long id, ERole name) {
        this.id = id;
        this.name = name;
    }
}
