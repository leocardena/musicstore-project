package persistence;

import java.util.List;

import model.Produto;

public interface ProdutoDAO {

	public void inserir(Produto produto) throws ProdutoDAOException;
	public void atualizar(Produto produto) throws ProdutoDAOException;
	public void remover(int id) throws ProdutoDAOException;
	public List<Produto> pesquisarPorNome(String nome) throws ProdutoDAOException;
	public List<Produto> pesquisarPorCategoria(String nome) throws ProdutoDAOException;
	public Produto pesquisarPorId( int id ) throws ProdutoDAOException;
	public int pesquisarUltimoId() throws ProdutoDAOException;
	
}
