package srb.samples.shopping.checkout.domain.product.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import srb.samples.shopping.checkout.domain.product.entity.Entity;
import srb.samples.shopping.checkout.domain.product.entity.Product;

/**
 *
 * @author Sourabh Sharma
 */
public interface ProductService {

    /**
     *
     * @param product
     * @throws Exception
     */
    public void add(Product product) throws Exception;

    /**
     *
     * @param product
     * @throws Exception
     */
    public void update(Product product) throws Exception;

    /**
     *
     * @param id
     * @throws Exception
     */
    public void delete(String id) throws Exception;

    /**
     *
     * @param productId
     * @return
     * @throws Exception
     */
    public Entity<?> findById(String productId) throws Exception;

   /**
    *
    * @param barcode
    * @return
    * @throws Exception
    */
   public Entity<?> findByBarcode(String barcode) throws Exception;    
    
    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    public Collection<Product> findByName(String name) throws Exception;

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    public Collection<Product> findByCriteria(Map<String, ArrayList<String>> name) throws Exception;

    /**
     * 
     * @param barcodes
     * @return
     * @throws Exception
     */
	public Map<String, Product> getProducts(Set<String> barcodes) throws Exception;

	public boolean validateBarcode(String barcode);
}
