package persistence;

import model.Venda;

public interface VendaDAO {

	public void incluir( Venda venda ) throws VendaDAOException;
	public int pesquisarUltimoId() throws VendaDAOException;
}
