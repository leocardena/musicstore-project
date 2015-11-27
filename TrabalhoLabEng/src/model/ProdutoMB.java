package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import persistence.CategoriaDAO;
import persistence.CategoriaDAOException;
import persistence.CategoriaDAOImpl;
import persistence.EstoqueDAO;
import persistence.EstoqueDAOException;
import persistence.EstoqueDAOImpl;
import persistence.ProdutoDAO;
import persistence.ProdutoDAOException;
import persistence.ProdutoDAOImpl;

@RequestScoped
@ManagedBean
public class ProdutoMB {

	private Produto produto;
	private CategoriaDAO categoriaDAO;
	private ProdutoDAO produtoDAO;
	private EstoqueDAO estoqueDAO;
	private List<Produto> lista;
	private List<SelectItem> categorias;
	private Produto produtoSelecionado;

	public ProdutoMB() {
		this.produto = new Produto();
		this.produtoDAO = new ProdutoDAOImpl();
		this.estoqueDAO = new EstoqueDAOImpl();
		this.categoriaDAO = new CategoriaDAOImpl();
		this.produtoSelecionado = new Produto();
	}

	@PostConstruct
	public void init() {
		SelectItemGroup sig1 = new SelectItemGroup("Cordas");
		sig1.setSelectItems(new SelectItem[] { new SelectItem("Guitarra", "Guitarra"), new SelectItem("Baixo", "Baixo"),
				new SelectItem("Ukulele", "Ukulele"), new SelectItem("Cavaco", "Cavaco"),
				new SelectItem("Violão", "Violão"), new SelectItem("Baixolão", "Baixolão"),
				new SelectItem("Banjo", "Banjo") });

		SelectItemGroup sig2 = new SelectItemGroup("Percursão");
		sig2.setSelectItems(new SelectItem[] { new SelectItem("Bateria", "Bateria"),
				new SelectItem("Bateria Eletrônica", "Bateria Eletrônica"), new SelectItem("Cajón", "Cajón"),
				new SelectItem("Pandeiro", "Pandeiro"), new SelectItem("Atabaque", "Atabaque") });

		SelectItemGroup sig3 = new SelectItemGroup("Sopro");
		sig3.setSelectItems(new SelectItem[] { new SelectItem("Flauta", "Flauta"),
				new SelectItem("Clarinete", "Clarinete"), new SelectItem("Saxofone", "Saxofone"),
				new SelectItem("Trompete", "Trompete"), new SelectItem("Trombone", "Trombone") });

		SelectItemGroup sig4 = new SelectItemGroup("Áudio");
		sig4.setSelectItems(new SelectItem[] { new SelectItem("Microfone", "Microfone"), new SelectItem("Fone", "Fone"),
				new SelectItem("Mesa Analógica", "Mesa Analógica") });

		SelectItemGroup sig5 = new SelectItemGroup("Teclas");
		sig5.setSelectItems(new SelectItem[] { new SelectItem("Piano", "Piano"), new SelectItem("Teclado", "Teclado"),
				new SelectItem("Acordeon/Sanfona", "Acordeon/Sanfona") });

		SelectItemGroup sig6 = new SelectItemGroup("Acessórios");
		sig6.setSelectItems(new SelectItem[] { new SelectItem("Cordas", "Cordas"),
				new SelectItem("Percursão", "Percursão"), new SelectItem("Sopro", "Sopro"),
				new SelectItem("Áudio", "Áudio"), new SelectItem("Teclas", "Teclas") });

		categorias = new ArrayList<SelectItem>();
		categorias.add(sig1);
		categorias.add(sig2);
		categorias.add(sig3);
		categorias.add(sig4);
		categorias.add(sig5);
		categorias.add(sig6);

	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void inserir() {
		try {

			int idCategoria = categoriaDAO.pesquisarIdPorNome(this.produto.getCategoria().getNome());
			this.produto.getCategoria().setId(idCategoria);

			estoqueDAO.incluir(this.produto.getEstoque());
			this.produto.getEstoque().setId(estoqueDAO.pesquisarUltimoId());

			produtoDAO.inserir(this.produto);

			System.out.println("PRODUTO INSERIDO");
		} catch (ProdutoDAOException | EstoqueDAOException | CategoriaDAOException e) {
			System.out.println("ERRO" + e.getMessage());
		}
	}

	public List<Produto> pesquisarPorNome() {

		lista = new ArrayList<Produto>();

		try {
			lista = produtoDAO.pesquisarPorNome(produto.getNome());
			produto = null;
		} catch (ProdutoDAOException e) {
			System.out.println("ERRO" + e.getMessage());
		}

		return lista;
	}

	public Produto pesquisarPorId() {

		Produto p = new Produto();

		try {
			p = produtoDAO.pesquisarPorId(produto.getId());
			if (estoqueDAO.verificarEstoque(p.getEstoque().getId())) {
				return p;
			} else {
				return null;
			}

		} catch (ProdutoDAOException | EstoqueDAOException e) {
			System.out.println("ERRO" + e.getMessage());
		}

		return p;

	}

	public List<Produto> pesquisarPorCategoria() {

		lista = new ArrayList<Produto>();

		try {
			lista = produtoDAO.pesquisarPorCategoria(produto.getCategoria().getNome());
		} catch (ProdutoDAOException e) {
			System.out.println("ERRO" + e.getMessage());
		}

		return lista;

	}

	public List<SelectItem> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<SelectItem> categorias) {
		this.categorias = categorias;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("produtoSelecionadoObj", produtoSelecionado);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Produto> listaProdutos() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		ArrayList<Produto> produtos = (ArrayList<Produto>) sessionMap.get("produtosObj");
		return produtos;
	}

