package com.deloitte.mule.tranning.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class AuditingConfig {

    @Autowired
    private HttpServletRequest request;

    @Bean
    AuditorAware<String> auditorProvider() {
        return () -> Optional.of(request.getHeader("user"));
    }

}
