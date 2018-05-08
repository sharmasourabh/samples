package srb.samples.shopping.checkout.domain.product.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import srb.samples.shopping.checkout.domain.product.Category;

/**
 *
 * @author Sourabh Sharma
 */
public class Product extends BaseEntity<String> {

    private List<Details> details = new ArrayList<>();
    private Category category;
    private BigDecimal price;
    
    // If required barcode library could be used - e.g. https://github.com/zxing/zxing etc.
    private String barcode;

    /**
     *
     * @return
     */
    public Category getCategory() {
        return category;
    }

    /**
     *
     * @param category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    public Product() {
    	// in actual implementation id is generated automatically at JPA layer.
    	// Default product name
    	super(null, "Product Name");
    }
    
    /**
     *
     * @param name
     * @param id
     * @param category
     * @param details
     */
    // Builder Pattern should be used
    public Product(String id, String name, String barcode, Category category, BigDecimal price, List<Details> details) {
        super(id, name);
        this.barcode = barcode;
        this.category = category;
        this.details = details;
        this.price = price;
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
	 * @return the barcode
	 */
	public String getBarcode() {
		return barcode;
	}

	/**
	 * @param barcode the barcode to set
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [details=" + details + ", category=" + category + ", price=" + price + ", barcode=" + barcode
				+ ", id=" + id + ", name=" + name + "]";
	}

}