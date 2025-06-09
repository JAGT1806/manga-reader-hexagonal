package com.jagt.mangareader.role.domain.usecases;

import com.jagt.mangareader.role.domain.model.Role;

public interface CreateRoleUseCase {
    Role execute(CreateRoleCommand command);

    record CreateRoleCommand(String name) {}
}
