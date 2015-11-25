package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.ConnectionImpl;
import connection.GenericConnection;
import model.Estoque;

public class EstoqueDAOImpl implements EstoqueDAO {
	
	private GenericConnection gc;
	private String sql;

	public EstoqueDAOImpl() {
		gc = new ConnectionImpl();
	}
	
	@Override
	public void incluir(Estoque estoque) throws EstoqueDAOException {

		try {
			Connection con = gc.getConnection();
			sql = "insert into estoque (codigo, valor_custo, valor_venda, quantidade)"
					+ "values (?, ?, ?, ?)";
			
			PreparedStatement ps = con.prepareStatement( sql );
			ps.setInt(1, this.pesquisarUltimoId()+1 );
			ps.setFloat(2, estoque.getValorCusto() );
			ps.setFloat(3, estoque.getValorVenda() );
			ps.setInt(4, estoque.getQuantidade() );
			
			ps.execute();
			ps.close();
			con.close();		
			
		}catch(Exception e){
			throw new EstoqueDAOException( e );
		}
		
	}

	@Override
	public void atualizar(Estoque estoque) throws EstoqueDAOException {

		try {
			Connection con = gc.getConnection();
			sql = "insert into categoria (codigo, valorCusto, valorVenda, quantidade)"
					+ "values (?, ?, ?, ?)";
			
			PreparedStatement ps = con.prepareStatement( sql );
			ps.setInt(1, estoque.getId() );
			ps.setFloat(2, estoque.getValorCusto() );
			ps.setFloat(2, estoque.getValorVenda() );
			ps.setInt(2, estoque.getQuantidade() );
			
			ps.execute();
			ps.close();
			con.close();		
			
		}catch(Exception e){
			throw new EstoqueDAOException( e );
		}
		
	}

	@Override
	public void remover(Estoque estoque) throws EstoqueDAOException {

		try {
			Connection con = gc.getConnection();
			sql = "delete estoque where codigo = ?";
			PreparedStatement ps = con.prepareStatement( sql );
			ps.setInt(1, estoque.getId() );
			
			ps.executeUpdate();
			ps.close();
			con.close();
		
		}catch(Exception e){
			throw new EstoqueDAOException( e );
		}
	}
	

	@Override
	public boolean verificarEstoque(int id) throws EstoqueDAOException {

		boolean aux = false;
		
		try {
			Connection con = gc.getConnection();
			sql = "select quantidade from estoque where codigo = ?";
		
			PreparedStatement ps = con.prepareStatement( sql );
			ps.setLong(1, id );
			
			ResultSet rs = ps.executeQuery();
			
			if( rs.next() ){
				aux = (rs.getInt("quantidade") > 0) ? true : false;
			}
			
			ps.close();
			con.close();
			
		}catch(Exception e){
			throw new EstoqueDAOException( e );
		}
		
		return aux;
	}

	@Override
	public void atualizarQuantidade(int id) throws EstoqueDAOException {
		
		try {
			//TODO		
		}catch(Exception e){
			throw new EstoqueDAOException( e );
		}
		
	}

	@Override
	public int pesquisarUltimoId() throws EstoqueDAOException {

		int id = 0;
		
		try {
			Connection con = gc.getConnection();
			sql = "select max(codigo) from estoque";
			
			PreparedStatement ps = con.prepareStatement( sql );
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			id = rs.getInt(1);
			
			ps.close();
			rs.close();
			con.close();
			
		} catch( Exception e ) {
			throw new EstoqueDAOException( e );
		}
		
		return id == 0 ? 1 : id;
	}
		
}
