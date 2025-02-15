package com.fiap.logistica_entrega.application.config;

import com.fiap.logistica_entrega.domain.ports.outbound.EntregaRepositoryPort;
import com.fiap.logistica_entrega.domain.usecase.CreateEntrega;
import com.fiap.logistica_entrega.infrastructure.EntregaRepositoryPortImpl;
import com.fiap.logistica_entrega.infrastructure.jpa.EntregaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringConfig implements WebMvcConfigurer {

  @Bean
  public CreateEntrega productCreateUseCase(EntregaRepositoryPort repository) {
    return new CreateEntrega(repository);
  }

  @Bean
  EntregaRepositoryPortImpl entregaRepositoryPortImpl(
      EntregaRepository entregaRepository) {
    return new EntregaRepositoryPortImpl(entregaRepository);
  }
}
