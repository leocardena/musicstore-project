package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionImpl implements GenericConnection {

	private Connection con;
	
	@Override
	public Connection getConnection() {
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", 
//					"SYSTEM", 
//					"aluno");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return con;
//		try {
//			Class.forName("net.sourceforge.jtds.jdbc.Driver");
//			con = DriverManager
//					.getConnection(
//							"jdbc:jtds:sqlserver://localhost:1433;"
//							+"DatabaseName=music_store;namedPipe=true;instance=SQLExpress",
//							"sa", "senha");
//		} catch (ClassNotFoundException e) {
//			System.out.println("ERRO"+e.getMessage());
//		} catch (SQLException e) {
//			System.out.println("ERRO"+e.getMessage());
//		} catch (Exception e) {
//			System.out.println("ERRO"+e.getMessage());
//		}
//		return con;
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			con = DriverManager
					.getConnection(
							"jdbc:jtds:sqlserver://localhost:1433;"
							+"DatabaseName=musicstore;namedPipe=true",
							"sa", "senha");
		} catch (ClassNotFoundException e) {
			System.out.println("ERRO"+e.getMessage());
		} catch (SQLException e) {
			System.out.println("ERRO"+e.getMessage());
		} catch (Exception e) {
			System.out.println("ERRO"+e.getMessage());
		}
		return con;
	}	

	@Override
	public void fechaConexao(Connection con) {
		try {
			if (con != null)
				con.close();
			con = null;
		} catch (Exception e) {
			System.out.println("ERRO" + e.getMessage() );
		}	
	}

}
