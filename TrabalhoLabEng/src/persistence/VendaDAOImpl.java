package persistence;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.ConnectionImpl;
import connection.GenericConnection;
import model.Venda;

public class VendaDAOImpl implements VendaDAO, Serializable {

	private static final long serialVersionUID = 4181682910101891906L;
	private GenericConnection gc;
	private String sql;
	
	public VendaDAOImpl() {
		gc = new ConnectionImpl();
	}
	
	@Override
	public void incluir(Venda venda) throws VendaDAOException {

		try {
			Connection con = gc.getConnection();
			sql = "insert into venda (codigo, data_venda, valor, cliente_venda, pagamento) "
					+ "values (?, ?, ?, ?, ?)";
			
			PreparedStatement ps = con.prepareStatement( sql );
			ps.setInt(1, this.pesquisarUltimoId()+1 );
//			ps.setDate(2, new Date());
			ps.setFloat(3, venda.getValor() );
			ps.setInt(4, venda.getCliente().getId() );
			ps.setInt(5, (venda.isPago() ) ? 1 : 0 );
			
			ps.execute();
			ps.close();
			con.close();
			
		} catch (Exception e){
			throw new VendaDAOException( e );
		}
		
	}

	@Override
	public int pesquisarUltimoId() throws VendaDAOException {


		int id = 0;

		try {
			Connection con = gc.getConnection();
			sql = "select max(codigo) from venda";

			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			rs.next();

			id = rs.getInt(1);

			ps.close();
			rs.close();
			con.close();

		} catch (Exception e) {
			throw new VendaDAOException(e);
		}

		return id == 0 ? 1 : id ;

	}

}
