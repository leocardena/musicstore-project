package model;

import java.io.Serializable;

public class ItemVenda implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Venda venda;
	private Produto produto;
	private int quantidade;	

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

}