package controller;

import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import model.Produto;
import persistence.ProdutoDAO;
import persistence.ProdutoDAOException;
import persistence.ProdutoDAOImpl;

@RequestScoped
@ManagedBean
public class MenuViewController {

	private String input;

	public String pesquisarCategoria(String nomeCategoria) {
		ProdutoDAO dao = new ProdutoDAOImpl();
		try {
			List<Produto> produtos = dao.pesquisarPorCategoria(nomeCategoria);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();
			sessionMap.put("produtosObj", produtos);
			return "pesquisar_produto.xhtml?faces-redirect=true";
		} catch (ProdutoDAOException e) {
			e.printStackTrace();
		}
		return "pesquisar_produto.xhtml?faces-redirect=true";
	}

	public String redirectPesquisa() {
		System.out.println("acionou");
		return "logar.xhtml?faces-redirect=true";
	}

	public String pesquisarNome() {
		ProdutoDAO dao = new ProdutoDAOImpl();
		try {
			List<Produto> produtos = dao.pesquisarPorNome(this.input);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();
			sessionMap.put("produtosObj", produtos);
			return "pesquisar_produto.xhtml?faces-redirect=true";
		} catch (ProdutoDAOException e) {
			e.printStackTrace();
		}

		return "pesquisar_produto.xhtml?faces-redirect=true";
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

}
