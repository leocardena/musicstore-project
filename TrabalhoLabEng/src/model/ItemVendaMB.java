package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import persistence.ItemVendaDAO;
import persistence.ItemVendaDAOException;
import persistence.ItemVendaDAOImpl;

@ManagedBean
@RequestScoped
public class ItemVendaMB {

	private ItemVenda itemVenda;
	private ItemVendaDAO itemVendaDAO;
	private String mes;
	private float lucro;

	public ItemVendaMB() {
		this.setItemVenda(new ItemVenda());
		this.itemVendaDAO = new ItemVendaDAOImpl();
	}
	
	
	
	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}
	
	public float getLucro() {
		return lucro;
	}

	public void setLucro(float lucro) {
		this.lucro = lucro;
	}
	
	public String gerarRelatorio(){
		
		List<ItemVenda> lista = new ArrayList<ItemVenda>();
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		
		try {
			if(this.getMes().equals("0")){
				System.out.println("entrou");
				lista = itemVendaDAO.gerarRelatorioGeral();
				sessionMap.put("lista", lista);
			} else {
				lista = itemVendaDAO.gerarRelatorioMensal( this.getMes() );
				sessionMap.put("lista", lista);
			}			
			return "relatorio.xhtml?faces-redirect=true";
		} catch (ItemVendaDAOException e) {
			System.out.println("ERRO" + e.getMessage());
		}
		
		return "relatorio.xhtml?faces-redirect=true";
	}
	
	public String gerarRelatorioLucro(){
		
		List<ItemVenda> lista = new ArrayList<ItemVenda>();
		
		try {
			lista = itemVendaDAO.gerarRelatorioDeLucro();
						
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();
			sessionMap.put("listaLucro", lista);
			
			return "relatorio_lucro.xhtml?faces-redirect=true";
			
		} catch (ItemVendaDAOException e) {
			System.out.println("ERRO" + e.getMessage());
		}
		
		return "relatorio_lucro.xhtml?faces-redirect=true";
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<ItemVenda> listar(String nome){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();	
		ArrayList<ItemVenda> lista = (ArrayList<ItemVenda>) sessionMap.get(nome);
		return lista;
	}

	



	public ItemVenda getItemVenda() {
		return itemVenda;
	}



	public void setItemVenda(ItemVenda itemVenda) {
		this.itemVenda = itemVenda;
	}


}
