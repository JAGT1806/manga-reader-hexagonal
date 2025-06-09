package com.jagt.mangareader.role.infrastructure.config;

import com.jagt.mangareader.role.domain.ports.output.RolePersistencePort;
import com.jagt.mangareader.role.domain.service.RoleDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleConfig {

    @Bean
    public RoleDomainService roleDomainService(RolePersistencePort rolePersistencePort) {
        return new RoleDomainService(rolePersistencePort);
    }
}
