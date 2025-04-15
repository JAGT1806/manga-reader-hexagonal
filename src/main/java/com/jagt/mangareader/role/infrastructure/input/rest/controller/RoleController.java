package com.jagt.mangareader.role.infrastructure.input.rest.controller;

import com.jagt.mangareader.role.domain.model.Role;
import com.jagt.mangareader.role.domain.ports.input.RoleServicePort;
import com.jagt.mangareader.role.infrastructure.input.rest.mapper.RoleRestMapper;
import com.jagt.mangareader.role.infrastructure.input.rest.request.RoleRequest;
import com.jagt.mangareader.shared.domain.PaginatedResult;
import com.jagt.mangareader.shared.infrastructure.web.response.OkResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleServicePort servicePort;
    private final RoleRestMapper mapper;

    @GetMapping
    public ResponseEntity<PaginatedResult<Role>> getAllRoles(
            @RequestParam(required = false) String role,
            @RequestParam(required = false, defaultValue = "0") int offset,
            @RequestParam(required = false, defaultValue = "12") int limit
    ) {
        return ResponseEntity.ok(servicePort.getAllRoles(role, offset, limit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        return ResponseEntity.ok(servicePort.getRoleById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<OkResponse> createRole(@Valid @RequestBody RoleRequest request) {
        servicePort.createRole(mapper.toRole(request));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(OkResponse.of(String.valueOf(HttpStatus.CREATED.value())));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<OkResponse> updateRole(@PathVariable Long id, @Valid @RequestBody RoleRequest request) {
        servicePort.updateRole(id, mapper.toRole(request));
        return ResponseEntity.ok(OkResponse.of(String.valueOf(HttpStatus.OK.value())));
    }

}
