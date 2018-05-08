package srb.samples.shopping.checkout.domain.taxation.entity;

import java.math.BigInteger;

import srb.samples.shopping.checkout.domain.product.Category;

/**
 *
 * @author Sourabh Sharma
 */
public class Tax extends BaseEntity<BigInteger> {

	private Category category;
	private float taxPercentage;
	private static BigInteger sequence = BigInteger.valueOf(0L);

	public Tax() {
		super(nextSequence(), "Undefined");
	}

	public Tax(String name, Category category, float taxPercentage) {
		super(nextSequence(), name);
		this.setCategory(category);
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
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tax [category=" + category + ", taxPercentage=" + taxPercentage + ", id=" + id + ", name=" + name + "]";
	}

}
