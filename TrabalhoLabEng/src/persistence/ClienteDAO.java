package persistence;

import java.util.List;

import model.Cliente;

public interface ClienteDAO {

	public void incluir( Cliente cliente ) throws ClienteDAOException;
	public void atualizar( Cliente cliente ) throws ClienteDAOException;
	public List<Cliente> pesquisarPorNome( String nome ) throws ClienteDAOException;
	public boolean pesquisarUsuarioSenha(String usuario, String senha) throws ClienteDAOException;
	public int pesquisarUltimoId() throws ClienteDAOException;
	List<Cliente> pesquisarPorUsuario(String usuario);
	
	
}
