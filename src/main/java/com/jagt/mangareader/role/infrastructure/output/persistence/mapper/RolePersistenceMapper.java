package com.jagt.mangareader.role.infrastructure.output.persistence.mapper;

import com.jagt.mangareader.role.domain.model.Role;
import com.jagt.mangareader.role.infrastructure.output.persistence.entity.RoleEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolePersistenceMapper {
    Role toDomain(RoleEntity entity);
    List<Role> toDomainList(List<RoleEntity> entities);
    RoleEntity toEntity(Role domain);
}
