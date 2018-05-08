package srb.samples.shopping.checkout.domain.customer.repository;

import srb.samples.shopping.checkout.domain.customer.entity.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sourabh Sharma
 */
@Repository("customerRepository")
public class InMemCustomerRepository implements CustomerRepository<Customer, String> {

    private Map<String, Customer> entities;

    /**
     * Initialize the in-memory Bill Repository with empty Map
     */
    public InMemCustomerRepository() {
        entities = new HashMap<String, Customer>();
        Customer customer = new Customer("Le Meurice", "1", "228 rue de Rivoli, 75001, Paris", null);
        entities.put("1", customer);
        customer = new Customer("L'Ambroisie", "2", "9 place des Vosges, 75004, Paris", null);
        entities.put("2", customer);
        customer = new Customer("Arpège", "3", "84, rue de Varenne, 75007, Paris", null);
        entities.put("3", customer);
        customer = new Customer("Alain Ducasse au Plaza Athénée", "4", "25 avenue de Montaigne, 75008, Paris", null);
        entities.put("4", customer);
        customer = new Customer("Pavillon LeDoyen", "5", "1, avenue Dutuit, 75008, Paris", null);
        entities.put("5", customer);
        customer = new Customer("Pierre Gagnaire", "6", "6, rue Balzac, 75008, Paris", null);
        entities.put("6", customer);
        customer = new Customer("L'Astrance", "7", "4, rue Beethoven, 75016, Paris", null);
        entities.put("7", customer);
        customer = new Customer("Pré Catelan", "8", "Bois de Boulogne, 75016, Paris", null);
        entities.put("8", customer);
        customer = new Customer("Guy Savoy", "9", "18 rue Troyon, 75017, Paris", null);
        entities.put("9", customer);
        customer = new Customer("Le Bristol", "10", "112, rue du Faubourg St Honoré, 8th arrondissement, Paris", null);
        entities.put("10", customer);
    }

    /**
     * Check if given product name already exist.
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
    public void add(Customer entity) {
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
    public void update(Customer entity) {
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
    public Customer get(String id) {
        return entities.get(id);
    }

    /**
     *
     * @return
     */
    @Override
    public Collection<Customer> getAll() {
        return entities.values();
    }

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Collection<Customer> findByName(String name) throws Exception {
        Collection<Customer> customers = new ArrayList<Customer>();
        int noOfChars = name.length();
        entities.forEach((k, v) -> {
            if (v.getName().toLowerCase().contains(name.subSequence(0, noOfChars))) {
                customers.add(v);
            }
        });
        return customers;
    }

}
