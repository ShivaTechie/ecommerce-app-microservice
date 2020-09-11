package poc.cart.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import poc.cart.model.Cart;
import poc.cart.model.CartItem;
import poc.cart.repo.CartRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * 
 * @author Shiva
 *
 */
@Repository
public class CartRepositoryImpl implements CartRepository {

	private static final Logger log = LoggerFactory.getLogger(CartRepositoryImpl.class);

	@Autowired
	RedisTemplate<String, Cart> redisTemplate;

	@Override
	public Cart getCartById(String id) {
		return redisTemplate.opsForValue().get(id);
	}

	@Override
	public Cart addToCart(String id, CartItem cartItem) {
		float total = 0f;
		Cart cart = null;
		log.info("cart id: {}", id);
		// If cart does not exist.
		if ((id == null) || (id.equalsIgnoreCase(""))) {
			log.info("Missing id, creating new cart.");
			cart = createCart(UUID.randomUUID().toString(), cartItem);
		} else {
			cart = getCartById(id);
			log.info("Retrieve existing cart by id: {}, cart: {}", id, cart);
			if (cart == null) {
				cart = createCart(id, cartItem);
			} else {
				cart.getItems().add(cartItem);
			}
		}

		for (CartItem item : cart.getItems()) {
			total += item.getPrice();
		}
		cart.setTotal(total);
		log.info("cart: " + cart);
		redisTemplate.opsForValue().set(cart.getId(), cart);
		return cart;
	}

	private Cart createCart(String id, CartItem cartItem) {
		List<CartItem> cartItems = new ArrayList<CartItem>();
		cartItems.add(cartItem);
		Cart cart = new Cart(id, cartItems, cartItem.getPrice(), cartItem.getCurrency());
		// If cart by id exist, then add to it.
		return cart;
	}

	@Override
	public Boolean deleteKey(String id) {

		if (Objects.nonNull(id))
			return redisTemplate.delete(id);
		else
			return false;

	}

}
