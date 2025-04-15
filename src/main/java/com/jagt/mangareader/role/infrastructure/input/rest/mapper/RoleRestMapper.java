package com.jagt.mangareader.role.infrastructure.input.rest.mapper;

import com.jagt.mangareader.role.domain.model.Role;
import com.jagt.mangareader.role.infrastructure.input.rest.request.RoleRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleRestMapper {
    Role toRole(RoleRequest roleRequest);
}
