package br.com.testes;

import javax.persistence.EntityManager;

import br.com.bean.Setor;
import br.com.util.EntityManagerUtil;

public class TesteInserirSetor {

	public static void main(String[] args) {
		EntityManager em = EntityManagerUtil.geEntityManager();
		Setor s = new Setor();
		s.setNome("Administrativo");
		Setor s2 = new Setor();
		s2.setNome("Setor Operacional");
		em.getTransaction().begin();
		em.persist(s);
		em.persist(s2);
		em.getTransaction().commit();
		System.out.println("Sucesso na inserção!");

	}

}
