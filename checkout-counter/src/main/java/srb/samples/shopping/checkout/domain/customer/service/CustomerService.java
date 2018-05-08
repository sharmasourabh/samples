package srb.samples.shopping.checkout.domain.customer.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import srb.samples.shopping.checkout.domain.customer.entity.Entity;
import srb.samples.shopping.checkout.domain.customer.vo.CartItemVO;
import srb.samples.shopping.checkout.domain.customer.entity.Customer;

/**
 *
 * @author Sourabh Sharma
 */
public interface CustomerService {

	/**
	 *
	 * @param customer
	 * @throws Exception
	 */
	public void add(Customer customer) throws Exception;

	/**
	 *
	 * @param customer
	 * @throws Exception
	 */
	public void update(Customer customer) throws Exception;

	/**
	 *
	 * @param customer
	 * @throws Exception
	 */
	public void addItemToCart(String customerId, CartItemVO cartItemVO) throws Exception;

	/**
	 *
	 * @param id
	 * @throws Exception
	 */
	public void delete(String id) throws Exception;

	/**
	 *
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	public Entity<?> findById(String customerId) throws Exception;

	/**
	 *
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Collection<Customer> findByName(String name) throws Exception;

	/**
	 *
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Collection<Customer> findByCriteria(Map<String, ArrayList<String>> name) throws Exception;
}
