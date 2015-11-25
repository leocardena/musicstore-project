package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionImpl;
import connection.GenericConnection;
import model.Cliente;

public class ClienteDAOImpl implements ClienteDAO {

	private GenericConnection gc;
	private String sql;
	
	public ClienteDAOImpl() {
		gc = new ConnectionImpl();
	}
	
	@Override
	public void incluir(Cliente cliente) throws ClienteDAOException {
		try {
			Connection con = gc.getConnection();
			sql = "insert into cliente (codigo,"
					+ "nome, email, data_nascimento, cpf, logradouro, numero, bairro, cep, cidade, "
					+ "estado, ddd, telefone, usuario, senha) values"
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = con.prepareStatement( sql );
			ps.setInt(1, this.pesquisarUltimoId()+1 );
			ps.setString(2, cliente.getNome());
			ps.setString(3, cliente.getEmail());
			ps.setDate(4, new java.sql.Date( cliente.getDataNascimento().getTime() ) );
			ps.setString(5, cliente.getCpf());
			ps.setString(6, cliente.getLogradouro());
			ps.setString(7, cliente.getNumero());
			ps.setString(8, cliente.getBairro());
			ps.setString(9, cliente.getCep());
			ps.setString(10, cliente.getCidade());
			ps.setString(11, cliente.getEstado().toString());
			ps.setString(12, cliente.getDdd());
			ps.setString(13, cliente.getTelefone());
			ps.setString(14, cliente.getUsuario());
			ps.setString(15, cliente.getSenha());
			
			ps.execute();
			ps.close();
			con.close();
			
//			EntityManager em = JPAUtil.getInstance().getEMG().createEntityManager();
//			em.getTransaction().begin();
//			em.persist( cliente );
//			em.getTransaction().commit();
//			em.close();
		} catch (Exception e) {
			throw new ClienteDAOException( e );
		}
	}

