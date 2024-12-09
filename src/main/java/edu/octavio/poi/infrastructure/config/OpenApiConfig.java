package edu.octavio.poi.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(name = "Octavio Piratininga",
                email = "59701790+Pira4Ever@users.noreply.github.com",
                url = "https://www.github.com/Pira4Ever"),
                description = "OpenApi documentation for Spring Boot",
                title = "OpenApi specification - Octavio",
                version = "1.0",
                license = @License(
                        name = "MIT",
                        url = "https://some-url.com"
                ),
                termsOfService = "Terms of Service"
        )
)
public class OpenApiConfig {
}
