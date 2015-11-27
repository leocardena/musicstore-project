package persistence;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionImpl;
import connection.GenericConnection;
import model.Categoria;
import model.Estoque;
import model.Produto;

public class ProdutoDAOImpl implements ProdutoDAO, Serializable {

	private static final long serialVersionUID = 1101769912540687887L;
	private GenericConnection gc;
	private String sql;
	private List<Produto> lista;

	public ProdutoDAOImpl() {
		gc = new ConnectionImpl();
	}

	@Override
	public void inserir(Produto produto) throws ProdutoDAOException {
		try {
			Connection con = gc.getConnection();
			sql = "insert into produto (codigo, nome, descricao, especificacao," + "codigo_categoria, codigo_estoque) "
					+ "values (?, ?, ?, ?, ?, ?)";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, this.pesquisarUltimoId() + 1);
			ps.setString(2, produto.getNome());
			ps.setString(3, produto.getDescricao());
			ps.setString(4, produto.getEspecificacao());
			ps.setInt(5, produto.getCategoria().getId());
			ps.setInt(6, produto.getEstoque().getId());

			ps.execute();
			ps.close();
			con.close();

		} catch (Exception e) {
			throw new ProdutoDAOException(e);
		}

	}

	@Override
	public void atualizar(Produto produto) throws ProdutoDAOException {

		try {
			Connection con = gc.getConnection();
			sql = "update produto set" + " nome = ?, " + "descricao = ?, " + "especificacao = ?, "
					+ "codigo_categoria = ?, " + "codigo_estoque = ? " + "where codigo = ?;";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, produto.getNome());
			ps.setString(2, produto.getDescricao());
			ps.setString(3, produto.getEspecificacao());
			ps.setLong(4, produto.getCategoria().getId());
			ps.setLong(5, produto.getEstoque().getId());
			ps.setInt(6, produto.getId());

			ps.executeUpdate();
			ps.close();
			con.close();

		} catch (SQLException e) {
			throw new ProdutoDAOException(e);
		}

	}

	@Override
	public void remover(int id) throws ProdutoDAOException {

		try {
			Connection con = gc.getConnection();
			sql = "delete produto where id = ?";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);

			ps.execute();
			ps.close();
			con.close();

		} catch (SQLException e) {
			throw new ProdutoDAOException(e);
		}

	}

	@Override
	public List<Produto> pesquisarPorNome(String nome) throws ProdutoDAOException {
		lista = new ArrayList<Produto>();

		try {
			Connection con = gc.getConnection();
			sql = "select p.codigo as p_codigo, " + "p.nome as p_nome, " + "p.descricao as p_descricao, "
					+ "p.especificacao as p_especificacao, " + "c.nome as c_nome, " + "c.codigo as c_codigo,"
					+ "c.supercategoria as c_super " + "from produto p " + "inner join categoria c "
					+ "on p.codigo_categoria = c.codigo " + "where p.nome like ?";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + nome + "%");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("p_codigo"));
				produto.setNome(rs.getString("p_nome"));
				produto.setDescricao(rs.getString("p_descricao"));
				produto.setEspecificacao(rs.getString("p_especificacao"));
				produto.setEstoque(pesquisarEstoquedoProduto(produto.getId()));
				produto.getCategoria().setId(rs.getInt("c_codigo"));
				produto.getCategoria().setNome(rs.getString("c_nome"));
				Categoria c = new Categoria();
				c.setId(rs.getInt("c_super"));
				produto.getCategoria().setCategoria(c);
				lista.add(produto);
			}

			ps.close();
			con.close();

		} catch (SQLException e) {
			throw new ProdutoDAOException(e);
		}

		return lista;
	}

	@Override
	public List<Produto> pesquisarPorCategoria(String nome) throws ProdutoDAOException {
		lista = new ArrayList<Produto>();
		try {
			Connection con = gc.getConnection();
			sql = "select p.codigo as p_codigo, " + "p.nome as p_nome, " + "p.descricao as p_descricao, "
					+ "p.especificacao as p_especificacao, " + "c.nome as c_nome, " + "c.codigo as c_codigo,"
					+ "c.supercategoria as c_super " + "from produto p " + "inner join categoria c "
					+ "on p.codigo_categoria = c.codigo " + "where c.nome = ?";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nome);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("p_codigo"));
				produto.setNome(rs.getString("p_nome"));
				produto.setDescricao(rs.getString("p_descricao"));
				produto.setEspecificacao(rs.getString("p_especificacao"));
				produto.setEstoque(pesquisarEstoquedoProduto(produto.getId()));
				produto.getCategoria().setId(rs.getInt("c_codigo"));
				produto.getCategoria().setNome(rs.getString("c_nome"));
				Categoria c = new Categoria();
				c.setId(rs.getInt("c_super"));
				produto.getCategoria().setCategoria(c);
				lista.add(produto);
			}

			ps.close();
			con.close();

		} catch (SQLException e) {
			throw new ProdutoDAOException(e);
		}
		return lista;
	}

	@Override
	public Produto pesquisarPorId(int id) throws ProdutoDAOException {

		Produto produto = new Produto();

		try {
			Connection con = gc.getConnection();
			sql = "select * from categoria c " + "inner join produto p " + "on ( c.codigo = p.codigoCategoria ) "
					+ "inner join estoque e on (p.codigoEstoque = e.codigo ) " + "where p.codigo = ?";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				Categoria categoria = new Categoria();
				Categoria supercategoria = new Categoria();
				Estoque estoque = new Estoque();

				produto.setNome(rs.getString("p.nome"));
				produto.setDescricao(rs.getString("p.descricao"));
				produto.setEspecificacao(rs.getString("p.especificacao"));

				categoria.setNome(rs.getString("c.nome"));
				supercategoria.setId(rs.getInt("c.supercategoria"));
				categoria.setCategoria(supercategoria);

				estoque.setQuantidade(rs.getInt("e.quantidade"));
				estoque.setValorCusto(rs.getInt("e.valorCusto"));
				estoque.setValorVenda(rs.getInt("e.valorVenda"));

				produto.setEstoque(estoque);
				produto.setCategoria(categoria);

			}
		} catch (SQLException e) {
			throw new ProdutoDAOException(e);
		}
		return produto;
	}

	public Estoque pesquisarEstoquedoProduto(int id) {
		Estoque estoque = new Estoque();

		try {
			Connection con = gc.getConnection();
			sql = "SELECT e.valor_venda, e.codigo, e.valor_custo, " + "e.quantidade " + "FROM estoque e " + "INNER JOIN  produto p "
					+ "ON e.codigo = p.codigo_estoque " + "WHERE p.codigo = ?;";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				estoque.setValorVenda(rs.getFloat("valor_venda"));
				estoque.setValorCusto(rs.getFloat("valor_custo"));
				estoque.setId(rs.getInt("codigo"));
				estoque.setQuantidade(rs.getInt("quantidade"));
			}

			ps.close();
			con.close();

		} catch (SQLException e) {
			try {
				throw new ProdutoDAOException(e);
			} catch (ProdutoDAOException e1) {
				e1.printStackTrace();
			}
		}
		return estoque;
	}

	@Override
	public int pesquisarUltimoId() throws ProdutoDAOException {

		int id = 0;

		try {
			Connection con = gc.getConnection();
			sql = "select max(codigo) from produto";

			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			rs.next();

			id = rs.getInt(1);

			ps.close();
			rs.close();
			con.close();

		} catch (Exception e) {
			throw new ProdutoDAOException(e);
		}

		return id == 0 ? 1 : id;

	}
}
