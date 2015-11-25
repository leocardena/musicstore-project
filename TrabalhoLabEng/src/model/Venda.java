package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Venda implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Date data;
	private List<ItemVenda> listaVenda;
	private Cliente cliente;
	private boolean pago;
	private float valor;
	
	public Venda(){
		this.listaVenda = new ArrayList<ItemVenda>();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	
	public List<ItemVenda> getListaVenda() {
		return listaVenda;
	}
	
	public void setListaVenda(List<ItemVenda> listaVenda) {
		this.listaVenda = listaVenda;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public boolean isPago() {
		return pago;
	}
	
	public void setPago(boolean pago) {
		this.pago = pago;
	}
	
	public float getValor() {
		return valor;
	}
	
	public void setValor(float valor) {
		this.valor = valor;
	}
	
}
