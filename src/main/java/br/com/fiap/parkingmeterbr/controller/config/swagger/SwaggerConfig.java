package br.com.fiap.parkingmeterbr.controller.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI usersApi() {

        Info info = new Info()
                .title("Api para cadastro e consulta de parquímetros")
                .version("0.0.1")
                .description("Esta API expõe endpoints para gerenciar cadastro de parquímetros");

        List<Tag> tags = Arrays.asList(
                new Tag().name("Parquímetros").description("Gerenciamento de parquímetros")
        );

        Server localServer = new Server();
        localServer.setUrl("http://localhost:8001");
        localServer.setDescription("Server URL local");

        return new OpenAPI().info(info).tags(tags).servers(Arrays.asList(localServer));

    }

}
