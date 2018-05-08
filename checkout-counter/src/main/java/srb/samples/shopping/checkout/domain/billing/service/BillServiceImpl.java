package srb.samples.shopping.checkout.domain.billing.service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import srb.samples.shopping.checkout.domain.billing.entity.Bill;
import srb.samples.shopping.checkout.domain.billing.entity.BillItem;
import srb.samples.shopping.checkout.domain.billing.entity.Entity;
import srb.samples.shopping.checkout.domain.billing.repository.BillRepository;
import srb.samples.shopping.checkout.domain.billing.vo.BillItemVO;
import srb.samples.shopping.checkout.domain.billing.vo.BillVO;
import srb.samples.shopping.checkout.domain.customer.entity.Cart;
import srb.samples.shopping.checkout.domain.customer.entity.Customer;
import srb.samples.shopping.checkout.domain.customer.service.CustomerService;
import srb.samples.shopping.checkout.domain.product.Category;
import srb.samples.shopping.checkout.domain.product.entity.Product;
import srb.samples.shopping.checkout.domain.product.service.ProductService;
import srb.samples.shopping.checkout.domain.taxation.service.TaxService;
import srb.samples.shopping.checkout.exception.CustomerNotFoundException;
import srb.samples.shopping.checkout.exception.EmptyCartException;

/**
 *
 * @author Sourabh Sharma
 */
@Service("billService")
public class BillServiceImpl extends BaseService<Bill, String> implements BillService {

	private BillRepository<Bill, String> billRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private TaxService taxService;

	/**
	 *
	 * @param billRepository
	 */
	@Autowired
	public BillServiceImpl(BillRepository<Bill, String> billRepository) {
		super(billRepository);
		this.billRepository = billRepository;
	}

	/**
	 *
	 * @param bill
	 * @throws Exception
	 */
	@Override
	public void update(Bill bill) throws Exception {
		billRepository.update(bill);
	}

	/**
	 *
	 * @param id
	 * @throws Exception
	 */
	@Override
	public void delete(String id) throws Exception {
		billRepository.remove(id);
	}

	/**
	 *
	 * @param billId
	 * @return
	 * @throws Exception
	 */
	@Override
	public Entity<?> findById(String billId) throws Exception {
		return billRepository.get(billId);
	}

	/**
	 *
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@Override
	public Collection<Bill> findByCriteria(Map<String, ArrayList<String>> name) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public BillVO getBill(String billId) throws Exception {
		BillVO billVO = new BillVO();
		Bill bill = (Bill) findById(billId);
		if (bill == null) {
			return billVO;
		}		
		String customerId = bill.getCustomerId();
		Customer customer = (Customer) customerService.findById(customerId);		
		billVO.setName(customer.getName());
		billVO.setAddress(bill.getAddress());
		billVO.setId(bill.getId());
		billVO.setDateTime(bill.getDateTime());
		List<BillItem> billItems = bill.getBillItems();
		List<BillItemVO> billItemsVO = new ArrayList<>();
		billVO.setTotalTax(BigDecimal.ZERO);
		billVO.setTotalPrice(BigDecimal.ZERO);
		billItems.forEach( billItem -> {
			float taxRate = billItem.getTaxPercentage();
			Category category = billItem.getCategory();
			BigDecimal price = billItem.getPrice();			
			BillItemVO billItemVO = new BillItemVO();
			billItemVO.setCode(billItem.getCode());
			billItemVO.setName(billItem.getName());
			billItemVO.setCategory(category);
			billItemVO.setPrice(price);
			billItemVO.setTaxPercentage(taxRate);
			Long quantity = billItem.getQuantity();
			billItemVO.setQuantity(quantity);
			BigDecimal taxAmount = (price.multiply(BigDecimal.valueOf((double)taxRate))).divide(BigDecimal.valueOf(100), 2)
					.multiply(BigDecimal.valueOf(quantity.longValue()));
			billItemVO.setTaxAmount(taxAmount);
			billVO.setTotalTax(billVO.getTotalTax().add(taxAmount));
			billItemVO.setTotalPrice(price.multiply(BigDecimal.valueOf(quantity.longValue())));
			billVO.setTotalPrice(billVO.getTotalPrice().add(billItemVO.getTotalPrice()));
			billItemVO.setTotalPriceWithTax(billItemVO.getTotalPrice().add(taxAmount));
			billItemsVO.add(billItemVO);
		});
		billVO.setBillItems(billItemsVO);
		billVO.setGrandTotal(billVO.getTotalPrice().add(billVO.getTotalTax()));
		return billVO;
	}	
	
	@Override
	public BillVO generateBill(String customerId) throws Exception {
		Bill bill = new Bill();
		BillVO billVO = new BillVO();
		Customer customer = (Customer) customerService.findById(customerId);
		if (customer == null) {
			Object[] args = {customerId};
			throw new CustomerNotFoundException("customerNotFound", args);
		}
		Map<String, Long> items = customer.getCart().getCartEntry();
		if (items.isEmpty()) {
			Object[] args = {customer.getId()};
			throw new EmptyCartException("emptyCart", args);
		}		
		billVO.setName(customer.getName());
		billVO.setAddress(customer.getAddress());
		billVO.setId(bill.getId());
		bill.setCustomerId(customer.getId());
		bill.setAddress(customer.getAddress());
		bill.setDateTime(Instant.now());
		billVO.setDateTime(bill.getDateTime());
		List<BillItem> billItems = new ArrayList<>();
		List<BillItemVO> billItemsVO = new ArrayList<>();
		billVO.setTotalTax(BigDecimal.ZERO);
		billVO.setTotalPrice(BigDecimal.ZERO);
		Map<String, Product> products = productService.getProducts(items.keySet());
		products.forEach((k, v) -> {
			Category category = v.getCategory();
			float taxRate = taxService.getTaxRate(category);
			BigDecimal price = v.getPrice();			
			BillItem billItem = new BillItem(k, price, taxRate);
			billItem.setName(v.getName());
			billItem.setCategory(category);
			BillItemVO billItemVO = new BillItemVO();
			billItemVO.setCode(billItem.getCode());
			billItemVO.setName(v.getName());
			billItemVO.setCategory(category);
			billItemVO.setPrice(price);
			billItemVO.setTaxPercentage(taxRate);
			Long quantity = items.get(v.getBarcode());
			billItemVO.setQuantity(quantity);
			billItem.setQuantity(quantity);
			BigDecimal taxAmount = (price.multiply(BigDecimal.valueOf((double)taxRate))).divide(BigDecimal.valueOf(100), 2)
					.multiply(BigDecimal.valueOf(quantity.longValue()));
			billItemVO.setTaxAmount(taxAmount);
			billVO.setTotalTax(billVO.getTotalTax().add(taxAmount));
			billItemVO.setTotalPrice(price.multiply(BigDecimal.valueOf(quantity.longValue())));
			billItemVO.setTotalPriceWithTax(billItemVO.getTotalPrice().add(taxAmount));
			billVO.setTotalPrice(billVO.getTotalPrice().add(billItemVO.getTotalPrice()));
			billItems.add(billItem);
			billItemsVO.add(billItemVO);
		});
		bill.setBillItems(billItems);
		billVO.setBillItems(billItemsVO);
		billVO.setGrandTotal(billVO.getTotalPrice().add(billVO.getTotalTax()));
		
		billRepository.add(bill);
		
		//Reset the cart after bill is generated
		customer.setCart(new Cart(new HashMap<String, Long>()));
		customerService.update(customer);
		return billVO;
	}
}
