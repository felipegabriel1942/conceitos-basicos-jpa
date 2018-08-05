package br.com.controle;

import java.io.Serializable;
import java.util.Calendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.bean.Funcionario;
import br.com.bean.Projeto;
import br.com.bean.ProjetoFuncionario;
import br.com.conversores.ConverterFuncionario;
import br.com.conversores.ConverterSetor;
import br.com.modelo.FuncionarioDAO;
import br.com.modelo.ProjetoDAO;
import br.com.modelo.SetorDAO;

@ManagedBean(name = "controleProjeto")
@SessionScoped
public class ControleProjeto implements Serializable {

	private static final long serialVersionUID = 1L;

	private ProjetoDAO dao;
	private Projeto obj;
	private FuncionarioDAO daoFuncionario;
	private ConverterFuncionario converterFuncionario;
	private SetorDAO daoSetor;
	private ConverterSetor converterSetor;
	private Funcionario funcionario;
	private Integer cargaHoraria;
	private Boolean gestor;
	private Calendar inicioParticipacao;
	private Calendar fimParticipacao;
	private Boolean addFunc = false;

	public ControleProjeto() {
		dao = new ProjetoDAO();
		daoFuncionario = new FuncionarioDAO();
		converterFuncionario = new ConverterFuncionario();
		daoSetor = new SetorDAO();
		converterSetor = new ConverterSetor();
	}

	public String listar() {
		return "/privado/projeto/listar?faces-redirect=true";
	}

	public String novo() {
		this.obj = new Projeto();
		this.addFunc = false;
		return "form";
	}

	public String cancelar() {
		this.addFunc = false;
		dao.rollBack();
		return "listar";
	}

	public String gravar() {
		if (dao.gravar(obj)) {
			this.addFunc = false;
			return "listar";

		} else {
			return "form";
		}
	}

	public String alterar(Projeto projeto) {
		obj = projeto;
		this.addFunc = false;
		return "form";
	}

	public String excluir(Projeto projeto) {
		dao.excluir(projeto);
		return "listar";
	}

	public void removerFuncionario(ProjetoFuncionario objeto) {
		obj.removerFuncionario(objeto);
	}

	public void adicionarFuncionario() {
		addFunc = true;
	}

	public void cancelarFuncionario() {
		addFunc = false;
	}

	public void salvarFuncionario() {
		ProjetoFuncionario objetoFuncionario = new ProjetoFuncionario();
		objetoFuncionario.setCargaHoraria(cargaHoraria);
		objetoFuncionario.setFuncionario(funcionario);
		objetoFuncionario.setInicioParticipacao(inicioParticipacao);
		objetoFuncionario.setFimParticipacao(fimParticipacao);
		objetoFuncionario.setGestor(gestor);
		obj.adicionarFuncionario(objetoFuncionario);
		addFunc = false;
		cargaHoraria = null;
		funcionario = null;
		inicioParticipacao = null;
		fimParticipacao = null;
		gestor = null;
	}

	
	
	//Getters e Setters
	public ProjetoDAO getDao() {
		return dao;
	}

	public void setDao(ProjetoDAO dao) {
		this.dao = dao;
	}

	public Projeto getObj() {
		return obj;
	}

	public void setObj(Projeto obj) {
		this.obj = obj;
	}

	public FuncionarioDAO getDaoFuncionario() {
		return daoFuncionario;
	}

	public void setDaoFuncionario(FuncionarioDAO daoFuncionario) {
		this.daoFuncionario = daoFuncionario;
	}

	public ConverterFuncionario getConverterFuncionario() {
		return converterFuncionario;
	}

	public void setConverterFuncionario(ConverterFuncionario converterFuncionario) {
		this.converterFuncionario = converterFuncionario;
	}

	public SetorDAO getDaoSetor() {
		return daoSetor;
	}

	public void setDaoSetor(SetorDAO daoSetor) {
		this.daoSetor = daoSetor;
	}

	public ConverterSetor getConverterSetor() {
		return converterSetor;
	}

	public void setConverterSetor(ConverterSetor converterSetor) {
		this.converterSetor = converterSetor;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Integer getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public Boolean getGestor() {
		return gestor;
	}

	public void setGestor(Boolean gestor) {
		this.gestor = gestor;
	}

	public Calendar getInicioParticipacao() {
		return inicioParticipacao;
	}

	public void setInicioParticipacao(Calendar inicioParticipacao) {
		this.inicioParticipacao = inicioParticipacao;
	}

	public Calendar getFimParticipacao() {
		return fimParticipacao;
	}

	public void setFimParticipacao(Calendar fimParticipacao) {
		this.fimParticipacao = fimParticipacao;
	}

	public Boolean getAddFunc() {
		return addFunc;
	}

	public void setAddFunc(Boolean addFunc) {
		this.addFunc = addFunc;
	}

}
