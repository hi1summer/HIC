package tsinghua.hic.commons.swagger;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.function.Predicate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableOpenApi // Enable open api 3.0.3 spec
public class SwaggerConfig {
    @Bean
    public Docket openApiPetStore() {
        return new Docket(DocumentationType.OAS_30).groupName("open-api")
                .apiInfo(apiInfo()).select().paths(noerrorpaths()).build();
    }

    private Predicate<String> noerrorpaths() {
        return regex(".*/error.*").negate();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("API DOC")
//                .description("API Info")
//                .termsOfServiceUrl("http://springfox.io")
//                .contact(new Contact("summer", "", "gonick@163.com"))
//                .license("Apache License Version 2.0")
//                .licenseUrl(
//                        "https://github.com/springfox/springfox/blob/master/LICENSE")
                .version("1.0-beta").build();
    }

//    @Bean
//    public TestController testController() {
//        return new TestController();
//    }
//
//    @Bean
//    public VerifyController verifyController() {
//        return new VerifyController();
//    }

}
