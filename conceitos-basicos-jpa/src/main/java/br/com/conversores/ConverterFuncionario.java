package br.com.conversores;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.bean.Funcionario;
import br.com.util.EntityManagerUtil;

public class ConverterFuncionario implements Converter, Serializable {

	private static final long serialVersionUID = 2839171868988421120L;
	
	//Converte da tela para o objeto
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.equals("Selecione um funcionario")) {
			return null;
		}
		return EntityManagerUtil.geEntityManager().find(Funcionario.class, Integer.parseInt(string));
	}
	
	//Converte do objeto para a tela
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object o) {
		if(o == null) {
			return null;
		}
		Funcionario obj = (Funcionario) o;
		return obj.getId().toString();
	}
	

}
