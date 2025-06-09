package com.jagt.mangareader.role.domain.usecases;

import com.jagt.mangareader.role.domain.model.Role;
import com.jagt.mangareader.shared.domain.PaginatedResult;

public interface GetAllRolesUseCase {
    PaginatedResult<Role> execute(String roleName, int offset, int limit);
}
