package app.bufa.paysdk.core;

public class PayException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4663474325105471812L;

	public PayException(String pMessage) {
		super(pMessage);
	}
}
