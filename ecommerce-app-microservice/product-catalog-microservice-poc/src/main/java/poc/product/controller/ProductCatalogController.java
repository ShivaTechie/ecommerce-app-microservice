package poc.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import poc.product.model.Product;
import poc.product.service.ProductService;

@CrossOrigin(origins = { "http://localhost:3000" }, maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping("/product")
/**
 * 
 * @author Shiva
 *
 */
public class ProductCatalogController extends ResponseEntityExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(ProductCatalogController.class);

	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public String index() {
		return "<h1>Welcome to Product Catalog API!</h1>";
	}

	@GetMapping(value = "/products/recommendations")
	public @ResponseBody List<Product> productRecommendations() {
		log.info("fetching all products");
		return productService.findAllProducts();
	}

	@GetMapping("/products/{id}")
	public Product product(@PathVariable("id") int id) throws Exception {

		Product product = productService.findProductById(id);
		System.out.println(product);
		if (product != null) {
			log.info("Fetched product is {}", product);
			return product;
		} else {
			log.info("Product is not present");
			return null;
		}
	}

	@PutMapping(value = "/products")
	public Product addProduct(@RequestBody Product product) throws Exception {
		System.out.println(product);
		return productService.addProduct(product);
	}

	@PostMapping(value = "/products")
	public Product updateProduct(@RequestBody Product product) throws Exception {
		return productService.updateProduct(product);
	}

	@ExceptionHandler(Exception.class)
	void handleExceptions(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Internal server error. We will be addressing this issue soon.");
	}
}