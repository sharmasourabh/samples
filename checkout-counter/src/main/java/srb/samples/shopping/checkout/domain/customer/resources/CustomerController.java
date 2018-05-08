package srb.samples.shopping.checkout.domain.customer.resources;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import srb.samples.shopping.checkout.domain.customer.entity.Customer;
import srb.samples.shopping.checkout.domain.customer.service.CustomerService;
import srb.samples.shopping.checkout.domain.customer.vo.CartItemVO;
import srb.samples.shopping.checkout.domain.customer.vo.CartVO;
import srb.samples.shopping.checkout.domain.customer.vo.CustomerVO;
import srb.samples.shopping.checkout.exception.CartItemsNotValidException;
import srb.samples.shopping.checkout.exception.CustomerNotFoundException;
import srb.samples.shopping.checkout.exception.InvalidBarcodeException;

/**
 *
 * @author Sourabh Sharma
 */
@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	/**
	 *
	 */
	protected CustomerService customerService;

	/**
	 *
	 * @param customerService
	 */
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	/**
	 * Fetch customers with the specified name. A partial case-insensitive match is
	 * supported. So <code>http://.../customers/rest</code> will find any customers
	 * with upper or lower case 'rest' in their name.
	 *
	 * @param name
	 * @return A non-null, non-empty collection of customers.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Customer>> findByName(@RequestParam("name") String name) {
		LOGGER.info("customer-service findByName() invoked:{} for {} ", customerService.getClass().getName(), name);
		name = name.trim().toLowerCase();
		Collection<Customer> customers;
		try {
			customers = customerService.findByName(name);
		} catch (Exception ex) {
			LOGGER.error("Exception raised findByName REST Call", ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return customers.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(customers, HttpStatus.OK);
	}

	/**
	 * Fetch customers with the given id.
	 * <code>http://.../v1/customers/{customer_id}</code> will return customer with
	 * given id.
	 *
	 * @param id
	 * @return A non-null, non-empty collection of customers.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/{customer_id}", method = RequestMethod.GET)
	public ResponseEntity<CustomerVO> findById(@PathVariable("customer_id") String id) throws Exception {
		LOGGER.info("customer-service findById() invoked:{} for {} ", customerService.getClass().getName(), id);
		id = id.trim();
		CustomerVO customerVO;
		Customer customer;
		try {
			customer = (Customer) customerService.findById(id);
			customerVO = new CustomerVO(customer);
		} catch (CustomerNotFoundException ex) {
			LOGGER.error("Exception raised genarateBill REST Call {0}", ex);
			throw ex;
		} catch (Exception ex) {
			LOGGER.error("Exception raised findById REST Call {0}", ex);
			throw ex;
		}
		return customerVO != null ? new ResponseEntity<>(customerVO, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Add item in cart of given user.
	 * <code>http://.../v1/customers/{customer_id}/cart</code> will add item in cart
	 * of given customer.
	 *
	 * @param cart
	 * @return A non-null, non-empty collection of customers.
	 * @throws Exception 
	 */
	@RequestMapping(value = "{customer_id}/cart", method = RequestMethod.POST)
	public ResponseEntity<CustomerVO> addItemToCart(@PathVariable("customer_id") String customerId,
			@RequestBody CartItemVO cartItemVO) throws Exception {
		LOGGER.info("customer-service update() invoked:{} for customer {} add item {} in cart.",
				customerService.getClass().getName(), customerId, cartItemVO);
		try {
			customerService.addItemToCart(customerId, cartItemVO);
		} catch (CustomerNotFoundException | InvalidBarcodeException | CartItemsNotValidException ex) {
			LOGGER.error("Exception raised genarateBill REST Call {0}", ex);
			throw ex;
		} catch (Exception ex) {
			LOGGER.error("Exception raised findByBarcode REST Call {0}", ex);
			throw ex;
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Update cart of given user. (for multiple items update). It would replace the
	 * existing with new one.
	 * <code>http://.../v1/customers/{customer_id}/cart</code> will update cart of
	 * given customer.
	 *
	 * @param cart
	 * @return A non-null, non-empty collection of customers.
	 * @throws Exception
	 */
	@RequestMapping(value = "{customer_id}/cart", method = RequestMethod.PUT)
	public ResponseEntity<CustomerVO> findByBarcode(@PathVariable("customer_id") String id, CartVO cart)
			throws Exception {
		LOGGER.info("customer-service findByBarcode() invoked:{} for {} ", customerService.getClass().getName(), cart);
		CustomerVO customerVO;
		Customer customer;
		try {
			customer = (Customer) customerService.findById(id);
			customer.getCart().setCartEntry(cart.getCartEntry());
			customerVO = new CustomerVO(customer);
			customerService.update(customer);
		} catch (Exception ex) {
			LOGGER.error("Exception raised genarateBill REST Call {0}", ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return customerVO != null ? new ResponseEntity<>(customerVO, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Add customer with the specified information.
	 *
	 * @param customerVO
	 * @return A non-null customer.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Customer> add(@RequestBody CustomerVO customerVO) {
		LOGGER.info("customer-service add() invoked: %s for %s", customerService.getClass().getName(),
				customerVO.getName());
		System.out.println(customerVO);
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerVO, customer);
		try {
			customerService.add(customer);
		} catch (Exception ex) {
			LOGGER.error("Exception raised add Bill REST Call {0}", ex);
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
