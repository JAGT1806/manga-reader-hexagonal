package com.jagt.mangareader.role.application.usecases;

import com.jagt.mangareader.role.domain.model.Role;
import com.jagt.mangareader.role.domain.ports.output.RolePersistencePort;
import com.jagt.mangareader.role.domain.service.RoleDomainService;
import com.jagt.mangareader.role.domain.usecases.CreateRoleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CreateRoleUseCaseImpl implements CreateRoleUseCase {
    private final RolePersistencePort rolePersistencePort;
    private final RoleDomainService roleDomainService;

    @Override
    public Role execute(CreateRoleCommand command) {
        if(command.name() == null || command.name().trim().isEmpty()) {
            throw new IllegalArgumentException("Role name cannot be null or empty");
        }
        String normalizedName = roleDomainService.normalizeRoleName(command.name());
        roleDomainService.validateRoleNameNotDuplicated(normalizedName);

        Role role = Role.builder()
                .name(normalizedName)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return rolePersistencePort.save(role);
    }
}
