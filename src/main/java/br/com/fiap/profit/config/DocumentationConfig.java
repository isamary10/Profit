package br.com.fiap.profit.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class DocumentationConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
      .info(new Info()
        .title("Profit API")
        .version("v1")
        .summary("API do APP para aprendizado e simulador de investimentos")
        .contact(new Contact().name("Isa e Giovana").email("contato@profit.com.br"))
        .license(new License().name("MIT Open Source").url("http://profit.com/licenca"))
      )
        .components(new Components()
          .addSecuritySchemes("bearer-key",
            new SecurityScheme()
              .type(SecurityScheme.Type.HTTP)
                .scheme("bearer").bearerFormat("JWT")));
}


}
