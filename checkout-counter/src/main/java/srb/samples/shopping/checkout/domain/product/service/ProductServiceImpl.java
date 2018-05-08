package srb.samples.shopping.checkout.domain.product.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import srb.samples.shopping.checkout.domain.product.entity.Entity;
import srb.samples.shopping.checkout.domain.product.entity.Product;
import srb.samples.shopping.checkout.domain.product.repository.ProductRepository;
import srb.samples.shopping.checkout.exception.InvalidBarcodeException;

/**
 *
 * @author Sourabh Sharma
 */
@Service("productService")
public class ProductServiceImpl extends BaseService<Product, String>
        implements ProductService {

    private ProductRepository<Product, String> productRepository;

    /**
     *
     * @param productRepository
     */
    @Autowired
    public ProductServiceImpl(ProductRepository<Product, String> productRepository) {
        super(productRepository);
        this.productRepository = productRepository;
    }

    @Override
    public void add(Product product) throws Exception {
        if (productRepository.containsName(product.getName())) {
            throw new Exception(String.format("There is already a product with the name - %s", product.getName()));
        }

        if (product.getName() == null || "".equals(product.getName())) {
            throw new Exception("Bill name cannot be null or empty string.");
        }
        super.add(product);
    }

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Collection<Product> findByName(String name) throws Exception {
        return productRepository.findByName(name);
    }

    /**
     *
     * @param product
     * @throws Exception
     */
    @Override
    public void update(Product product) throws Exception {
        productRepository.update(product);
    }

    /**
     *
     * @param id
     * @throws Exception
     */
    @Override
    public void delete(String id) throws Exception {
        productRepository.remove(id);
    }

    /**
     *
     * @param productId
     * @return
     * @throws Exception
     */
    @Override
    public Entity<?> findById(String productId) throws Exception {
        return productRepository.get(productId);
    }

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Collection<Product> findByCriteria(Map<String, ArrayList<String>> name) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public Entity<?> findByBarcode(String barcode) throws Exception {
		Entity<?> product = productRepository.findByBarcode(barcode);
		if (product == null) {
			Object[] args = {barcode};
			throw new InvalidBarcodeException("invalidBarcode", args);
		}
		return product;
	}

	@Override
	public boolean validateBarcode(String barcode) {
		return productRepository.validateBarcode(barcode);
	}	
	
	@Override
	public Map<String, Product> getProducts(Set<String> barcodes) throws Exception {		
		return productRepository.findByBarcodes(barcodes);
	}
}
