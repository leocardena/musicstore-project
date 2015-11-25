package model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import persistence.ClienteDAO;
import persistence.ClienteDAOException;
import persistence.ClienteDAOImpl;
import util.Estado;

@ManagedBean
@RequestScoped
public class ClienteMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cliente cliente;
	private ClienteDAO clienteDao;
	
	@SuppressWarnings("unused")
	private SelectItem[] nomesEstados;

	public ClienteMB() {
		this.cliente = new Cliente();
		this.clienteDao = new ClienteDAOImpl();
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void incluir(){
		try {
			clienteDao.incluir( cliente );
			cliente = new Cliente();
		} catch (ClienteDAOException e) {
			System.out.println("ERRO" + e.getMessage());
		}
	}
	
	public void atualizar(){
		try {
			clienteDao.atualizar( cliente );
			cliente = new Cliente();
		} catch (ClienteDAOException e) {
			System.out.println("ERRO" + e.getMessage());
		}
	}
	
	public void pesquisarPorNome(){
		try {
			clienteDao.pesquisarPorNome( cliente.getNome() );
			cliente = new Cliente();
		} catch (ClienteDAOException e) {
			System.out.println("ERRO" + e.getMessage());
		}
	}


	public SelectItem[] getNomesEstados() {
		   	SelectItem[] items = new SelectItem[Estado.values().length];
		    int i = 0;
		    for(Estado t: Estado.values()) {
		        items[i++] = new SelectItem(t, t.getEstado());
		    }
		    return items;
	}

	public void setNomesEstados(SelectItem[] nomesEstados) {
		this.nomesEstados = nomesEstados;
	}
	
}
