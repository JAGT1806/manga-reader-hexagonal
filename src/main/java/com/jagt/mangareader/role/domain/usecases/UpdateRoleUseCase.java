package com.jagt.mangareader.role.domain.usecases;

import com.jagt.mangareader.role.domain.model.Role;

public interface UpdateRoleUseCase {
    Role execute(UpdateRoleCommand command);

    record UpdateRoleCommand(Long roleId, String name) {}
}
