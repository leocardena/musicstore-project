package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionImpl;
import connection.GenericConnection;
import model.Estoque;
import model.ItemVenda;
import model.Produto;

public class ItemVendaDAOImpl implements ItemVendaDAO {

	private GenericConnection gc;
	private String sql;
	
	public ItemVendaDAOImpl() {
		gc = new ConnectionImpl();
	}
	
	@Override
	public void incluir(List<ItemVenda> listaItemVenda) throws ItemVendaDAOException {
		
		try {
			Connection con = gc.getConnection();
			sql = "insert into item_venda (cod_venda, cod_prod, quantidade)"
					+ "values (?, ?, ?)";
		
			PreparedStatement ps = con.prepareStatement( sql );
			
			for (ItemVenda itemVenda : listaItemVenda) {
				ps.setInt(1, itemVenda.getVenda().getId() );
				ps.setInt(2, itemVenda.getProduto().getId() );
				ps.setInt(3, itemVenda.getQuantidade() );
			}
			
			ps.execute();
			ps.close();
			con.close();
						
		} catch (Exception e) {
			throw new ItemVendaDAOException( e );
		}
		
	}

	@Override
	public List<ItemVenda> gerarRelatorioMensal(String mes) throws ItemVendaDAOException {
		
		List<ItemVenda> lista = new ArrayList<ItemVenda>();
		
		try {
			Connection con = gc.getConnection();
			sql = "select produto.codigo ,produto.nome, SUM(item_venda.quantidade) as quantidade_vendida "
					+ "from venda "
					+ "inner join item_venda "
					+ "on venda.codigo = item_venda.cod_venda "
					+ "inner join produto "
					+ "on item_venda.cod_produto = produto.codigo "
					+ "where month(venda.data_venda) = ? "
					+ "group by produto.nome, produto.codigo, venda.valor, venda.data_venda "
					+ "order by produto.nome asc";
			
			PreparedStatement ps = con.prepareStatement( sql );
			ps.setString(1, mes);
			
			ResultSet rs = ps.executeQuery();
			while( rs.next() ){
				ItemVenda itemVenda = new ItemVenda();
				Produto produto = new Produto();
				
				produto.setId( rs.getInt("codigo"));
				produto.setNome( rs.getString("nome"));

				itemVenda.setQuantidade( rs.getInt("quantidade_vendida"));
				itemVenda.setProduto(produto);
				
				lista.add(itemVenda);
			}
			
			rs.close();
			ps.close();
			con.close();
			
		} catch (Exception e) {
			throw new ItemVendaDAOException( e );
		}
		return lista;
	}

	@Override
	public List<ItemVenda> gerarRelatorioGeral() throws ItemVendaDAOException {

		List<ItemVenda> lista = new ArrayList<ItemVenda>();
		
		try {
			Connection con = gc.getConnection();
			sql = "select produto.codigo ,produto.nome , SUM(item_venda.quantidade) as quantidade_vendida "
					+ "from venda "
					+ "inner join item_venda "
					+ "on venda.codigo = item_venda.cod_venda "
					+ "inner join produto "
					+ "on item_venda.cod_produto = produto.codigo "
					+ "group by produto.nome, produto.codigo "
					+ "order by produto.nome asc";
			
			PreparedStatement ps = con.prepareStatement( sql );
			
			ResultSet rs = ps.executeQuery();
			while( rs.next() ){
				ItemVenda itemVenda = new ItemVenda();
				Produto produto = new Produto();
				
				produto.setId( rs.getInt("codigo"));
				produto.setNome( rs.getString("nome"));
				
				itemVenda.setQuantidade( rs.getInt("quantidade_vendida"));
				itemVenda.setProduto(produto);
				
				lista.add(itemVenda);
			}
			
			rs.close();
			ps.close();
			con.close();
			
		} catch (Exception e) {
			throw new ItemVendaDAOException( e );
		}
		return lista;
	}

	@Override
	public List<ItemVenda> gerarRelatorioDeLucro() throws ItemVendaDAOException {

		List<ItemVenda> lista = new ArrayList<ItemVenda>();
		
		try {
			Connection con = gc.getConnection();
			sql = "select produto.nome as nome_produto, "
					+ "estoque.valor_custo as valor_comprado, "
					+ "estoque.valor_venda as valor_para_venda, "
					+ "SUM(item_venda.quantidade) as quantidade_vendida, "
					+ "SUM((item_venda.quantidade * estoque.valor_venda)-(estoque.valor_custo * item_venda.quantidade)) as lucro "
						+ "from item_venda item_venda "
						+ "inner join produto produto "
						+ "on item_venda.cod_produto = produto.codigo "
						+ "inner join estoque estoque "
						+ "on produto.codigo_estoque = estoque.codigo "
						+ "group by produto.nome, estoque.valor_custo, estoque.valor_venda "
						+ "order by produto.nome asc";
			
			PreparedStatement ps = con.prepareStatement( sql );
			
			ResultSet rs = ps.executeQuery();
			while( rs.next() ){
				ItemVenda itemVenda = new ItemVenda();
				Produto produto = new Produto();
				Estoque estoque = new Estoque();
				
				produto.setNome( rs.getString("nome_produto"));

				estoque.setValorCusto( rs.getFloat("valor_comprado"));
				estoque.setValorVenda( rs.getFloat("valor_para_venda"));
				
				produto.setEstoque(estoque);
				Float aux = rs.getFloat("lucro");
				estoque.setQuantidade( aux.intValue() );
				
				itemVenda.setQuantidade( rs.getInt("quantidade_vendida"));
				itemVenda.setProduto(produto);
				
				lista.add(itemVenda);
			}
			
			rs.close();
			ps.close();
			con.close();
			
		} catch (Exception e) {
			throw new ItemVendaDAOException( e );
		}
		return lista;
	}

}
