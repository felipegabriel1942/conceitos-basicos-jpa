package br.com.controle;




import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.bean.Grupo;
import br.com.modelo.GrupoDAO;
import br.com.util.UtilMensagens;

@ManagedBean(name="controleGrupo")
@SessionScoped
public class ControleGrupo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private GrupoDAO dao;
	private Grupo obj;
	@ManagedProperty(value="#{controleLogin}")
	private ControleLogin controleLogin;
	
	public ControleGrupo() {
		dao = new GrupoDAO();
	}
	
	public String listar() {
		return "/privado/grupo/listar?faces-redirect=true";
	}
	
	public String novo() {
		obj = new Grupo();
		return "form";
	}
	
	public String cancelar() {
		return "listar";
	}
	
	public String gravar() {
		if(dao.gravar(obj)) {
			return "listar";
		} else {
			return "form";
		}
	}
	
	public String alterar(Grupo grupo) {
		obj = grupo;
		return "form";
	}
	
	public String excluir(Grupo grupo) {
		if(controleLogin.getUsuarioLogado().getGrupo().getNome().equals("Administrador")) {
			dao.excluir(grupo);
		} else {
			UtilMensagens.mensagemErro("Usuario não tem autorização para exclusão!");
		}		
		return "listar";
	}
	
		
	public GrupoDAO getDao() {
		return dao;
	}
	public void setDao(GrupoDAO dao) {
		this.dao = dao;
	}
	public Grupo getObj() {
		return obj;
	}
	public void setObj(Grupo obj) {
		this.obj = obj;
	}
	
	

}
