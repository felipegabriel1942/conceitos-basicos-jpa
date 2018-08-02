package br.com.modelo;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.bean.Grupo;
import br.com.util.EntityManagerUtil;
import br.com.util.UtilErros;
import br.com.util.UtilMensagens;

public class GrupoDAO {

	private EntityManager em;
	
	//Esse construtor inicia a conex�o com o banco ao instanciar a classe
	public GrupoDAO() {
		em = EntityManagerUtil.geEntityManager();
	}


	@SuppressWarnings("unchecked")
	public List<Grupo> listarTodos() {
		return em.createQuery("from Grupo order by nome").getResultList();
	}
	
	//Salvar
	public boolean gravar(Grupo obj) {
		try {
			em.getTransaction().begin();
			if (obj.getId() == null) {
				em.persist(obj);
			} else {
				em.merge(obj);
			}

			em.getTransaction().commit();
			UtilMensagens.mensagemInformacao("Grupo cadastrado com sucesso!");
			return true;
		} catch (Exception e) {
			if (em.getTransaction().isActive() == false) {
				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao cadastrar grupo: " + UtilErros.getMensagemErro(e));
			return false;
		}
	}
	
	//Excluir
	public boolean excluir(Grupo obj) {
		try {
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
			UtilMensagens.mensagemInformacao("Grupo removido com sucesso!");
			return true;
		} catch (Exception e) {
			if (em.getTransaction().isActive() == false) {
				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao remover grupo: " + UtilErros.getMensagemErro(e));
			return false;
		}
	}
	
	public Grupo localizar(Integer id) {
		return em.find(Grupo.class, id);
	}
	
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
