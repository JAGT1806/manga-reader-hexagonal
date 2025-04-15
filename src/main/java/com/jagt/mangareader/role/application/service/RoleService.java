package com.jagt.mangareader.role.application.service;

import com.jagt.mangareader.role.domain.model.Role;
import com.jagt.mangareader.role.domain.ports.input.RoleServicePort;
import com.jagt.mangareader.role.domain.ports.output.RolePersistencePort;
import com.jagt.mangareader.shared.domain.PaginatedResult;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService implements RoleServicePort {
    private final RolePersistencePort rolePersistencePort;

    @Override
    public PaginatedResult<Role> getAllRoles(String roleName, int offset, int limit) {
        List<Role> roles;
        Pageable pageable = PageRequest.of(offset, limit);
        long total;

        if(roleName != null) {
            roles = rolePersistencePort.findByNameContaining(roleName.toUpperCase(), pageable);
            total = rolePersistencePort.countByNameContaining(roleName.toUpperCase());
        } else {
            roles = rolePersistencePort.findAll(pageable);
            total = rolePersistencePort.count();
        }

        return PaginatedResult.<Role>builder()
                .data(roles)
                .offset(offset)
                .limit(limit)
                .total(total)
                .build();
    }

    @Override
    public Role getRoleById(Long roleId) {
        return rolePersistencePort.findById(roleId)
                .orElseThrow();
    }

    @Override
    public void createRole(Role role) {
        role.setName(role.getName().toUpperCase());
        role.setCreatedAt(LocalDateTime.now());
        role.setUpdatedAt(LocalDateTime.now());
        rolePersistencePort.save(role);
    }

    @Override
    public void updateRole(Long id, Role role) {
        Role roleDB = getRoleById(id);
        if(StringUtils.isNotBlank(role.getName())) {
            roleDB.setName(role.getName().toUpperCase());
        }
        roleDB.setUpdatedAt(LocalDateTime.now());
        rolePersistencePort.save(roleDB);
    }
}
