package com.fiap.logistica_entrega.application.config;

import com.fiap.logistica_entrega.domain.ports.outbound.EntregaRepositoryPort;
import com.fiap.logistica_entrega.domain.usecase.CreateEntrega;
import com.fiap.logistica_entrega.domain.usecase.DeleteEntrega;
import com.fiap.logistica_entrega.domain.usecase.FindEntrega;
import com.fiap.logistica_entrega.domain.usecase.UpdateEntrega;
import com.fiap.logistica_entrega.infrastructure.EntregaRepositoryPortImpl;
import com.fiap.logistica_entrega.infrastructure.jpa.EntregaRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.fiap.logistica_entrega.infrastructure.jpa")
@EntityScan(basePackages = "com.fiap.logistica_entrega.domain")
@EnableTransactionManagement
public class SpringConfig implements WebMvcConfigurer {

    @Bean
    public CreateEntrega createEntrega(EntregaRepositoryPort repository) {
        return new CreateEntrega(repository);
    }

    @Bean
    public FindEntrega findEntrega(EntregaRepositoryPort repository) {
        return new FindEntrega(repository);
    }

    @Bean
    public UpdateEntrega updateEntrega(EntregaRepositoryPort repository) {
        return new UpdateEntrega(repository);
    }

    @Bean
    public DeleteEntrega deleteEntrega(EntregaRepositoryPort repository) {
        return new DeleteEntrega(repository);
    }

    @Bean
    EntregaRepositoryPortImpl entregaRepositoryPortImpl(
            EntregaRepository entregaRepository) {
        return new EntregaRepositoryPortImpl(entregaRepository);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.fiap.logistica_entrega.domain") // Adjust to your entity package
                .persistenceUnit("logisticaPU")
                .build();
    }
}
