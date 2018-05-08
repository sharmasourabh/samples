package srb.samples.shopping.checkout.domain.customer.entity;

/**
 *
 * @author Sourabh Sharma
 * @param <T>
 */
public abstract class BaseEntity<T> extends Entity<T> {

    private final boolean isModified;

    /**
     *
     * @param id
     * @param name
     */
    public BaseEntity(T id) {
        super.id = id;
        super.name = "default";
        isModified = false;
    }

    /**
     *
     * @return
     */
    public boolean isIsModified() {
        return isModified;
    }

}
