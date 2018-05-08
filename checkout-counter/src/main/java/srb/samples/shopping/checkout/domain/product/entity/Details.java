package srb.samples.shopping.checkout.domain.product.entity;

import java.math.BigInteger;

/**
 *
 * @author Sourabh Sharma
 */
public class Details extends BaseEntity<BigInteger> {

    private String description;

    /**
     *
     * @param name
     * @param id
     * @param description
     */
    public Details(String name, BigInteger id, String description) {
        super(id, name);
        this.description = description;
    }

    /**
     *
     * @param description
     */
    public void setCapacity(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public String getCapacity() {
        return description;
    }

    /**
     * Overridden toString() method that return String presentation of the
     * Object
     *
     * @return
     */
    @Override
    public String toString() {
        return new StringBuilder("{id: ").append(id).append(", name: ")
                .append(name).append(", description: ").append(description).append("}").toString();
    }

}
