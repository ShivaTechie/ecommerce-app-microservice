package poc.product.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import poc.product.model.Product;
import poc.product.repo.ProductRepository;

/**
 * 
 * @author Shiva
 *
 */
@Repository
public class ProductService {

	private static final Logger log = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProductRepository productRepository;

	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}

	public Product findProductById(int id) {

		log.debug("Trying retrieve product with id: {}", id);
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent())
			return product.get();
		else
			return null;
	}

	public Product addProduct(Product product) {

		System.out.println(product);
		return productRepository.save(product);

	}

	public Product updateProduct(Product product) {
		return addProduct(product);

	}
}
