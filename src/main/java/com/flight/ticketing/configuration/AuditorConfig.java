package com.flight.ticketing.configuration;

import com.flight.ticketing.auditor.DateAuditProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "dateAuditProvider")
public class AuditorConfig {

    @Bean
    public DateAuditProvider dateAuditProvider() {
        return new DateAuditProvider();
    }

}