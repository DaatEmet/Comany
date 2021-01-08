package company.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class FormatFieldException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3957335713150865576L;

}
