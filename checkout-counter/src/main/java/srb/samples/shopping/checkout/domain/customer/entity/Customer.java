package srb.samples.shopping.checkout.domain.customer.entity;

/**
 *
 * @author Sourabh Sharma
 */
public class Customer extends BaseEntity<String> {

    private String address;
    private Cart cart;

    /**
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public Customer() {
    	// id is generated automatically at JPA
    	super(null);
    }
    
    /**
     *
     * @param name
     * @param id
     * @param address
     * @param tables
     */
    //TODO: Builder pattern
    public Customer(String name, String id, String address, Cart cart) {
        super(id);
        this.name = name;
        this.address = address;
        if (cart == null) {
        	cart = new Cart(null);
        }
        this.cart = cart;
    }


    /**
     * Overridden toString() method that return String presentation of the
     * Object
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("{id: %s, name: %s, address: %s, cart: %s}", this.getId(),
                this.getName(), this.getAddress(), this.getCart());
    }

	/**
	 * @return the cart
	 */
	public Cart getCart() {
		return cart;
	}

	/**
	 * @param cart the cart to set
	 */
	public void setCart(Cart cart) {
		this.cart = cart;
	}

}
