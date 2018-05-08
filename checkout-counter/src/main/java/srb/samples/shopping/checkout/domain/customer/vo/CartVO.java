package srb.samples.shopping.checkout.domain.customer.vo;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sourabh Sharma
 */
public class CartVO {
	private Map<String, Long> cartEntry;

	/**
	 * Constructor
	 */
	public CartVO() {
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
		this.cartEntry = new HashMap<>(cartEntry);
	}
}
