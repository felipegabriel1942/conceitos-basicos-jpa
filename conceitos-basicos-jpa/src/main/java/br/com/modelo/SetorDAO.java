package br.com.modelo;


import java.util.List;

import javax.persistence.EntityManager;


import br.com.bean.Setor;
import br.com.util.EntityManagerUtil;
import br.com.util.UtilErros;
import br.com.util.UtilMensagens;

public class SetorDAO{

	
	
	private EntityManager em;
	
	//Esse construtor inicia a conexão com o banco ao instanciar a classe
	public SetorDAO() {
		em = EntityManagerUtil.geEntityManager();
	}


	@SuppressWarnings("unchecked")
	public List<Setor> listarTodos() {
		return em.createQuery("from Setor order by nome").getResultList();
	}
	
	//Salvar
	public boolean gravar(Setor obj) {
		try {
			em.getTransaction().begin();
			if (obj.getId() == null) {
				em.persist(obj);
			} else {
				em.merge(obj);
			}

			em.getTransaction().commit();
			UtilMensagens.mensagemInformacao("Setor cadastrado com sucesso!");
			return true;
		} catch (Exception e) {
			if (em.getTransaction().isActive() == false) {
				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao cadastrar setor: " + UtilErros.getMensagemErro(e));
			return false;
		}
	}
	
	//Excluir
	public boolean excluir(Setor obj) {
		try {
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
			UtilMensagens.mensagemInformacao("Setor removido com sucesso!");
			return true;
		} catch (Exception e) {
			if (em.getTransaction().isActive() == false) {
				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao remover setor: " + UtilErros.getMensagemErro(e));
			return false;
		}
	}
	
	public Setor localizar(Integer id) {
		return em.find(Setor.class, id);
	}
	
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
