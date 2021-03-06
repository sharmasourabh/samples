package srb.samples.shopping.checkout.exception;

public class CustomerNotFoundException extends Exception {

	private static final long serialVersionUID = -3035672774358850631L;

	private String message;
	private Object[] args;

	public CustomerNotFoundException(String message){
		this.message = String.format("Customer %s not found.", message);
	}
	
	public CustomerNotFoundException(Object[] args) {
		this.args = args;
	}
	
	public CustomerNotFoundException(String message, Object[] args){
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
