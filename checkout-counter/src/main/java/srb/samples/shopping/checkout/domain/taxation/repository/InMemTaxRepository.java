package srb.samples.shopping.checkout.domain.taxation.repository;

import srb.samples.shopping.checkout.domain.product.Category;
import srb.samples.shopping.checkout.domain.taxation.entity.Tax;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sourabh Sharma
 */
@Repository("taxRepository")
public class InMemTaxRepository implements TaxRepository<Tax, String> {

    private Map<BigInteger, Tax> entities;

    /**
     * Initialize the in-memory Tax Repository with empty Map
     */
    public InMemTaxRepository() {
        entities = new HashMap<BigInteger, Tax>();
        Tax tax = new Tax("Category A Tax", Category.A, 10);
        entities.put(tax.getId(), tax);
        tax = new Tax("Category B Tax", Category.B, 20);
        entities.put(tax.getId(), tax);
        tax = new Tax("Category C Tax", Category.C, 0);
        entities.put(tax.getId(), tax);
    }

    /**
     * Check if given tax name already exist.
     *
     * @param name
     * @return true if already exist, else false
     */
    @Override
    public boolean containsName(String name) {
        try {
            return !this.findByName(name).isEmpty();
        } catch (Exception ex) {
            //Exception Handler
        }
        return false;
    }

    /**
     *
     * @param entity
     */
    @Override
    public void add(Tax entity) {
        entities.put(entity.getId(), entity);
    }

    /**
     *
     * @param id
     */
    @Override
    public void remove(String id) {
        if (entities.containsKey(id)) {
            entities.remove(id);
        }
    }

    /**
     *
     * @param entity
     */
    @Override
    public void update(Tax entity) {
        if (entities.containsKey(entity.getId())) {
            entities.put(entity.getId(), entity);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public boolean contains(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Tax get(String id) {
        return entities.get(id);
    }

    /**
     *
     * @return
     */
    @Override
    public Collection<Tax> getAll() {
        return entities.values();
    }

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Collection<Tax> findByName(String name) throws Exception {
        Collection<Tax> taxs = new ArrayList<Tax>();
        int noOfChars = name.length();
        entities.forEach((k, v) -> {
            if (v.getName().toLowerCase().contains(name.subSequence(0, noOfChars))) {
                taxs.add(v);
            }
        });
        return taxs;
    }

	@Override
	public boolean containsCategory(Category category) {
        try {
            return this.findByCategory(category) != null;
        } catch (Exception ex) {
            //Exception Handler
        }
        return false;
	}

	@Override
	public Tax findByCategory(Category category) {
        List<Tax> taxs = new ArrayList<Tax>();
        entities.forEach((k, v) -> {
            if (v.getCategory().equals(category)) {
                taxs.add(v);
            }
        });
        return taxs.get(0);
	}

}
