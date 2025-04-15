package com.jagt.mangareader.role.infrastructure.output.persistence.adapter;

import com.jagt.mangareader.role.domain.model.Role;
import com.jagt.mangareader.role.domain.ports.output.RolePersistencePort;
import com.jagt.mangareader.role.infrastructure.output.persistence.mapper.RolePersistenceMapper;
import com.jagt.mangareader.role.infrastructure.output.persistence.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RolePersistenceAdapter implements RolePersistencePort {
    private final RoleRepository roleRepository;
    private final RolePersistenceMapper mapper;

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Role> findAll(Pageable pageable) {
        return mapper.toDomainList(roleRepository.findAll(pageable).getContent());
    }

    @Override
    public Role save(Role role) {
        return mapper.toDomain(roleRepository.save(mapper.toEntity(role)));
    }

    @Override
    public Optional<Role> findByName(String roleName) {
        return roleRepository.findByName(roleName)
                .map(mapper::toDomain);
    }

    @Override
    public List<Role> findByNameContaining(String roleName, Pageable pageable) {
        return mapper.toDomainList(roleRepository.findByNameContaining(roleName, pageable));
    }

    @Override
    public long countByNameContaining(String roleName) {
        return roleRepository.countByNameContaining(roleName);
    }

    @Override
    public long count() {
        return roleRepository.count();
    }
}
