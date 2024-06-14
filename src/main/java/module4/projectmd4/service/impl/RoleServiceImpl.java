package module4.projectmd4.service.impl;

import lombok.RequiredArgsConstructor;
import module4.projectmd4.constant.RoleName;
import module4.projectmd4.model.entity.Role;
import module4.projectmd4.repository.IRoleRepository;
import module4.projectmd4.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {
    private final IRoleRepository roleRepository;

    @Override
    public Role findByRoleName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName).orElseThrow(() -> new RuntimeException("Role not found"));
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}