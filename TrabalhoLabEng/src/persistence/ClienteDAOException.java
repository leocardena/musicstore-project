package persistence;

public class ClienteDAOException extends Exception {


	private static final long serialVersionUID = 1L;

	public ClienteDAOException() {
	}

	public ClienteDAOException(String message) {
		super(message);
	}

	public ClienteDAOException(Throwable cause) {
		super(cause);
	}

	public ClienteDAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClienteDAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
