package model;

import java.io.Serializable;
import java.util.List;

public class Categoria implements Serializable {

	private static final long serialVersionUID = -8781478602286052220L;
	private int id;
	private String nome;
	private Categoria categoria;
	private List<Produto> listaProduto;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public List<Produto> getListaProduto() {
		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}

	
}
