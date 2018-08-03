package br.com.modelo;


import java.util.List;

import javax.persistence.EntityManager;

import br.com.bean.Funcionario;
import br.com.bean.Grupo;
import br.com.util.EntityManagerUtil;
import br.com.util.UtilErros;
import br.com.util.UtilMensagens;

public class FuncionarioDAO{

	
	
	private EntityManager em;
	
	//Esse construtor inicia a conexão com o banco ao instanciar a classe
	public FuncionarioDAO() {
		em = EntityManagerUtil.geEntityManager();
	}


	@SuppressWarnings("unchecked")
	public List<Funcionario> listarTodos() {
		return em.createQuery("from Funcionario order by nome").getResultList();
	}
	
	//Salvar
	public boolean gravar(Funcionario obj) {
		try {
			em.getTransaction().begin();
			if (obj.getId() == null) {
				em.persist(obj);
			} else {
				em.merge(obj);
			}

			em.getTransaction().commit();
			UtilMensagens.mensagemInformacao("Funcionario cadastrado com sucesso!");
			return true;
		} catch (Exception e) {
			if (em.getTransaction().isActive() == false) {
				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao cadastrar funcionario: " + UtilErros.getMensagemErro(e));
			return false;
		}
	}
	
	//Excluir
	public boolean excluir(Funcionario obj) {
		try {
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
			UtilMensagens.mensagemInformacao("Funcionario removido com sucesso!");
			return true;
		} catch (Exception e) {
			if (em.getTransaction().isActive() == false) {
				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao remover funcionario: " + UtilErros.getMensagemErro(e));
			return false;
		}
	}
	
	public Funcionario localizar(Integer id) {
		return em.find(Funcionario.class, id);
	}
	
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
