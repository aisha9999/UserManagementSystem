package az.project.usermanagementsystem.dao.repository;

import az.project.usermanagementsystem.dao.entity.ERole;
import az.project.usermanagementsystem.dao.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    Optional<RoleEntity> findByName(ERole name);
}
