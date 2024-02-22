package com.uncledavecode.api_rest.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${spring.security.oauth2.resourceserver.endpoint.authorization-uri}")
    private String authURI;

    @Value("${spring.security.oauth2.resourceserver.endpoint.token-uri}")
    private String tokenURI;

    @Bean
    public OpenAPI openAPIConfig() {
        return new OpenAPI()
                .components(
                        new Components()
                                .addSecuritySchemes("security_auth", new SecurityScheme()
                                        .type(SecurityScheme.Type.OAUTH2)
                                        .in(SecurityScheme.In.HEADER)
                                        .flows(new OAuthFlows()
                                                .authorizationCode(new OAuthFlow()
                                                        .authorizationUrl(authURI)
                                                        .tokenUrl(tokenURI)
                                                        .scopes(new Scopes()
                                                                .addString("openid", "OpenID connect")
                                                                .addString("profile", "User profile")
                                                        )
                                                )
                                        )))
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("API REST")
                        .description("API REST")
                        .version("1.0.0")
                );


    }
}
