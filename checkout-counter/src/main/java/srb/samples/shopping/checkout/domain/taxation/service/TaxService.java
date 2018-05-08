package srb.samples.shopping.checkout.domain.taxation.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import srb.samples.shopping.checkout.domain.product.Category;
import srb.samples.shopping.checkout.domain.taxation.entity.Entity;
import srb.samples.shopping.checkout.domain.taxation.entity.Tax;

/**
 *
 * @author Sourabh Sharma
 */
public interface TaxService {

    /**
     *
     * @param tax
     * @throws Exception
     */
    public void add(Tax tax) throws Exception;

    /**
     *
     * @param tax
     * @throws Exception
     */
    public void update(Tax tax) throws Exception;

    /**
     *
     * @param id
     * @throws Exception
     */
    public void delete(String id) throws Exception;

    /**
     *
     * @param taxId
     * @return
     * @throws Exception
     */
    public Entity<?> findById(String taxId) throws Exception;

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    public Collection<Tax> findByName(String name) throws Exception;

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    public Collection<Tax> findByCriteria(Map<String, ArrayList<String>> name) throws Exception;

	public Entity<?> findByCategory(Category category);

	public float getTaxRate(Category category);
}
