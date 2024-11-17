package project.personal.personalstoremanagementproject.confiugation;

import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * ApiConfig - Configuration for Swagger OpenAPI
 */
@Configuration
public class ApiConfig {

    /**
     * Configures the OpenAPI object for Swagger.
     *
     * @param title the title of the API.
     * @param url the URL of the API.
     * @param version the version of the API.
     * @param server the server URL of the API.
     * @return an instance of {@link OpenAPI} object.
     */
    @Bean
    public OpenAPI openAPI(@Value("${open.api.title}") String title,
                           @Value("${open.api.url}") String url,
                           @Value("${open.api.version}") String version,
                           @Value("${open.api.server}") String server){
        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .license(new License().url(url))
                        .version(version))
                .servers(Collections.singletonList(new Server().url(server)));
    }
}
