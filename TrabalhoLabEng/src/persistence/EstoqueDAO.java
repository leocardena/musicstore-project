package persistence;

import model.Estoque;

public interface EstoqueDAO {

	public void incluir( Estoque estoque ) throws EstoqueDAOException;
	public void atualizar( Estoque estoque ) throws EstoqueDAOException;
	public void remover( Estoque estoque ) throws EstoqueDAOException;
	public boolean verificarEstoque( int id ) throws EstoqueDAOException;
	public void atualizarQuantidade( int id ) throws EstoqueDAOException;
	public int pesquisarUltimoId () throws EstoqueDAOException;
}
