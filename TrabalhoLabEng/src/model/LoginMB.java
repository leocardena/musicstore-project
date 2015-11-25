package model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import persistence.ClienteDAO;
import persistence.ClienteDAOException;
import persistence.ClienteDAOImpl;

@ManagedBean
@SessionScoped
public class LoginMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cliente cliente;
	private ClienteDAO clienteDAO;
	private boolean logado;
	private String tipoUsuarioLogado;

	public LoginMB() {
		this.cliente = new Cliente();
		this.clienteDAO = new ClienteDAOImpl();
	}

	public String logar() throws ClienteDAOException {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession session = request.getSession();
		FacesContext context = FacesContext.getCurrentInstance();

		if (clienteDAO.pesquisarUsuarioSenha(cliente.getUsuario(), cliente.getSenha())) {
			logado = true;
			if ("admin".equals(cliente.getUsuario())) {
				setTipoUsuarioLogado("admin");
				return "/restrito/index_adm.xhtml?faces-redirect=true";
			} else {
				List<Cliente> usuarios = clienteDAO.pesquisarPorUsuario(cliente.getUsuario());
				Cliente clienteLogado = usuarios.get(0);

				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				Map<String, Object> sessionMap = externalContext.getSessionMap();
				sessionMap.put("clienteLogadoObj", clienteLogado);

				setTipoUsuarioLogado("cliente");
				return "/index.xhtml?faces-redirect=true";
			}
		} else {
			FacesMessage mensagem = new FacesMessage("Usuário/senha inválidos!");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			session.removeAttribute("loginMB");
			context.addMessage(null, mensagem);
		}

		return null;

	}

	public String deslogar() {
		logado = false;
		cliente = null;
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("loginMB");
		return "logar?faces-redirect=true";
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getTipoUsuarioLogado() {
		return tipoUsuarioLogado;
	}

	public void setTipoUsuarioLogado(String tipoUsuarioLogado) {
		this.tipoUsuarioLogado = tipoUsuarioLogado;
	}

}
