package com.jagt.mangareader.role.domain.ports.input;

import com.jagt.mangareader.role.domain.model.Role;
import com.jagt.mangareader.shared.domain.PaginatedResult;

public interface RoleServicePort {
    PaginatedResult<Role> getAllRoles(String roleName, int offset, int limit);
    Role getRoleById(Long roleId);
    void createRole(Role role);
    void updateRole(Long id, Role role);
}
