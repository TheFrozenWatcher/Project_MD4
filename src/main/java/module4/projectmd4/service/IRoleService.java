package module4.projectmd4.service;

import module4.projectmd4.constant.RoleName;
import module4.projectmd4.model.entity.Role;

import java.util.List;

public interface IRoleService {
    Role findByRoleName(RoleName roleName);
List<Role> findAll();
}
