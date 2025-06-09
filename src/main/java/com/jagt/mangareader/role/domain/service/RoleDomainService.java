package com.jagt.mangareader.role.domain.service;

import com.jagt.mangareader.role.domain.exceptions.RoleNotFoundException;
import com.jagt.mangareader.role.domain.ports.output.RolePersistencePort;
import com.jagt.mangareader.shared.domain.exceptions.DuplicateDataException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleDomainService {
    private final RolePersistencePort rolePersistencePort;

    public void validateRoleExists(Long roleId) {
        if(rolePersistencePort.findById(roleId).isPresent()) {
            throw new RoleNotFoundException("Role with id " + roleId + " not found");
        }
    }

    public void validateRoleNameNotDuplicated(String roleName) {
        if (rolePersistencePort.findByName(roleName.toUpperCase()).isPresent()) {
            throw new DuplicateDataException("Role with name " + roleName + " already exists");
        }
    }

    public void validateRoleNameDuplicatedForUpdate(Long roleId, String roleName) {
        rolePersistencePort.findByName(roleName.toUpperCase())
                .ifPresent(existingRole -> {
                    if(!existingRole.getId().equals(roleId)) {
                        throw new DuplicateDataException("Role  with name " + roleName + " already exists");
                    }
                });
    }

    public String normalizeRoleName(String roleName) {
        return roleName != null ? roleName.trim().toUpperCase() : null;
    }
}
