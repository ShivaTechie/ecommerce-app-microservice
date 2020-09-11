package poc.cart.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Shiva
 *
 */
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7365739551748423456L;
	private String id;
	private List<CartItem> items;
	private float total;
	private String currency;

	public Cart() {
	}

	public Cart(String id, List<CartItem> items, float total, String currency) {
		this.id = id;
		this.items = items;
		this.total = total;
		this.currency = currency;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public int hashCode() {
		return Integer.parseInt(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null || obj instanceof Cart) {
			Cart c = (Cart) obj;
			if (id == c.id && total == c.total && currency.equals(c.currency))
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Cart[" + id + "," + total + "," + currency + "]";
	}
}
