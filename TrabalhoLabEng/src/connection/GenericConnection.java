package connection;

import java.sql.Connection;

/**
 * Interface que possui os m�todos de conex�o.
 * @author Pedro Afonso
 *
 */
public interface GenericConnection {

	/**
	 * M�todo que abre a conex�o com o banco
	 * @return conexao com o banco
	 */
	public Connection getConnection();
	
	/**
	 * M�todo que fecha a conex�o com o banco
	 * @param conexao do banco
	 */
	public void fechaConexao(Connection con);
	
}