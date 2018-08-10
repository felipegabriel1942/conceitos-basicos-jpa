package br.com.testes;

import javax.persistence.EntityManager;

import br.com.bean.Grupo;
import br.com.util.EntityManagerUtil;

public class TesteInserirGrupo {
	
	
	public static void main(String[] args) {
		EntityManager em = EntityManagerUtil.geEntityManager();
		Grupo g = new Grupo();
		g.setNome("Administrador");
		em.getTransaction().begin();
		em.persist(g);
		em.getTransaction().commit();
		
	}
	
}
