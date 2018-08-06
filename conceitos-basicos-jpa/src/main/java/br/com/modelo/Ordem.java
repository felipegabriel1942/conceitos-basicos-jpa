package br.com.modelo;

import java.io.Serializable;

public class Ordem implements Serializable {

	private static final long serialVersionUID = -8800991800533071536L;

	private String rotulo;
	private String atributo;

	public Ordem(String rotulo, String atributo) {
		this.rotulo = rotulo;
		this.atributo = atributo;
	}

	public String getRotulo() {
		return rotulo;
	}

	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	@Override
	public String toString() {
		return rotulo;
	}

}
