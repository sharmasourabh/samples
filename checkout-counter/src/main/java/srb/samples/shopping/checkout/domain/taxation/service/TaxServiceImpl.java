package srb.samples.shopping.checkout.domain.taxation.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import srb.samples.shopping.checkout.domain.product.Category;
import srb.samples.shopping.checkout.domain.taxation.entity.Entity;
import srb.samples.shopping.checkout.domain.taxation.entity.Tax;
import srb.samples.shopping.checkout.domain.taxation.repository.TaxRepository;

/**
 *
 * @author Sourabh Sharma
 */
@Service("taxService")
public class TaxServiceImpl extends BaseService<Tax, String>
        implements TaxService {

    private TaxRepository<Tax, String> taxRepository;

    /**
     *
     * @param taxRepository
     */
    @Autowired
    public TaxServiceImpl(TaxRepository<Tax, String> taxRepository) {
        super(taxRepository);
        this.taxRepository = taxRepository;
    }

    @Override
    public void add(Tax tax) throws Exception {
        if (taxRepository.containsCategory(tax.getCategory())) {
            throw new Exception(String.format("There is already a tax with the name - %s", tax.getCategory()));
        }

        if (tax.getCategory() == null) {
            throw new Exception("Tax Category cannot be null.");
        }
        super.add(tax);
    }

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Collection<Tax> findByName(String name) throws Exception {
        return taxRepository.findByName(name);
    }

    /**
     *
     * @param tax
     * @throws Exception
     */
    @Override
    public void update(Tax tax) throws Exception {
        taxRepository.update(tax);
    }

    /**
     *
     * @param id
     * @throws Exception
     */
    @Override
    public void delete(String id) throws Exception {
        taxRepository.remove(id);
    }

    /**
     *
     * @param taxId
     * @return
     * @throws Exception
     */
    @Override
    public Entity findById(String taxId) throws Exception {
        return taxRepository.get(taxId);
    }

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Collection<Tax> findByCriteria(Map<String, ArrayList<String>> name) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public Entity<?> findByCategory(Category category) {
		return taxRepository.findByCategory(category);
	}

	@Override
	public float getTaxRate(Category category) {
		return ( (Tax) findByCategory(category)).getTaxPercentage();
	}
}
