package br.com.modelo;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.bean.Grupo;
import br.com.util.EntityManagerUtil;
import br.com.util.UtilErros;
import br.com.util.UtilMensagens;

public class GrupoDAO {

	/**
	 * Filtros -> filtrar as consultas e qual o parametro de filtragem
	 * 
	 */

	private EntityManager em;
	private String ordem = "id";
	private String filtro = "";
	private Integer maximoObjetos = 2;
	private Integer posicaoAtual = 0;
	private Integer totalObjetos = 0;

	// Esse construtor inicia a conexão com o banco ao instanciar a classe
	public GrupoDAO() {
		em = EntityManagerUtil.geEntityManager();
	}

	// Consulta com filtro
	@SuppressWarnings("unchecked")
	public List<Grupo> listar() {
		String where = "";
		if (filtro.length() > 0) {
			if (ordem.equals("id")) {
				try {
					Integer.parseInt(filtro);
					where = " where " + ordem + " = " + filtro + " ";
				} catch (Exception e) {

				}
			} else {
				where = " where upper(" + ordem + ") like '" + filtro.toUpperCase() + "%' ";
			}
		}

		String jpql = "from Grupo " + where + " order by " + ordem;
		totalObjetos = em.createQuery("Select id from Grupo " + where + " order by " + ordem).getResultList().size();
		return em.createQuery(jpql).setFirstResult(posicaoAtual).setMaxResults(maximoObjetos).getResultList();
	}

	// Metodos de paginação
	public void primeiro() {
		posicaoAtual = 0;
	}

	public void anterior() {
		posicaoAtual -= maximoObjetos;
		if (posicaoAtual < 0) {
			posicaoAtual = 0;
		}
	}

	public void proximo() {
		if (posicaoAtual + maximoObjetos < totalObjetos) {
			posicaoAtual += maximoObjetos;
		}
	}

	public void ultimo() {
		int resto = totalObjetos % maximoObjetos;
		if (resto > 0) {
			posicaoAtual = totalObjetos - resto;
		} else {
			posicaoAtual = totalObjetos - maximoObjetos;
		}
	}

	public String getMensagemNavegacao() {
		int ate = posicaoAtual + maximoObjetos;
		if (ate > totalObjetos) {
			ate = totalObjetos;
		}
		return "Listando de " + (posicaoAtual + 1) + " até " + ate + " de " + totalObjetos + " registros";
	}

	@SuppressWarnings("unchecked")
	public List<Grupo> listarTodos() {
		return em.createQuery("from Grupo order by nome").getResultList();
	}

	// Salvar
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

	// Excluir
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

	public String getOrdem() {
		return ordem;
	}

	public void setOrdem(String ordem) {
		this.ordem = ordem;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public Integer getMaximoObjetos() {
		return maximoObjetos;
	}

	public void setMaximoObjetos(Integer maximoObjetos) {
		this.maximoObjetos = maximoObjetos;
	}

	public Integer getPosicaoAtual() {
		return posicaoAtual;
	}

	public void setPosicaoAtual(Integer posicaoAtual) {
		this.posicaoAtual = posicaoAtual;
	}

	public Integer getTotalObjetos() {
		return totalObjetos;
	}

	public void setTotalObjetos(Integer totalObjetos) {
		this.totalObjetos = totalObjetos;
	}

}
