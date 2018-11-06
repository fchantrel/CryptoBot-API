package fr.fchantrel.cryptobot.exceptions;

/**
 * @author fchantrel
 *
 */
public class CryptobotException extends Exception {

	
	private static final long serialVersionUID = -6048480108822824395L;

	
	public CryptobotException() {
	}

	public CryptobotException(String message) {
		super(message);
	}


	public CryptobotException(Throwable cause) {
		super(cause);
	}


	public CryptobotException(String message, Throwable cause) {
		super(message, cause);
	}


	public CryptobotException(String message, Throwable cause,
							  boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
