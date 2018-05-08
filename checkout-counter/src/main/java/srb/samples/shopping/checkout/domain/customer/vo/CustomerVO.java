package srb.samples.shopping.checkout.domain.customer.vo;

import java.util.HashMap;

import org.springframework.beans.BeanUtils;

import srb.samples.shopping.checkout.domain.customer.entity.Customer;
import srb.samples.shopping.checkout.domain.customer.vo.CartVO;

/**
 *
 * @author Sourabh Sharma
 */
public class CustomerVO {
	private String name;
	private String id;
	private String address;
	private CartVO cartVO;

	/**
	 * Constructor
	 */
	public CustomerVO() {
	}

	/**
	 * 
	 * @param customer
	 */
	public CustomerVO(Customer customer) {
		this.id = customer.getId();
		this.name = customer.getName();
		this.address = customer.getAddress();
		this.cartVO = new CartVO();
		this.cartVO.setCartEntry(new HashMap<>(customer.getCart().getCartEntry()));
	}

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

	/**
	 *
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 *
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 *
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Overridden toString() method that return String presentation of the Object
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return String.format("{id: %s, name: %s, address: %s, cartVO: %s}", this.getId(), this.getName(),
				this.getAddress(), this.getCartVO());
	}

	/**
	 * @return the cartVO
	 */
	public CartVO getCartVO() {
		return cartVO;
	}

	/**
	 * @param cartVO
	 *            the cartVO to set
	 */
	public void setCartVO(CartVO cartVO) {
		this.cartVO = cartVO;
	}
}
