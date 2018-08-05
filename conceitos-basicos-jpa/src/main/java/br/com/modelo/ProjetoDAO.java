package br.com.modelo;


import java.util.List;

import javax.persistence.EntityManager;

import br.com.bean.Projeto;
import br.com.util.EntityManagerUtil;
import br.com.util.UtilErros;
import br.com.util.UtilMensagens;

public class ProjetoDAO{

	
	
	private EntityManager em;
	
	//Esse construtor inicia a conexão com o banco ao instanciar a classe
	public ProjetoDAO() {
		em = EntityManagerUtil.geEntityManager();
	}


	@SuppressWarnings("unchecked")
	public List<Projeto> listarTodos() {
		return em.createQuery("from Projeto order by nome").getResultList();
	}
	
	//Salvar
	public boolean gravar(Projeto obj) {
		try {
			em.getTransaction().begin();
			if (obj.getId() == null) {
				em.persist(obj);
			} else {
				em.merge(obj);
			}

			em.getTransaction().commit();
			UtilMensagens.mensagemInformacao("Projeto cadastrado com sucesso!");
			return true;
		} catch (Exception e) {
			if (em.getTransaction().isActive() == false) {
				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao cadastrar projeto: " + UtilErros.getMensagemErro(e));
			return false;
		}
	}
	
	//Excluir
	public boolean excluir(Projeto obj) {
		try {
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
			UtilMensagens.mensagemInformacao("Projeto removido com sucesso!");
			return true;
		} catch (Exception e) {
			if (em.getTransaction().isActive() == false) {
				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao remover projeto: " + UtilErros.getMensagemErro(e));
			return false;
		}
	}
	
	public Projeto localizar(Integer id) {
		return em.find(Projeto.class, id);
	}
	
	public void rollBack() {
		if(em.getTransaction().isActive() == false){
			em.getTransaction().begin();
		}
		em.getTransaction().rollback();
	}
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
