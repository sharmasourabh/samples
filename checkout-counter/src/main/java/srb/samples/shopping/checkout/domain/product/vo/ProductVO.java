package srb.samples.shopping.checkout.domain.product.vo;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import srb.samples.shopping.checkout.domain.product.Category;
import srb.samples.shopping.checkout.domain.product.entity.Details;

/**
 *
 * @author Sourabh Sharma
 */
public class ProductVO {

    private List<Details> details = new ArrayList<>();
    private String name;
    private String id;
    private String barcode;
    private Category category;
    private BigDecimal price;

    /**
	 * @return the details
	 */
	public List<Details> getDetails() {
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(List<Details> details) {
		this.details = details;
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
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
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
    public ProductVO() {
    }

    /**
     * Overridden toString() method that return String presentation of the
     * Object
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("{id: %s, name: %s, barcode: %s, price: %s, category: %s, details: %s}", this.getId(),
                this.getName(), this.getBarcode(), this.getPrice(), this.getCategory(), this.getDetails());
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
}
