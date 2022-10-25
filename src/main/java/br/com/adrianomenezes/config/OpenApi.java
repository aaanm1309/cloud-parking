package br.com.adrianomenezes.config;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Value;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


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

//    @Bean
//    public Docket getDocket(){
//        return new Docket(DocumentationType.OAS_30)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("br.com.adrianomenezes"))
//                .build()
//                .apiInfo(metaData());
//    }
//
//    private ApiInfo metaData() {
//        return new ApiInfoBuilder()
//                .title("Parking Cloud REST API")
//                .description("Spring REST API for parking")
//                .version("1.0.0")
//                .license("")
//                .licenseUrl("")
//                .build();
//    }
}
