package srb.samples.shopping.checkout.exception;

public class CartItemsNotValidException extends Exception {

	private static final long serialVersionUID = 216588015018664305L;

	private String message;
	private Object[] args;

	public CartItemsNotValidException(String message){
		this.message = String.format("Customer %s has invalid items in cart.", message);
	}
	
	public CartItemsNotValidException(Object[] args) {
		this.args = args;
	}
	
	public CartItemsNotValidException(String message, Object[] args){
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
	 * @param message the message to set
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
	 * @param args the args to set
	 */
	public void setArgs(Object[] args) {
		this.args = args;
	}
}
