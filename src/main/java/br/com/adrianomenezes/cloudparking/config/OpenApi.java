package br.com.adrianomenezes.cloudparking.config;

import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class OpenApi {

//    @Bean
//    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
//        return new OpenAPI().info(new Info().title("Controller API")
//                .version(appVersion)
//                .description("This is a sample server created using springdocs - a library for OpenAPI 3 with spring boot.")
//                .termsOfService("http://swagger.io/terms/")
//                .license(new License().name("Apache 2.0")
//                        .url("http://springdoc.org")));
//    }

    @Bean
    public Docket getDocket(){
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.adrianomenezes"))
                .build()
                .apiInfo(metaData())
                .securityContexts(Arrays.asList(getSecurityContext()))
                .securitySchemes(Arrays.asList(basicAuthScheme()));
    }

    private SecurityScheme basicAuthScheme() {
        return new BasicAuth("basicAuth");
    }
//    private List<SecurityScheme> basicAuth() {
//        List<SecurityScheme> schemeList = new ArrayList<>();
//        schemeList.add(new BasicAuth("basicAuth"));
//        return schemeList;
//    }

    private SecurityContext getSecurityContext() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(basicAuthReference()))
                .build();
    }

    private SecurityReference basicAuthReference() {
        return new SecurityReference("basicAuth",new AuthorizationScope[0]);
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Parking Cloud REST API")
                .description("Spring REST API for parking")
                .version("1.0.0")
                .license("")
                .licenseUrl("")
                .build();
    }
}
