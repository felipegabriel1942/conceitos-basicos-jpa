package br.com.testes;

import java.util.List;

import br.com.bean.Setor;
import br.com.modelo.DAOSetor;

public class TesteDaoGenerico {

	public static void main(String[] args) {
		DAOSetor<Setor> dao = new DAOSetor<Setor>();
		List<Setor> lista = dao.listar();
		for(Setor o: lista) {
			System.out.println("Codigo: " + o.getId() + " Nome: " + o.getNome());
		}
		

	}

}
