package srb.samples.shopping.checkout.domain.billing.entity;

import java.math.BigInteger;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sourabh Sharma
 */
public class Bill extends BaseEntity<String> {

	private List<BillItem> billItems = new ArrayList<>();
	private static BigInteger sequence = BigInteger.valueOf(0L);
	private String customerId;
	private String address;
	private Instant dateTime;

	public Bill() {
		super(nextSequenceString());
	}

	/**
	 *
	 * @param name
	 * @param id
	 * @param address
	 * @param billItems
	 */
	public Bill(String customerId, String address, List<BillItem> billItems) {
		super(nextSequenceString());
		this.address = address;
		this.customerId = customerId;
		this.billItems = billItems;
	}

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
	 * @param billItems
	 */
	public void setBillItems(List<BillItem> billItems) {
		this.billItems = billItems;
	}

	public static String getCurrentSequence() {
		return sequence.toString();
	}

	public static String nextSequenceString() {
		return nextSequence().toString();
	}
	
	public static void resetSequence() {
		sequence = BigInteger.valueOf(0L);
	}	

	public static BigInteger nextSequence() {
		sequence = sequence.add(BigInteger.valueOf(1L));
		return sequence;
	}

	/**
	 *
	 * @return
	 */
	public List<BillItem> getBillItems() {
		return billItems;
	}

	/**
	 * Overridden toString() method that return String presentation of the Object
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return String.format("{id: %s, address: %s, dateTime: %s, billItems: %s}", this.getId(), this.getAddress(), this.getDateTime(),
				this.getBillItems());
	}

	/**
	 * @return the dateTime
	 */
	public Instant getDateTime() {
		return dateTime;
	}

	/**
	 * @param dateTime the dateTime to set
	 */
	public void setDateTime(Instant dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
