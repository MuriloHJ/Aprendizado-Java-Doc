package com.br.pokedex.config;

import com.br.pokedex.controller.PokemonController;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "API de simulação de Pokedex",
                version = "v1.0.0",
                description = "API para simulação didádica de uma Pokedex funcional"
        )
)

@Configuration
public class OpenApiConfig
{

}