	public String editarPorCategoria() {
		ProdutoDAO dao = new ProdutoDAOImpl();
		try {
			List<Produto> produtos = dao.pesquisarPorCategoria(produto.getCategoria().getNome());
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();
			sessionMap.put("produtosObj", produtos);
			return "editar_produto.xhtml?faces-redirect=true";
		} catch (ProdutoDAOException e) {
			e.printStackTrace();
		}
		return "editar_produto.xhtml?faces-redirect=true";
	}
	
	public String removerPorCategoria() {
		ProdutoDAO dao = new ProdutoDAOImpl();
		try {
			List<Produto> produtos = dao.pesquisarPorCategoria(produto.getCategoria().getNome());
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();
			sessionMap.put("produtosObj", produtos);
			return "remover_produto.xhtml?faces-redirect=true";
		} catch (ProdutoDAOException e) {
			e.printStackTrace();
		}
		return "remover_produto.xhtml?faces-redirect=true";
	}

	public String atualizarProduto() {
		ProdutoDAO produtoDAO = new ProdutoDAOImpl();
		CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();
			Produto p = (Produto) sessionMap.get("produtoSelecionadoObj");
			p.setNome(produtoSelecionado.getNome());
			p.setDescricao(produtoSelecionado.getDescricao());
			p.setEspecificacao(produtoSelecionado.getEspecificacao());
			p.getCategoria().setId(categoriaDAO.pesquisarIdPorNome(produtoSelecionado.getCategoria().getNome()));

			produtoDAO.atualizar(p);
			System.out.println("Produto Atualizado");
		} catch (ProdutoDAOException e) {
			e.printStackTrace();
		} catch (CategoriaDAOException e) {
			e.printStackTrace();
		}

		

		return editarPorCategoria();
	}
	
	public String removerProduto(){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		Produto p = (Produto) sessionMap.get("produtoSelecionadoObj");
		try {
			produtoDAO.remover(p.getId());
			System.out.println("Produto excluído");
			return editarPorCategoria();
		} catch (ProdutoDAOException e) {
			e.printStackTrace();
		}
		
		return removerPorCategoria();
	}

	public List<String> buscarImagens() {
		List<String> imagens = new ArrayList<String>();
		imagens.add("baixo.jpg");
		imagens.add("bateria.jpg");
		imagens.add("flauta.jpg");
		imagens.add("guitarra.jpg");
		imagens.add("microfone.jpg");
		imagens.add("sanfona.jpg");
		imagens.add("saxofone.jpg");
		imagens.add("ukulele.jpg");
		imagens.add("violao.jpg");
		return imagens;
	}

}
