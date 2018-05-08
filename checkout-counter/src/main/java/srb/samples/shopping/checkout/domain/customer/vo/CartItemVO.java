package srb.samples.shopping.checkout.domain.customer.vo;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sourabh Sharma
 */
public class CartItemVO {
	private Map<String, Long> cartEntry = new HashMap<>();

	/**
	 * Constructor
	 */
	public CartItemVO() {
	}

	/**
	 * @return the cartEntry
	 */
	public Map<String, Long> getCartEntry() {
		return cartEntry;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CartVO [cartEntry=" + cartEntry + "]";
	}

	/**
	 * @param cartEntry
	 *            the cartEntry to set
	 */
	public void setCartEntry(Map<String, Long> cartEntry) {
		this.cartEntry = cartEntry;
	}
}
