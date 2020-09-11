package poc.cart.repo;

import poc.cart.model.Cart;
import poc.cart.model.CartItem;

/**
 * 
 * @author Shiva
 *
 */
public interface CartRepository {
	Cart getCartById(String id);

	Cart addToCart(String id, CartItem cartItem);

	Boolean deleteKey(String id);

}
