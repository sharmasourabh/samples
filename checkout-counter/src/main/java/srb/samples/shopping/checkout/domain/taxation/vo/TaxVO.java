package srb.samples.shopping.checkout.domain.taxation.vo;


import java.util.ArrayList;
import java.util.List;

import srb.samples.shopping.checkout.domain.product.entity.Details;

/**
 *
 * @author Sourabh Sharma
 */
public class TaxVO {

    private List<Details> details = new ArrayList<>();
    private String name;
    private String id;
    private String address;

    /**
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Constructor
     */
    public TaxVO() {
    }

    /**
     *
     * @param details
     */
    public void setTables(List<Details> details) {
        this.details = details;
    }

    /**
     *
     * @return
     */
    public List<Details> getTables() {
        return details;
    }

    /**
     * Overridden toString() method that return String presentation of the
     * Object
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("{id: %s, name: %s, address: %s, details: %s}", this.getId(),
                this.getName(), this.getAddress(), this.getTables());
    }
}
