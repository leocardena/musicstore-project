package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.ConnectionImpl;
import connection.GenericConnection;
import model.Categoria;

public class CategoriaDAOImpl implements CategoriaDAO {

	private GenericConnection gc;
	private String sql;
	
	public CategoriaDAOImpl() {
		gc = new ConnectionImpl();
	}

	@Override
	public Categoria pesquisarPorNome(String nome) throws CategoriaDAOException {
		
		Categoria categoria = null;
		
		try {
			Connection con = gc.getConnection();
			sql = "select * from categoria where nome = ?";
		
			PreparedStatement ps = con.prepareStatement( sql );
			ps.setString(1, nome);
			
			ResultSet rs = ps.executeQuery();
			
			if( rs.next() ){
				categoria = new Categoria();
				Categoria supercategoria = new Categoria();
				
				categoria.setId(rs.getInt("codigo"));
				categoria.setNome(rs.getString("nome"));
				
				supercategoria.setId(rs.getInt("supercategoria"));
				
				categoria.setCategoria(supercategoria);
			}
			
			ps.close();
			con.close();
			
		} catch (Exception e) {
			throw new CategoriaDAOException( e );
		}
		return categoria;
	}
	
	@Override
	public int pesquisarIdPorNome(String nome) throws CategoriaDAOException {
		
		int id = 0;
		
		try {
			Connection con = gc.getConnection();
			sql = "select codigo from categoria where nome = ?";
		
			PreparedStatement ps = con.prepareStatement( sql );
			ps.setString(1, nome);
			
			ResultSet rs = ps.executeQuery();
			
			if( rs.next() ){
				
				id = rs.getInt("codigo");
				
			}
			
			ps.close();
			con.close();
			
		} catch (Exception e) {
			throw new CategoriaDAOException( e );
		}
		return id;
		
	}
	 @Override
	public Categoria pesquisarPorId(int id){
		 
		 Categoria categoria = null;
			
			try {
				Connection con = gc.getConnection();
				sql = "select * from categoria where codigo = ?";
			
				PreparedStatement ps = con.prepareStatement( sql );
				ps.setInt(1, id);
				
				ResultSet rs = ps.executeQuery();
				
				if( rs.next() ){
					categoria = new Categoria();
					Categoria supercategoria = new Categoria();
					
					categoria.setId(rs.getInt("codigo"));
					categoria.setNome(rs.getString("nome"));
					supercategoria.setId(rs.getInt("supercategoria"));
					
					categoria.setCategoria(supercategoria);
				}
				
				ps.close();
				con.close();
				
			} catch (Exception e) {
				try {
					throw new CategoriaDAOException( e );
				} catch (CategoriaDAOException e1) {
					e1.printStackTrace();
				}
			}
		 
		 return categoria;
	 }

}
