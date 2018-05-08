package srb.samples.shopping.checkout.domain.billing.vo;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;



/**
 *
 * @author Sourabh Sharma
 */
public class BillVO {
	
    private String name;
    private String id;
    private String address;
    private Instant dateTime;
    private List<BillItemVO> billItems = new ArrayList<>();
    private BigDecimal totalTax;
    private BigDecimal totalPrice;
    private BigDecimal grandTotal;

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
    public BillVO() {
    }


    /**
     * Overridden toString() method that return String presentation of the
     * Object
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("{id: %s, dateTime: %s, name: %s, address: %s, billItems: %s}", this.getId(), this.getDateTime(),
                this.getName(), this.getAddress(), this.getBillItems());
    }

	/**
	 * @return the dateTime
	 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ",timezone = "UTC")    
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
	 * @return the billItems
	 */
	public List<BillItemVO> getBillItems() {
		return billItems;
	}

	/**
	 * @param billItems the billItems to set
	 */
	public void setBillItems(List<BillItemVO> billItems) {
		this.billItems = billItems;
	}

	/**
	 * @return the totalTax
	 */
	public BigDecimal getTotalTax() {
		return totalTax;
	}

	/**
	 * @param totalTax the totalTax to set
	 */
	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
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
	 * @return the grandTotal
	 */
	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	/**
	 * @param grandTotal the grandTotal to set
	 */
	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}
}
