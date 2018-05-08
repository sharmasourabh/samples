package srb.samples.shopping.checkout.domain.billing.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import srb.samples.shopping.checkout.domain.billing.entity.Entity;
import srb.samples.shopping.checkout.domain.billing.vo.BillVO;
import srb.samples.shopping.checkout.domain.customer.entity.Customer;
import srb.samples.shopping.checkout.domain.billing.entity.Bill;

/**
 *
 * @author Sourabh Sharma
 */
public interface BillService {

    /**
     *
     * @param bill
     * @throws Exception
     */
    public void add(Bill bill) throws Exception;

   /**
    *
    * @param customer
    * @throws Exception
    */
   public BillVO generateBill(String customerId) throws Exception;    
    
    /**
     *
     * @param bill
     * @throws Exception
     */
    public void update(Bill bill) throws Exception;

    /**
     *
     * @param id
     * @throws Exception
     */
    public void delete(String id) throws Exception;

    /**
     *
     * @param billId
     * @return
     * @throws Exception
     */
    public Entity<?> findById(String billId) throws Exception;

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    public Collection<Bill> findByCriteria(Map<String, ArrayList<String>> name) throws Exception;

    /**
     * 
     * @param billId
     * @return
     * @throws Exception
     */
	public BillVO getBill(String billId) throws Exception;
}
