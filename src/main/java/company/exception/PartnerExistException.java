package company.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class PartnerExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8617320153958687767L;

}
