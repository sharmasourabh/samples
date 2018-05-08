package srb.samples.shopping.checkout.domain.billing.resources;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import srb.samples.shopping.checkout.CheckoutApplication;
import srb.samples.shopping.checkout.domain.billing.service.BillService;
import srb.samples.shopping.checkout.domain.billing.vo.BillItemVO;
import srb.samples.shopping.checkout.domain.billing.vo.BillVO;
import srb.samples.shopping.checkout.domain.product.Category;
import srb.samples.shopping.checkout.exception.CustomerNotFoundException;
import srb.samples.shopping.checkout.exception.EmptyCartException;
import srb.samples.shopping.checkout.exception.EndpointErrorHandler;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CheckoutApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BillControllerUnitTest {
	private MockMvc mockMvc;

	@Mock
	private BillService billService;

	@InjectMocks
	private BillController billController;

	@Before
	public void setup() {
		StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.addMessage("customerNotFound", Locale.US, "Customer {0} not found.");
        messageSource.addMessage("emptyCart", Locale.US, "Customer {0} has an empty cart");
		this.mockMvc = MockMvcBuilders.standaloneSetup(billController)
				.setControllerAdvice(new EndpointErrorHandler(messageSource)).build();
	}

	@Test
	public void verifyGetBillFindByIdFailWith404() throws Exception {
		BillVO bill = new BillVO();
		when(billService.getBill("1")).thenReturn(bill);

		mockMvc.perform(get("/v1/bills/{bill_id}", 1))
			.andExpect(status().isNotFound());
		verify(billService, times(1)).getBill("1");
		verifyNoMoreInteractions(billService);
	}
	
	@Test
	public void verifyGetBillFindById() throws Exception {
		BillVO bill = createBillVOData();
		when(billService.getBill("1")).thenReturn(bill);

		mockMvc.perform(get("/v1/bills/{bill_id}", 1))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(jsonPath("$.id", is("1")))
			.andExpect(jsonPath("$.name", is("Le Meurice")))
			.andExpect(jsonPath("$.address", is("228 rue de Rivoli, 75001, Paris")))
			.andExpect(jsonPath("$.billItems", hasSize(2)))
			.andExpect(jsonPath("$.billItems[0].name", is("Product 5")))
			.andExpect(jsonPath("$.billItems[0].totalPriceWithTax", is(720)))
			.andExpect(jsonPath("$.billItems[1].name", is("Product 8")))
			.andExpect(jsonPath("$.billItems[1].totalPriceWithTax", is(1440)))
			.andExpect(jsonPath("$.grandTotal", is(2160)));
		verify(billService, times(1)).getBill("1");
		verifyNoMoreInteractions(billService);
	}

	@Test
	public void verifyGenerateBillOfGivenUserFailWith404() throws Exception {
		Object[] args = {"99"};
		when(billService.generateBill("99")).thenThrow(new CustomerNotFoundException("customerNotFound", args));

		mockMvc.perform(post("/v1/bills/customer/{customer_id}", 99)
			.locale(Locale.US)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound())
			.andExpect(jsonPath("$.url", is("http://localhost/v1/bills/customer/99")))
			.andExpect(jsonPath("$.message", is("Customer 99 not found.")));
		verify(billService, times(1)).generateBill("99");
		verifyNoMoreInteractions(billService);
	}

	@Test
	public void verifyGenerateBillOfGivenUserFailWith406() throws Exception {
		Object[] args = {"1"};
		when(billService.generateBill("1")).thenThrow(new EmptyCartException("emptyCart", args));

		mockMvc.perform(post("/v1/bills/customer/{customer_id}", 1)
			.locale(Locale.US)				
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable())
			.andExpect(jsonPath("$.url", is("http://localhost/v1/bills/customer/1")))
			.andExpect(jsonPath("$.message", is("Customer 1 has an empty cart")));
		verify(billService, times(1)).generateBill("1");
		verifyNoMoreInteractions(billService);
	}	
	
	@Test
	public void verifyGenerateBillOfGivenUser() throws Exception {
		BillVO bill = createBillVOData();
		when(billService.generateBill("1")).thenReturn(bill);

		mockMvc.perform(post("/v1/bills/customer/{customer_id}", 1)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(jsonPath("$.id", is("1")))
			.andExpect(jsonPath("$.name", is("Le Meurice")))
			.andExpect(jsonPath("$.address", is("228 rue de Rivoli, 75001, Paris")))
			.andExpect(jsonPath("$.billItems", hasSize(2)))
			.andExpect(jsonPath("$.billItems[0].name", is("Product 5")))
			.andExpect(jsonPath("$.billItems[0].totalPriceWithTax", is(720)))
			.andExpect(jsonPath("$.billItems[1].name", is("Product 8")))
			.andExpect(jsonPath("$.billItems[1].totalPriceWithTax", is(1440)))
			.andExpect(jsonPath("$.grandTotal", is(2160)));
		verify(billService, times(1)).generateBill("1");
		verifyNoMoreInteractions(billService);
	}	
	
	
	public BillVO createBillVOData() {
		BillVO bill = new BillVO();
		bill.setId("1");
		bill.setName("Le Meurice");
		bill.setAddress("228 rue de Rivoli, 75001, Paris");
		bill.setDateTime(Instant.now());
		bill.setTotalPrice(BigDecimal.valueOf(1800));
		bill.setTotalTax(BigDecimal.valueOf(360));
		bill.setGrandTotal(BigDecimal.valueOf(2160));
		List<BillItemVO> billItems = new ArrayList<>();
		BillItemVO billItem = new BillItemVO();
		billItem.setCode("123456789005");
		billItem.setName("Product 5");
		billItem.setCategory(Category.B);
		billItem.setPrice(BigDecimal.valueOf(300));
		billItem.setQuantity(Long.valueOf(2L));
		billItem.setTaxPercentage(20f);
		billItem.setTaxAmount(BigDecimal.valueOf(120));
		billItem.setTotalPrice(BigDecimal.valueOf(600));
		billItem.setTotalPriceWithTax(BigDecimal.valueOf(720));
		billItems.add(billItem);

		billItem = new BillItemVO();
		billItem.setCode("123456789008");
		billItem.setName("Product 8");
		billItem.setCategory(Category.B);
		billItem.setPrice(BigDecimal.valueOf(600));
		billItem.setQuantity(Long.valueOf(2L));
		billItem.setTaxPercentage(20f);
		billItem.setTaxAmount(BigDecimal.valueOf(240));
		billItem.setTotalPrice(BigDecimal.valueOf(1200));
		billItem.setTotalPriceWithTax(BigDecimal.valueOf(1440));
		billItems.add(billItem);
		bill.setBillItems(billItems);
		return bill;
	}
}
