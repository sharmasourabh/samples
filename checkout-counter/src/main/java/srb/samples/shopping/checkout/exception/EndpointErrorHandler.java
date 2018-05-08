package srb.samples.shopping.checkout.exception;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EndpointErrorHandler {
	private static final String UNEXPECTED_ERROR = "Exception.unexpected";
	private final MessageSource messageSource;

	@Autowired
	public EndpointErrorHandler(MessageSource messageSource) {
	   this.messageSource = messageSource;
	}
	
	@ExceptionHandler(EmptyCartException.class)
	public ResponseEntity<ErrorInfo> handleEmptyCartException(HttpServletRequest request, EmptyCartException ex, Locale locale) {
		ErrorInfo response = new ErrorInfo();
		response.setUrl(request.getRequestURL().toString());
		response.setMessage(messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale));
		return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ErrorInfo> handleCustomerNotFoundException(HttpServletRequest request, CustomerNotFoundException ex, Locale locale) {
		ErrorInfo response = new ErrorInfo();
		response.setUrl(request.getRequestURL().toString());
		response.setMessage(messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale));
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidBarcodeException.class)
	public ResponseEntity<ErrorInfo> handleInvalidBarcodeException(HttpServletRequest request, InvalidBarcodeException ex, Locale locale) {
		ErrorInfo response = new ErrorInfo();
		response.setUrl(request.getRequestURL().toString());
		response.setMessage(messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale));
		return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(CartItemsNotValidException.class)
	public ResponseEntity<ErrorInfo> handleCartItemsNotValidException(HttpServletRequest request, CartItemsNotValidException ex, Locale locale) {
		ErrorInfo response = new ErrorInfo();
		response.setUrl(request.getRequestURL().toString());
		response.setMessage(messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}	
/*	@ExceptionHandler(RestException.class)
	public ResponseEntity<ErrorInfo> handleIllegalArgument(RestException ex, Locale locale) {
		String errorMessage = messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale);
		return new ResponseEntity<>(new ErrorInfo(errorMessage), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorInfo> handleArgumentNotValidException(MethodArgumentNotValidException ex,
			Locale locale) {
		BindingResult result = ex.getBindingResult();
		List<String> errorMessages = result.getAllErrors().stream()
				.map(objectError -> messageSource.getMessage(objectError, locale)).collect(Collectors.toList());
		return new ResponseEntity<>(new ErrorInfo(errorMessages), HttpStatus.BAD_REQUEST);
	}*/

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> handleException(Exception ex, Locale locale) {
		String errorMessage = messageSource.getMessage(UNEXPECTED_ERROR, null, locale);
		return new ResponseEntity<>(new ErrorInfo(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
