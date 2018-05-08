package srb.samples.shopping.checkout.domain.customer.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import srb.samples.shopping.checkout.domain.customer.entity.Customer;
import srb.samples.shopping.checkout.domain.customer.repository.CustomerRepository;
import srb.samples.shopping.checkout.domain.customer.resources.CustomerController;
import srb.samples.shopping.checkout.domain.customer.vo.CartItemVO;
import srb.samples.shopping.checkout.domain.product.service.ProductService;
import srb.samples.shopping.checkout.exception.CartItemsNotValidException;
import srb.samples.shopping.checkout.exception.CustomerNotFoundException;
import srb.samples.shopping.checkout.exception.InvalidBarcodeException;

/**
 *
 * @author Sourabh Sharma
 */
@Service("customerService")
public class CustomerServiceImpl extends BaseService<Customer, String> implements CustomerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
	private CustomerRepository<Customer, String> customerRepository;

	@Autowired
	private ProductService productService;

	/**
	 *
	 * @param customerRepository
	 */
	@Autowired
	public CustomerServiceImpl(CustomerRepository<Customer, String> customerRepository) {
		super(customerRepository);
		this.customerRepository = customerRepository;
	}

	@Override
	public void add(Customer customer) throws Exception {
		if (customerRepository.containsName(customer.getName())) {
			throw new Exception(String.format("There is already a customer with the name - %s", customer.getName()));
		}

		if (customer.getName() == null || "".equals(customer.getName())) {
			throw new Exception("Customer name cannot be null or empty string.");
		}
		super.add(customer);
	}

	/**
	 *
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@Override
	public Collection<Customer> findByName(String name) throws Exception {
		return customerRepository.findByName(name);
	}

	/**
	 *
	 * @param customer
	 * @throws Exception
	 */
	@Override
	public void update(Customer customer) throws Exception {
		customerRepository.update(customer);
	}

	/**
	 *
	 * @param id
	 * @throws Exception
	 */
	@Override
	public void delete(String id) throws Exception {
		customerRepository.remove(id);
	}

	/**
	 *
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	@Override
	public Customer findById(String customerId) throws Exception {
		Customer customer = customerRepository.get(customerId);
		if (customer == null) {
			Object[] args = {customerId};
			throw new CustomerNotFoundException("customerNotFound", args);
		}
		return customerRepository.get(customerId);
	}

	/**
	 *
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@Override
	public Collection<Customer> findByCriteria(Map<String, ArrayList<String>> name) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void addItemToCart(String customerId, CartItemVO cartItemVO) throws Exception {
		Customer customer = findById(customerId);
		if (customer == null) {
			Object[] args = {customerId};
			throw new CustomerNotFoundException("customerNotFound", args);
		}
		if (cartItemVO == null || cartItemVO.getCartEntry().isEmpty()) {
			Object[] args = {customerId};
			throw new CartItemsNotValidException("cartItemsNotValid", args);
		}
		Map<String, Long> cartItems = cartItemVO.getCartEntry();
		for (String barcode : cartItems.keySet()) {
			if (!productService.validateBarcode(barcode)) {
				Object[] args = {barcode};
				throw new InvalidBarcodeException("invalidBarcode", args);
			}
		}
		if (customer.getCart().getCartEntry().isEmpty()) {
			customer.getCart().getCartEntry().putAll(cartItemVO.getCartEntry());
		} else {
			cartItems.forEach((barcode, qty) -> {
				if (customer.getCart().getCartEntry().containsKey(barcode)) {
					Long existingQty = customer.getCart().getCartEntry().get(barcode);
					customer.getCart().getCartEntry().put(barcode,
							Long.valueOf(existingQty.longValue() + qty.longValue()));
				} else {
					customer.getCart().getCartEntry().put(barcode, qty);
				}
			});
		}
		customerRepository.update(customer);
	}
}
