package poc.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import poc.product.model.Product;

/**
 * 
 * @author Shiva
 *
 */

public interface ProductRepository extends JpaRepository<Product, Integer> {

}