package persistence;

import model.Categoria;

public interface CategoriaDAO {

	public Categoria pesquisarPorNome( String nome ) throws CategoriaDAOException;

	int pesquisarIdPorNome(String nome) throws CategoriaDAOException;

	Categoria pesquisarPorId(int id);
	
}
