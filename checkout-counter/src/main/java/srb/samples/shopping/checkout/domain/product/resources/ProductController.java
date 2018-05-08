package srb.samples.shopping.checkout.domain.product.resources;


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

import srb.samples.shopping.checkout.domain.product.entity.Entity;
import srb.samples.shopping.checkout.domain.product.entity.Product;
import srb.samples.shopping.checkout.domain.product.service.ProductService;
import srb.samples.shopping.checkout.domain.product.vo.ProductVO;

/**
 *
 * @author Sourabh Sharma
 */
@RestController
@RequestMapping("/v1/products")
public class ProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    /**
     *
     */
    protected ProductService productService;

    /**
     *
     * @param productService
     */
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Fetch products with the specified name. A partial case-insensitive
     * match is supported. So <code>http://.../products/rest</code> will find
     * any products with upper or lower case 'rest' in their name.
     *
     * @param name
     * @return A non-null, non-empty collection of products.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Product>> findByName(@RequestParam("name") String name) {
        LOGGER.info("product-service findByName() invoked:{} for {} ", productService.getClass().getName(), name);
        name = name.trim().toLowerCase();
        Collection<Product> products;
        try {
            products = productService.findByName(name);
        } catch (Exception ex) {
            LOGGER.error("Exception raised findByName REST Call", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return products.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Fetch products with the given id.
     * <code>http://.../v1/products/{product_id}</code> will return
     * product with given id.
     *
     * @param id
     * @return A non-null, non-empty collection of products.
     */
    @RequestMapping(value = "/{product_id}", method = RequestMethod.GET)
    public ResponseEntity<Entity<?>> findById(@PathVariable("product_id") String id) {
        LOGGER.info("product-service findById() invoked:{} for {} ", productService.getClass().getName(), id);
        id = id.trim();
        Entity<?> product;
        try {
            product = productService.findById(id);
        } catch (Exception ex) {
            LOGGER.error("Exception raised findById REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return product != null ? new ResponseEntity<>(product, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Fetch products with the given barcode.
     * <code>http://.../v1/products/barcode/{product_id}</code> will return
     * product with given id.
     *
     * @param barcode
     * @return A non-null, non-empty collection of products.
     */
    @RequestMapping(value = "/barcode/{barcode}", method = RequestMethod.GET)
    public ResponseEntity<Entity<?>> findByBarcode(@PathVariable("barcode") String barcode) {
        LOGGER.info("product-service findByBarcode() invoked:{} for {} ", productService.getClass().getName(), barcode);
        barcode = barcode.trim();
        Entity<?> product;
        try {
            product = productService.findByBarcode(barcode);
        } catch (Exception ex) {
            LOGGER.error("Exception raised findByBarcode REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return product != null ? new ResponseEntity<>(product, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }    
    
    
    /**
     * Add product with the specified information.
     *
     * @param productVO
     * @return A non-null product.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody ProductVO productVO) {
        LOGGER.info("product-service add() invoked: %s for %s", productService.getClass().getName(), productVO.getName());
        System.out.println(productVO);
        Product product = new Product();
        BeanUtils.copyProperties(productVO, product);
        try {
            productService.add(product);
        } catch (Exception ex) {
            LOGGER.error("Exception raised add Bill REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
