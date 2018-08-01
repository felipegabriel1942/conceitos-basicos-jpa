package br.com.testes;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.bean.Funcionario;
import br.com.bean.Projeto;
import br.com.bean.ProjetoFuncionario;
import br.com.bean.Setor;
import br.com.util.EntityManagerUtil;

public class TesteInserirProjeto {

	public static void main(String[] args) {
		EntityManager em = EntityManagerUtil.geEntityManager();
		Setor setor = em.find(Setor.class, 1);
		Funcionario func = em.find(Funcionario.class, 1);
		Projeto projeto = new Projeto();
		projeto.setAtivo(true);
		projeto.setDescricao("Meu novo projeto JSF");
		projeto.setInicio(Calendar.getInstance());
		projeto.setFim(Calendar.getInstance());
		projeto.setNome("Sistema de funcionarios");
		projeto.setSetor(setor);
		ProjetoFuncionario pf = new ProjetoFuncionario();
		pf.setCargaHoraria(100);
		pf.setFimParticipacao(Calendar.getInstance());
		pf.setFuncionario(func);
		pf.setGestor(true);
		pf.setInicioParticipacao(Calendar.getInstance());
		projeto.adicionarFuncionario(pf);
		em.getTransaction().begin();
		em.persist(projeto);
		em.getTransaction().commit();
		

	}

}
