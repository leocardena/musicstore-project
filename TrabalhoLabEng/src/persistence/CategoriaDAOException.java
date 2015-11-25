package persistence;

public class CategoriaDAOException extends Exception {


	private static final long serialVersionUID = 1L;

	public CategoriaDAOException() {
	}

	public CategoriaDAOException(String arg0) {
		super(arg0);
	}

	public CategoriaDAOException(Throwable cause) {
		super(cause);
	}

	public CategoriaDAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public CategoriaDAOException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
