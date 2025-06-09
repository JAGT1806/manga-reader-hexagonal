package com.jagt.mangareader.role.application.usecases;

import com.jagt.mangareader.role.domain.exceptions.RoleNotFoundException;
import com.jagt.mangareader.role.domain.model.Role;
import com.jagt.mangareader.role.domain.ports.output.RolePersistencePort;
import com.jagt.mangareader.role.domain.service.RoleDomainService;
import com.jagt.mangareader.role.domain.usecases.UpdateRoleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UpdateRoleUseCaseImpl implements UpdateRoleUseCase {
    private final RolePersistencePort rolePersistencePort;
    private final RoleDomainService roleDomainService;

    @Override
    public Role execute(UpdateRoleCommand command) {
        if (command.roleId() == null) {
            throw new IllegalArgumentException("Role ID cannot be null");
        }

        Role existingRole = rolePersistencePort.findById(command.roleId())
                .orElseThrow(() -> new RoleNotFoundException("Role with id " + command.roleId() + " not found"));

        if (command.name() != null && !command.name().trim().isEmpty()) {
            String normalizedName = roleDomainService.normalizeRoleName(command.name());
            roleDomainService.validateRoleNameDuplicatedForUpdate(command.roleId(), normalizedName);
            existingRole.setName(normalizedName);
        }

        existingRole.setUpdatedAt(LocalDateTime.now());
        return rolePersistencePort.save(existingRole);
    }
}
