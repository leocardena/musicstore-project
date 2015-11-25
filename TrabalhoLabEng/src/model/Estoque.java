package model;

import java.io.Serializable;
import java.util.List;

public class Estoque implements Serializable {

	private static final long serialVersionUID = 7870717197325354651L;
	
	private int id;
	private float valorCusto;
	private float valorVenda;
	private int quantidade;
	private List<Produto> listaProduto;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public float getValorCusto() {
		return valorCusto;
	}
	
	public void setValorCusto(float valorCusto) {
		this.valorCusto = valorCusto;
	}
	
	public float getValorVenda() {
		return valorVenda;
	}
	
	public void setValorVenda(float valorVenda) {
		this.valorVenda = valorVenda;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
		
	public List<Produto> getListaProduto() {
		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}

}
