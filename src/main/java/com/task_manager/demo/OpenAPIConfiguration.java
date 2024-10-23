package com.task_manager.demo;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        System.out.println("Set up Open API");
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");


        Info information = new Info()
                .title("Task Management System API")
                .version("1.0")
                .description("This API exposes endpoints to manage task.");

        return new OpenAPI().info(information).servers(List.of(server));
    }
}
