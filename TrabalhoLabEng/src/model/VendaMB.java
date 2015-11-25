package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import persistence.ItemVendaDAO;
import persistence.ItemVendaDAOException;
import persistence.ItemVendaDAOImpl;
import persistence.VendaDAO;
import persistence.VendaDAOException;
import persistence.VendaDAOImpl;

@ManagedBean
@SessionScoped
public class VendaMB implements Serializable {

	private static final long serialVersionUID = 5825557975331231786L;
	private Venda venda;
	private VendaDAO vendaDAO;
	private Produto produto;
	private List<ItemVenda> itens;
	private ItemVendaDAO itemVendaDAO;
	private Produto produtoSelecionado;
	private int quantidade;
	private float total;

	public VendaMB() {
		this.itemVendaDAO = new ItemVendaDAOImpl();
		this.venda = new Venda();
		this.vendaDAO = new VendaDAOImpl();
		this.setProduto(new Produto());
		itens = new ArrayList<ItemVenda>();
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}

	public void finalizarCompra() {
		float valorTotal = calcular();

		venda.setValor(valorTotal);
		venda.setData(new Date());
		venda.setListaVenda(itens);

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		Cliente cliente = (Cliente) sessionMap.get("clienteLogadoObj");
		venda.setCliente(cliente);

		try {

			itemVendaDAO.incluir(itens);
			vendaDAO.incluir(venda);
			itens = new ArrayList<>();
			venda = new Venda();

		} catch (VendaDAOException | ItemVendaDAOException e) {
			System.out.println("ERRO" + e.getMessage());
		}
	}

	private float calcular() {
		float valor = 0;
		for (ItemVenda itemVenda : itens) {
			valor += itemVenda.getProduto().getEstoque().getValorVenda() * itemVenda.getQuantidade();
		}
		return valor;
	}

	public String finalizarCompraRedirect() {
		return "/restrito_usuario/finalizar_compra.xhtml?faces-redirect=true";
	}

	public String adicionarProduto() {
		if (!(quantidade > produtoSelecionado.getEstoque().getQuantidade())) {
			ItemVenda itemVenda = new ItemVenda();
			itemVenda.setProduto(produtoSelecionado);
			itemVenda.setVenda(venda);
			itemVenda.setQuantidade(quantidade);
			itens.add(itemVenda);
			quantidade = 0;
			System.out.println("adc");
			return "pesquisar_produto.xhtml?faces-redirect=true";
		} else {
			quantidade = 0;
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage mensagem = new FacesMessage("Quantidade superior ao estoque!");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
			System.out.println("n adc");
			return "pesquisar_produto.xhtml?faces-redirect=true";
		}
	}

	public void limparCompra() {
		itens = new ArrayList<>();
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public float getTotal() {
		total = calcular();
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

}
