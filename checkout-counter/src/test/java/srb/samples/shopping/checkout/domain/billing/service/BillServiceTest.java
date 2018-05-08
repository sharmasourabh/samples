package srb.samples.shopping.checkout.domain.billing.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import srb.samples.shopping.checkout.domain.billing.entity.Bill;
import srb.samples.shopping.checkout.domain.billing.repository.BillRepository;
import srb.samples.shopping.checkout.domain.billing.vo.BillVO;
import srb.samples.shopping.checkout.domain.customer.entity.Cart;
import srb.samples.shopping.checkout.domain.customer.entity.Customer;
import srb.samples.shopping.checkout.domain.customer.entity.Entity;
import srb.samples.shopping.checkout.domain.customer.service.CustomerService;
import srb.samples.shopping.checkout.domain.product.Category;
import srb.samples.shopping.checkout.domain.product.entity.Product;
import srb.samples.shopping.checkout.domain.product.service.ProductService;
import srb.samples.shopping.checkout.domain.taxation.service.TaxService;

@RunWith(SpringJUnit4ClassRunner.class)
public class BillServiceTest {

	@Mock
	private BillRepository<Product, String> billRepository;

	@InjectMocks
	private BillServiceImpl billService;

	@Mock
	CustomerService customerService;
	
	@Mock
	ProductService productService;
	
	@Mock
	TaxService taxService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		Bill.resetSequence();
	}

	@Test
	public void testGenerateBill() throws Exception {
		Customer customer = new Customer("Le Meurice", "1", "228 rue de Rivoli, 75001, Paris", null);
		Cart cart = new Cart(null);
		cart.getCartEntry().put("123456789005", 2L);
		cart.getCartEntry().put("123456789008", 2L);
		customer.setCart(cart);
		customer.setId("1");
		when(customerService.findById("1")).thenReturn((Entity) customer);
		
		Map<String, Product> products = new HashMap<>();
		Product product = new Product("5", "Product 5", "123456789005", Category.B, BigDecimal.valueOf(300.00),
				null);
		products.put(product.getBarcode(), product);
		product = new Product("8", "Product 8", "123456789008", Category.B, BigDecimal.valueOf(600.00),
				null);
		products.put(product.getBarcode(), product);
		when(productService.getProducts(cart.getCartEntry().keySet())).thenReturn(products);
		
		when(taxService.getTaxRate(Category.B)).thenReturn(20f);
		
		// when(billRepository.add((Bill) )).thenReturn(toDoList);
		
		BillVO bill = billService.generateBill("1");
		assertEquals("1", bill.getId());
		assertEquals("Le Meurice", bill.getName());
		assertEquals("228 rue de Rivoli, 75001, Paris", bill.getAddress());
		assertEquals(2, bill.getBillItems().size());
		assertEquals("Product 5", bill.getBillItems().get(0).getName());
		assertEquals(BigDecimal.valueOf(720.0).stripTrailingZeros(), bill.getBillItems().get(0).getTotalPriceWithTax().stripTrailingZeros());
		assertEquals("Product 8", bill.getBillItems().get(1).getName());
		assertEquals(BigDecimal.valueOf(1440.0).stripTrailingZeros(), bill.getBillItems().get(1).getTotalPriceWithTax().stripTrailingZeros());
		assertEquals(BigDecimal.valueOf(2160.0).stripTrailingZeros(), bill.getGrandTotal().stripTrailingZeros());
	}
}
