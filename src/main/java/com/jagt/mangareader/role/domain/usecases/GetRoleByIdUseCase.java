package com.jagt.mangareader.role.domain.usecases;

import com.jagt.mangareader.role.domain.model.Role;

public interface GetRoleByIdUseCase {
    Role execute(Long roleId);
}
