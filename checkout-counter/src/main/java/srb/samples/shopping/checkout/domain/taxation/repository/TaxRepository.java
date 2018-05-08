package srb.samples.shopping.checkout.domain.taxation.repository;

import java.util.Collection;

import srb.samples.shopping.checkout.domain.product.Category;

/**
 *
 * @author Sourabh Sharma
 * @param <Bill>
 * @param <String>
 */
public interface TaxRepository<Tax, String> extends Repository<Tax, String> {

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
    */
   boolean containsCategory(Category name);    
    
   public Tax findByCategory(Category category);
   
    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    public Collection<Tax> findByName(String name) throws Exception;
}
