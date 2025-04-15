package com.jagt.mangareader.role.domain.ports.output;

import com.jagt.mangareader.role.domain.model.Role;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RolePersistencePort {
    Optional<Role> findById(Long id);

    List<Role> findAll(Pageable pageable);

    Role save(Role role);

    Optional<Role> findByName(String roleName);

    List<Role> findByNameContaining(String roleName, Pageable pageable);

    long countByNameContaining(String roleName);

    long count();
}
