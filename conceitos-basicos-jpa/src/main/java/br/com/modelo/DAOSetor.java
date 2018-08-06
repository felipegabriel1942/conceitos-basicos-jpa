package br.com.modelo;

import java.io.Serializable;

import br.com.bean.Setor;
import br.com.conversores.ConverterOrdem;
import br.com.util.EntityManagerUtil;

public class DAOSetor<T> extends GenericDAO<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public DAOSetor() {
		super.setClasse(Setor.class);
		super.setEm(EntityManagerUtil.geEntityManager());
		super.getListaOrdem().add(new Ordem("Código","id"));
		super.getListaOrdem().add(new Ordem("Nome","nome"));
		super.setOrdemAtual ((Ordem) this.getListaOrdem().get(1));
		super.setFiltro("");
		super.setMaximoObjetos(2);
		super.setConverterOrdem(new ConverterOrdem(super.getListaOrdem()));
	}
}
