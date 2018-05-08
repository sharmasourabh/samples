package srb.samples.shopping.checkout.domain.billing.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import srb.samples.shopping.checkout.domain.billing.entity.Bill;

/**
 *
 * @author Sourabh Sharma
 */
@Repository("billRepository")
public class InMemBillRepository implements BillRepository<Bill, String> {

    private Map<String, Bill> entities;

    /**
     * Initialize the in-memory Bill Repository with empty Map
     */
    public InMemBillRepository() {
        entities = new HashMap<String, Bill>();
    }


    /**
     *
     * @param entity
     */
    @Override
    public void add(Bill entity) {
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
    public void update(Bill entity) {
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
    public Bill get(String id) {
        return entities.get(id);
    }

    /**
     *
     * @return
     */
    @Override
    public Collection<Bill> getAll() {
        return entities.values();
    }

}
