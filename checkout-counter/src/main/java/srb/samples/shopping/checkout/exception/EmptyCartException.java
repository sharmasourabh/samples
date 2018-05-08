package srb.samples.shopping.checkout.exception;

public class EmptyCartException extends Exception {

	private static final long serialVersionUID = 8617134037372523257L;

	private String message;
	private Object[] args;

	public EmptyCartException(String message){
		this.message = String.format("Customer %s has an empty cart", message);
	}

	public EmptyCartException(Object[] args) {
		this.args = args;
	}

	public EmptyCartException(String message, Object[] args){
		this.message = message;
		this.args = args;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the args
	 */
	public Object[] getArgs() {
		return args;
	}

	/**
	 * @param args
	 *            the args to set
	 */
	public void setArgs(Object[] args) {
		this.args = args;
	}
}
