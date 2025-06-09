package com.jagt.mangareader.role.infrastructure.input.rest.mapper;

import com.jagt.mangareader.role.domain.usecases.CreateRoleUseCase;
import com.jagt.mangareader.role.domain.usecases.UpdateRoleUseCase;
import com.jagt.mangareader.role.infrastructure.input.rest.request.RoleRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleRestMapper {
    CreateRoleUseCase.CreateRoleCommand toCreateRoleCommand(RoleRequest roleRequest);

    default UpdateRoleUseCase.UpdateRoleCommand toUpdateRoleCommand(Long id, RoleRequest request) {
        return new UpdateRoleUseCase.UpdateRoleCommand(id, request.name());
    }
}
