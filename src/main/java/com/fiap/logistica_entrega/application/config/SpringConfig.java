package com.fiap.logistica_entrega.application.config;

import com.fiap.logistica_entrega.domain.ports.outbound.EntregaRepositoryPort;
import com.fiap.logistica_entrega.domain.usecase.CreateEntrega;
import com.fiap.logistica_entrega.domain.usecase.DeleteEntrega;
import com.fiap.logistica_entrega.domain.usecase.FindEntrega;
import com.fiap.logistica_entrega.domain.usecase.UpdateEntrega;
import com.fiap.logistica_entrega.infrastructure.EntregaRepositoryPortImpl;
import com.fiap.logistica_entrega.infrastructure.jpa.EntregaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
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

}
