package com.jagt.mangareader.role.application.usecases;

import com.jagt.mangareader.role.domain.exceptions.RoleNotFoundException;
import com.jagt.mangareader.role.domain.model.Role;
import com.jagt.mangareader.role.domain.ports.output.RolePersistencePort;
import com.jagt.mangareader.role.domain.usecases.GetRoleByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetRoleByIdUseCaseImpl implements GetRoleByIdUseCase {
    private final RolePersistencePort rolePersistencePort;

    @Override
    public Role execute(Long roleId) {
        if (roleId == null) {
            throw new IllegalArgumentException("Role ID cannot be null");
        }

        return rolePersistencePort.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("Role with id " + roleId + " not found"));
    }
}
