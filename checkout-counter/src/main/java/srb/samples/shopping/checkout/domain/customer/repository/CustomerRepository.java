package srb.samples.shopping.checkout.domain.customer.repository;

import java.util.Collection;

/**
 *
 * @author Sourabh Sharma
 * @param <Customer>
 * @param <String>
 */
public interface CustomerRepository<Customer, String> extends Repository<Customer, String> {

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
    public Collection<Customer> findByName(String name) throws Exception;
}
