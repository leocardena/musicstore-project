package persistence;

import java.util.List;

import model.ItemVenda;

public interface ItemVendaDAO {

	public void incluir(List<ItemVenda> listaItemVenda) throws ItemVendaDAOException;
	public List<ItemVenda> gerarRelatorioMensal(String mes) throws ItemVendaDAOException;
	public List<ItemVenda> gerarRelatorioGeral() throws ItemVendaDAOException;
	public List<ItemVenda> gerarRelatorioDeLucro() throws ItemVendaDAOException;
 
}
