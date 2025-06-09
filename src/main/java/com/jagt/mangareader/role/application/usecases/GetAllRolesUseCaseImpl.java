package com.jagt.mangareader.role.application.usecases;

import com.jagt.mangareader.role.domain.model.Role;
import com.jagt.mangareader.role.domain.ports.output.RolePersistencePort;
import com.jagt.mangareader.role.domain.usecases.GetAllRolesUseCase;
import com.jagt.mangareader.shared.domain.PaginatedResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllRolesUseCaseImpl implements GetAllRolesUseCase {
    private final RolePersistencePort rolePersistencePort;

    @Override
    public PaginatedResult<Role> execute(String roleName, int offset, int limit) {
        List<Role> roles;
        Pageable pageable = PageRequest.of(offset, limit);
        long total;

        if(roleName != null && !roleName.trim().isEmpty()) {
            String normalizedName = roleName.trim().toLowerCase();
            roles = rolePersistencePort.findByNameContaining(normalizedName, pageable);
            total = rolePersistencePort.countByNameContaining(normalizedName);
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
}
