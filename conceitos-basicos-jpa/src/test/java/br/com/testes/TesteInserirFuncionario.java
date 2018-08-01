package br.com.testes;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.bean.Funcionario;
import br.com.bean.Grupo;
import br.com.bean.Setor;
import br.com.util.EntityManagerUtil;

public class TesteInserirFuncionario {

	public static void main(String[] args) {
		EntityManager em = EntityManagerUtil.geEntityManager();
		Grupo grupo = em.find(Grupo.class, 1);
		Setor setor = em.find(Setor.class, 1);
		Funcionario f = new Funcionario();
		f.setAtivo(true);
		f.setCpf("029.720.643-56");
		f.setEmail("Pinheiro_felipeg@gmail.com.br");
		f.setGrupo(grupo);
		f.setSetor(setor);
		f.setNascimento(Calendar.getInstance());
		f.setNome("Felipe Gabriel");
		f.setNomeUsuario("felipe_gabriel");
		f.setSalario(5000.00);
		f.setSenha("123456");
		em.getTransaction().begin();
		em.persist(f);
		em.getTransaction().commit();

	}

}
