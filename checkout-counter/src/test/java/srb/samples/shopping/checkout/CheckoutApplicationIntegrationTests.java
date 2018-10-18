package srb.samples.shopping.checkout;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import srb.samples.shopping.checkout.domain.billing.entity.Bill;
import srb.samples.shopping.checkout.domain.customer.vo.CartItemVO;
import srb.samples.shopping.checkout.domain.customer.vo.CustomerVO;
import srb.samples.shopping.checkout.domain.product.vo.ProductVO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CheckoutApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CheckoutApplicationIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testProductGetById() throws InterruptedException {
        ResponseEntity<ProductVO> response = this.testRestTemplate
                .getForEntity("http://localhost:" + this.port + "v1/products/1", ProductVO.class);
        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(response);
        ProductVO product = response.getBody();
        // Asserting API Response
        String barcode = product.getBarcode();
        assertNotNull(barcode);
        assertEquals("123456789001", barcode);
        String name = product.getName();
        assertNotNull(name);
        assertEquals("Product 1", name);
    }

    @Test
    public void testProductGetByBarcode() throws InterruptedException {
        ResponseEntity<ProductVO> response = this.testRestTemplate
                .getForEntity("http://localhost:" + this.port + "v1/products/barcode/123456789001", ProductVO.class);
        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(response);
        ProductVO product = response.getBody();
        // Asserting API Response
        String id = product.getId();
        assertNotNull(id);
        assertEquals("1", id);
        String name = product.getName();
        assertNotNull(name);
        assertEquals("Product 1", name);
    }

    @Test
    public void testCustomerGetById() throws InterruptedException {
        ResponseEntity<CustomerVO> response = this.testRestTemplate
                .getForEntity("http://localhost:" + this.port + "v1/customers/1/", CustomerVO.class);
        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(response);
        CustomerVO customer = response.getBody();
        // Asserting API Response
        String id = customer.getId();
        assertNotNull(id);
        assertEquals("1", id);
        String name = customer.getName();
        assertNotNull(name);
        assertEquals("Le Meurice", name);
        assertEquals(true, customer.getCartVO().getCartEntry().isEmpty());
    }

    @Test
    public void testCustomerAddItemsInCart() throws InterruptedException {
        CartItemVO cartItemVO = new CartItemVO();
        cartItemVO.getCartEntry().put("123456789001", 1L);
        ResponseEntity<CustomerVO> response = this.testRestTemplate
                .postForEntity("http://localhost:" + this.port + "v1/customers/1/cart", cartItemVO, CustomerVO.class);
        then(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void testBillingGenerateBill() throws InterruptedException {
        CartItemVO cartItemVO = new CartItemVO();
        cartItemVO.getCartEntry().put("123456789001", 1L);
        ResponseEntity<Bill> response = this.testRestTemplate
                .postForEntity("http://localhost:" + this.port + "v1/bills/customer/1", cartItemVO, Bill.class);
        then(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}