	@Override
	public void atualizar(Cliente cliente) throws ClienteDAOException {
		try {
			Connection con = gc.getConnection();
			sql = "update cliente set"
					+ "nome = ? "
					+ "email = ? "
					+ "data_nascimento = ? "
					+ "cpf = ? "
					+ "logradouro = ? "
					+ "numero = ? "
					+ "bairro = ?  "
					+ "cep = ?  "
					+ "cidade = ?  "
					+ "estado = ?  "
					+ "ddd = ?  "
					+ "telefone = ?  "
					+ "usuario = ? "
					+ "senha = ? "
					+ "where codigo = ?";
			
			PreparedStatement ps = con.prepareStatement( sql );
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getEmail());
			ps.setDate(3, new java.sql.Date( cliente.getDataNascimento().getTime()) );
			ps.setString(4, cliente.getCpf());
			ps.setString(5, cliente.getLogradouro());
			ps.setString(6, cliente.getNumero());
			ps.setString(7, cliente.getBairro());
			ps.setString(8, cliente.getCep());
			ps.setString(9, cliente.getCidade());
			ps.setString(10, cliente.getEstado().toString());
			ps.setString(11, cliente.getDdd());
			ps.setString(12, cliente.getTelefone());
			ps.setString(13, cliente.getUsuario());
			ps.setString(14, cliente.getSenha());
			ps.setInt(15, cliente.getId());
						
			ps.executeUpdate();
			ps.close();
			con.close();
			
//			EntityManager em = JPAUtil.getInstance().getEMG().createEntityManager();
//			em.getTransaction().begin();
//			em.merge( cliente );
//			em.getTransaction().commit();
//			em.close();
		} catch (Exception e) {
			throw new ClienteDAOException( e );
		}
	}

	@Override
	public List<Cliente> pesquisarPorNome(String nome) throws ClienteDAOException {
		List<Cliente> lista = new ArrayList<Cliente>();
		
		try {
			Connection con = gc.getConnection();
			sql = "select * from cliente where nome like ?";
			
			PreparedStatement ps = con.prepareStatement( sql );
			ps.setString(1, "%" + nome + "%");
			ResultSet rs = ps.executeQuery();
			
			while( rs.next() ){
				Cliente cliente = new Cliente();
				cliente.setNome( rs.getString("nome") );
				cliente.setEmail( rs.getString("email") );
				cliente.setNome( rs.getString("data_nascimento") );
				cliente.setNome( rs.getString("cpf") );
				cliente.setNome( rs.getString("logradouro") );
				cliente.setNome( rs.getString("numero") );
				cliente.setNome( rs.getString("bairro") );
				cliente.setNome( rs.getString("cep") );
				cliente.setCidade( rs.getString("cidade") );
				cliente.setEstado( rs.getString("estado") );
				cliente.setDdd( rs.getString("ddd") );
				cliente.setTelefone( rs.getString("telefone") );
				cliente.setUsuario( rs.getString("usuario") );
				cliente.setSenha( rs.getString("senha") );
				lista.add( cliente );
			}
			
			ps.close();
			con.close();
			
//			TypedQuery<Cliente> qry = em.createQuery(sql, Cliente.class);
//			qry.setParameter("n", "%" + nome + "%");
//			lista = qry.getResultList();
//			
//			em.close();
			
		} catch (Exception e) {
			throw new ClienteDAOException( e );
		}
		return lista;
	}
	
	@Override
	public List<Cliente> pesquisarPorUsuario(String usuario){
		List<Cliente> lista = new ArrayList<Cliente>();
			
		try {
			Connection con = gc.getConnection();
			sql = "select * from cliente where usuario = ?";
			
			PreparedStatement ps = con.prepareStatement( sql );
			ps.setString(1, usuario);
			ResultSet rs = ps.executeQuery();
			
			while( rs.next() ){
				Cliente cliente = new Cliente();
				cliente.setNome( rs.getString("nome") );
				cliente.setEmail( rs.getString("email") );
				cliente.setNome( rs.getString("data_nascimento") );
				cliente.setNome( rs.getString("cpf") );
				cliente.setNome( rs.getString("logradouro") );
				cliente.setNome( rs.getString("numero") );
				cliente.setNome( rs.getString("bairro") );
				cliente.setNome( rs.getString("cep") );
				cliente.setCidade( rs.getString("cidade") );
				cliente.setEstado( rs.getString("estado") );
				cliente.setDdd( rs.getString("ddd") );
				cliente.setTelefone( rs.getString("telefone") );
				cliente.setUsuario( rs.getString("usuario") );
				cliente.setSenha( rs.getString("senha") );
				lista.add( cliente );
			}
			
			ps.close();
			con.close();
			
		} catch (Exception e) {
			try {
				throw new ClienteDAOException( e );
			} catch (ClienteDAOException e1) {
				e1.printStackTrace();
			}
		}
		return lista;
	}

	@Override
	public boolean pesquisarUsuarioSenha(String usuario, String senha) throws ClienteDAOException {

		boolean aux = false;
		
		try {
			Connection con = gc.getConnection();
			sql = "select * from cliente "
					+ "where usuario = ? and senha = ? ";
			
			PreparedStatement ps = con.prepareStatement( sql );
			ps.setString(1, usuario);
			ps.setString(2, senha);
			ResultSet rs = ps.executeQuery();
			
			if( rs.next() ){
				aux = true;
			}
			
			ps.close();
			con.close();
			
		} catch (Exception e) {
			throw new ClienteDAOException( e );
		}
		return aux;
	}

	@Override
	public int pesquisarUltimoId() throws ClienteDAOException {

		int id = 0;

		try {
			Connection con = gc.getConnection();
			sql = "select max(codigo) from cliente";

			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			rs.next();

			id = rs.getInt(1);

			ps.close();
			rs.close();
			con.close();

		} catch (Exception e) {
			throw new ClienteDAOException(e);
		}

		return id == 0 ? 1 : id;

	}
		
}
