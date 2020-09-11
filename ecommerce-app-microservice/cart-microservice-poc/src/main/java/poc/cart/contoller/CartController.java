package poc.cart.contoller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import poc.cart.model.Cart;
import poc.cart.model.CartItem;
import poc.cart.repo.CartRepository;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * @author Shiva
 *
 */
@CrossOrigin(origins = { "http://localhost:3000" }, maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping("/cart")
public class CartController extends ResponseEntityExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(CartController.class);

	@Autowired
	private CartRepository cartRepository;

	@PostConstruct
	public void init() throws IOException {
	}

	@GetMapping("/cart")
	public String index() {
		return "<h1>Welcome to Cart API!</h1>";
	}

	@GetMapping(value = "/cart/{id}")
	public Cart cart(@PathVariable("id") String id) {
		log.debug("Received request for cart by id: {}", id);
		Cart cart = cartRepository.getCartById(id);
		log.debug("Cart: {}", cart);
		return cart;
	}

	@PostMapping(value = "/cart/{id}")
	public Cart cart(@PathVariable("id") String id, @RequestBody CartItem cartItem) {

		System.out.println("Hello here");
		log.debug("Received request to add item to cart by id: {}", id);
		Cart cart = cartRepository.addToCart(id, cartItem);
		log.debug("Cart: {}", cart);
		return cart;
	}

	@PostMapping(value = "/cart")
	public Cart cart(@RequestBody CartItem cartItem) {
		log.debug("Received request to add item to cart without id.");
		Cart cart = cartRepository.addToCart(null, cartItem);
		log.debug("Cart: {}", cart);
		return cart;
	}

	@DeleteMapping(value = "/cart/{id}")
	public boolean deleteCart(@PathVariable("id") String id) {
		log.debug("Received request to delete item to cart without id.");
		boolean bool = cartRepository.deleteKey(id);
		log.debug("Cart: {}", bool);
		return bool;
	}

	@ExceptionHandler(Exception.class)
	void handleExceptions(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Internal server error. We will be addressing this issue soon.");
	}
}