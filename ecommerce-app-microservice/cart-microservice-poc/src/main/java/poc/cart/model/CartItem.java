package poc.cart.model;

import java.io.Serializable;

/**
 * 
 * @author Shiva
 *
 */
public class CartItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3786872986571713675L;
	private String id;

	public CartItem() {
	}

	private String name;
	private float price;
	private String currency;

	public CartItem(String id, String name, float price, String currency) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.currency = currency;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
