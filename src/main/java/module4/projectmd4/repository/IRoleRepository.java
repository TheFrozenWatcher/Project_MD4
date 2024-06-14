package module4.projectmd4.repository;

import module4.projectmd4.constant.RoleName;
import module4.projectmd4.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleName(RoleName roleName);
}
