package fr.fchantrel.cryptobot.exceptions;

/**
 * @author fchantrel
 *
 */
public class CryptobotRuntimeException extends RuntimeException {


	private static final long serialVersionUID = 475103085517799579L;


	public CryptobotRuntimeException() {
	}


	public CryptobotRuntimeException(String message) {
		super(message);
	}


	public CryptobotRuntimeException(Throwable cause) {
		super(cause);
	}


	public CryptobotRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}


	public CryptobotRuntimeException(String message, Throwable cause,
									 boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
