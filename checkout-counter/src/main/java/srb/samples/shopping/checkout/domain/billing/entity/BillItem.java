package srb.samples.shopping.checkout.domain.billing.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

import srb.samples.shopping.checkout.domain.product.Category;

/**
 *
 * @author Sourabh Sharma
 */
public class BillItem extends BaseEntity<BigInteger> {

	private String code; // barcode
	private String name;
	private Category category;
	private static BigInteger sequence = BigInteger.valueOf(0L);
	private BigDecimal price;
	private Long quantity;
	private float taxPercentage;

	public BillItem() {
		super(nextSequence());
	}

	public BillItem(String code, BigDecimal price, float taxPercentage) {
		super(nextSequence());
		this.code = code;
		this.price = price;
		this.taxPercentage = taxPercentage;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the taxPercentage
	 */
	public float getTaxPercentage() {
		return taxPercentage;
	}

	/**
	 * @param taxPercentage
	 *            the taxPercentage to set
	 */
	public void setTaxPercentage(float taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public static String getCurrentSequence() {
		return sequence.toString();
	}

	public static String nextSequenceString() {
		return nextSequence().toString();
	}

	public static BigInteger nextSequence() {
		sequence = sequence.add(BigInteger.valueOf(1L));
		return sequence;
	}
	
	/**
	 * Overridden toString() method that return String presentation of the Object
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return new StringBuilder("{id: ").append(id).append(", code: ").append(code).append(", price: ").append(price)
				.append(", taxPercentage: ").append(taxPercentage).append(", quantity: ").append(quantity)
				.append(", name: ").append(name).append(", category: ").append(category).append("}").toString();
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
	 * @return the quantity
	 */
	public Long getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
