package com.knock.routingrules.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    // COMMENTED OUT: This GroupedOpenApi causes the /api-docs/1.0 path issue
    // The grouped API creates versioned endpoints which conflicts with Swagger UI
    /*
    private final String[] packagesToScan = {"com.knock.routingrules"};

    @Bean
    public GroupedOpenApi openApiV1D0() {
        final String[] paths = {"/1.0/**"};
        return GroupedOpenApi.builder()
                .group("1.0")
                .pathsToMatch(paths)
                .packagesToScan(packagesToScan)
                .addOpenApiCustomizer(openApi -> {
                    openApi.setOpenapi("3.0.0");
                    openApi.paths(removeSubstringVersionFromPath(openApi.getPaths(), "1.0"));
                    openApi.setServers(getServerForApi("/api/routing-rules/1.0"));
                    openApi.info(new Info()
                            .title("Routing Rules API")
                            .version("1.0")
                            .description("API for managing routing rules and calculating member assignments"));
                })
                .build();
    }

    public List<Server> getServerForApi(final String ServerUrl) {
        final List<Server> serverList = new ArrayList<>();
        final Server server = new Server();
        server.setUrl(ServerUrl);
        serverList.add(server);
        return serverList;
    }

    public static Paths removeSubstringVersionFromPath(final Paths openApiPaths, String version) {
        final Paths paths = new Paths();
        for (Map.Entry<String, PathItem> entry : openApiPaths.entrySet()) {
            final int endOfVersionIndex = entry.getKey().indexOf(version) + version.length();
            paths.put(entry.getKey().substring(endOfVersionIndex), entry.getValue());
        }
        return paths;
    }
    */

    // SIMPLE OpenAPI configuration that works with standard /api-docs path
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Routing Rules API")
                        .version("1.0")
                        .description("API for managing routing rules and calculating member assignments")
                        .contact(new Contact().name("API Support")));
    }
}