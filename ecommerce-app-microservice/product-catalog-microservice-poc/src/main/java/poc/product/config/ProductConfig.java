package poc.product.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "poc.product")
/**
 * 
 * @author Shiva
 *
 */
public class ProductConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("poc.product"))
				.paths(PathSelectors.any()).build().apiInfo(getApiInformation());
	}

	private ApiInfo getApiInformation() {
		return new ApiInfo(Constant.SWAGGER_TITLE, Constant.SWAGGER_DESCRIPTION, Constant.SWAGGER_VERSION,
				Constant.SWAGGER_TERMS_OF_SERVICE_URL,
				new Contact(Constant.SWAGGER_CODERS, Constant.SWAGGER_GIT_REPO, Constant.SWAGGER_EMAIL_ID),
				Constant.SWAGGER_API_LICENCE, Constant.SWAGGER_LICENCE_URL, Collections.emptyList());
	}

	public static class Constant {

		public static final String SWAGGER_TERMS_OF_SERVICE_URL = "";
		public static final String SWAGGER_LICENCE_URL = "";
		public static final String SWAGGER_API_LICENCE = "";
		public static final String SWAGGER_EMAIL_ID = "shivatechnocrat@gmail.com";
		public static final String SWAGGER_GIT_REPO = "";
		public static final String SWAGGER_CODERS = "Shiva";
		public static final String SWAGGER_VERSION = "1.0";
		public static final String SWAGGER_DESCRIPTION = "This is a product catalogues service which is used to to display the all data in server";
		public static final String SWAGGER_TITLE = "Product Service";

	}
}
