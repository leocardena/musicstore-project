package persistence;

public class ProdutoDAOException extends Exception {

	private static final long serialVersionUID = 1L;

	public ProdutoDAOException() {
	}

	public ProdutoDAOException(String message) {
		super(message);
	}

	public ProdutoDAOException(Throwable cause) {
		super(cause);
	}

	public ProdutoDAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProdutoDAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
