package poc.cart.config;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import poc.cart.model.Cart;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author Shiva
 *
 */
@Configuration
@EnableCaching
@EnableSwagger2
@ComponentScan(basePackages = "poc.cart")
public class CacheConfig extends CachingConfigurerSupport {
	private static final Logger log = LoggerFactory.getLogger(CacheConfig.class);

	@Bean
	RedisTemplate<String, Cart> redisTemplate() {
		RedisTemplate<String, Cart> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		return redisTemplate;
	}

	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		log.debug("{}", this.getClass().getName());
		log.debug("Redis connection created successfully.");
		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
		redisConnectionFactory.getPoolConfig().setMaxIdle(30);
		redisConnectionFactory.getPoolConfig().setMinIdle(10);

		return redisConnectionFactory;
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("poc.cart")).paths(PathSelectors.any()).build()
				.apiInfo(getApiInformation());
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
		public static final String SWAGGER_DESCRIPTION = "This is a cart service which is used to save the data accross the application to maintain the states of the data.";
		public static final String SWAGGER_TITLE = "Cart Service";

	}
}