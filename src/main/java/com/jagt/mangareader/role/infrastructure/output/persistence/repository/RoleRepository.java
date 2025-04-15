package com.jagt.mangareader.role.infrastructure.output.persistence.repository;

import com.jagt.mangareader.role.infrastructure.output.persistence.entity.RoleEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String name);

    List<RoleEntity> findByNameContaining(String name, Pageable pageable);

    long countByNameContaining(String name);
}
