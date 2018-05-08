package srb.samples.shopping.checkout.domain.billing.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import srb.samples.shopping.checkout.domain.billing.service.BillService;
import srb.samples.shopping.checkout.domain.billing.vo.BillVO;
import srb.samples.shopping.checkout.exception.CustomerNotFoundException;
import srb.samples.shopping.checkout.exception.EmptyCartException;

/**
 *
 * @author Sourabh Sharma
 */
@RestController
@RequestMapping("/v1/bills")
public class BillController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BillController.class);

	/**
	 *
	 */
	private BillService billService;

	/**
	 *
	 * @param billService
	 */
	@Autowired
	public BillController(BillService billService) {
		this.billService = billService;
	}

	/**
	 * Fetch bills with the given id. <code>http://.../v1/bills/{bill_id}</code>
	 * will return bill with given id.
	 *
	 * @param id
	 * @return A non-null, non-empty collection of bills.
	 */
	@RequestMapping(value = "/{bill_id}", method = RequestMethod.GET)
	public ResponseEntity<BillVO> findById(@PathVariable("bill_id") String id) {
		LOGGER.info("bill-service findById() invoked:{} for {} ", billService.getClass().getName(), id);
		id = id.trim();
		BillVO bill;
		try {
			bill = billService.getBill(id);
		} catch (Exception ex) {
			LOGGER.error("Exception raised findById REST Call {0}", ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return bill.getId() != null ? new ResponseEntity<>(bill, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Generate bill for given user. <code>http://.../v1/bills/customer/{customer_id}</code>
	 * will add item in cart of given bill.
	 *
	 * @param cart
	 * @return A non-null, non-empty collection of bills.
	 */
	@RequestMapping(value = "customer/{customer_id}", method = RequestMethod.POST)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
	public ResponseEntity<BillVO> generateBill(@PathVariable("customer_id") String customerId) throws Exception {
		LOGGER.info("bill-service genarateBill() invoked:{} for customer id {} .", billService.getClass().getName(),
				customerId);
		BillVO bill;
		try {
			bill = billService.generateBill(customerId);
		} catch (EmptyCartException ex) {
			LOGGER.error("Exception raised genarateBill REST Call {0}", ex);
			throw ex;
		} catch (CustomerNotFoundException ex) {
			LOGGER.error("Exception raised genarateBill REST Call {0}", ex);
			throw ex;			
		} catch (Exception ex) {
			LOGGER.error("Exception raised genarateBill REST Call {0}", ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return bill != null ? new ResponseEntity<>(bill, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
