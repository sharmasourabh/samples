package srb.samples.shopping.checkout.domain.billing.vo;
import java.math.BigDecimal;
import java.math.BigInteger;

import srb.samples.shopping.checkout.domain.product.Category;

/**
 *
 * @author Sourabh Sharma
 */
public class BillItemVO {

		private String code; // barcode
		private String name;
		private Category category;
		private BigDecimal price;
		private Long quantity;
		private float taxPercentage;
		private BigDecimal taxAmount;
		private BigDecimal totalPrice;
		private BigDecimal totalPriceWithTax;
		
		public BillItemVO() {
			
		}

		public BillItemVO(String code, BigDecimal price, float taxPercentage) {
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


		/**
		 * Overridden toString() method that return String presentation of the Object
		 *
		 * @return
		 */
		@Override
		public String toString() {
			return new StringBuilder("{code: ").append(code).append(", name: ").append(name)
					.append(", price: ").append(price).append(", taxPercentage: ").append(taxPercentage)
					.append(", totalPrice: ").append(totalPrice).append(", taxAmount: ").append(taxAmount)
					.append(", category: ").append(category).append(", quantity: ").append(quantity)
					.append(", totalPriceWithTax: ").append(totalPriceWithTax).append("}").toString();
		}

		/**
		 * @return the totalPrice
		 */
		public BigDecimal getTotalPrice() {
			return totalPrice;
		}

		/**
		 * @param totalPrice the totalPrice to set
		 */
		public void setTotalPrice(BigDecimal totalPrice) {
			this.totalPrice = totalPrice;
		}

		/**
		 * @return the taxAmount
		 */
		public BigDecimal getTaxAmount() {
			return taxAmount;
		}

		/**
		 * @param taxAmount the taxAmount to set
		 */
		public void setTaxAmount(BigDecimal taxAmount) {
			this.taxAmount = taxAmount;
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
		 * @return the totalPriceWithTax
		 */
		public BigDecimal getTotalPriceWithTax() {
			return totalPriceWithTax;
		}

		/**
		 * @param totalPriceWithTax the totalPriceWithTax to set
		 */
		public void setTotalPriceWithTax(BigDecimal totalPriceWithTax) {
			this.totalPriceWithTax = totalPriceWithTax;
		}

	}
