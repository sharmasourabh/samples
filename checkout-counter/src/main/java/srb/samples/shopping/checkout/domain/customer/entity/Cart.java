package srb.samples.shopping.checkout.domain.customer.entity;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sourabh Sharma
 */
public class Cart extends BaseEntity<String> {

    private Map<String, Long> cartEntry;
	private static BigInteger sequence = BigInteger.valueOf(0L);
	
    /**
	 * @return the cartEntry
	 */
	public Map<String, Long> getCartEntry() {
		return cartEntry;
	}


	/**
	 * @param cartEntry the cartEntry to set
	 */
	public void setCartEntry(Map<String, Long> cartEntry) {
		this.cartEntry = cartEntry;
	}


	/**
     *
     * @param name
     * @param id
     * @param address
     * @param tables
     */
    //TODO: Builder pattern would be implemented for actual case
    public Cart(Map<String, Long> cartEntry) {    	
        super(nextSequenceString());
        this.cartEntry = cartEntry != null ? cartEntry : new HashMap<String, Long>();
    }
    
	public static String getCurrentSequence() {
		return sequence.toString();
	}

	public static String nextSequenceString() {
		return nextSequence().toString();
	}

	public static BigInteger nextSequence() {
		sequence = sequence.add(BigInteger.valueOf(1L));
		return sequence;
	}

	/**
     * Overridden toString() method that return String presentation of the
     * Object
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("{id: %s, name: %s, cartEntry: %s}", this.getId(),
                this.getName(), this.getCartEntry());
    }

}
