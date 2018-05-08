package srb.samples.shopping.checkout.domain.product.repository;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import srb.samples.shopping.checkout.domain.product.entity.Entity;

/**
 *
 * @author Sourabh Sharma
 * @param <Product>
 * @param <String>
 */
public interface ProductRepository<Product, String> extends Repository<Product, String> {

    /**
     *
     * @param name
     * @return
     */
    boolean containsName(String name);

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    public Collection<Product> findByName(String name) throws Exception;
    
    /**
     * 
     * @param barcode
     * @return
     * @throws Exception
     */
    public Entity<?> findByBarcode(String barcode) throws Exception;

    /**
     * 
     * @param barcodes
     * @return
     * @throws Exception
     */
	public Map<String, Product> findByBarcodes(Set<String> barcodes) throws Exception;

	public boolean validateBarcode(String barcode);
}
