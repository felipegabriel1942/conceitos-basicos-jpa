package br.com.conversores;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.bean.Setor;
import br.com.util.EntityManagerUtil;

public class ConverterSetor implements Converter, Serializable{
	
	private static final long serialVersionUID = -8409612798819400092L;

	// converte da tela para o objeto
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if(string == null || string.equals("Selecione um setor")) {
			return null;
		}
		return EntityManagerUtil.geEntityManager().find(Setor.class, Integer.parseInt(string));
	}
	
	// converte do objeto para a tela
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object o) {
		if(o == null) {
			return null;
		}
		Setor obj = (Setor) o;
		return obj.getId().toString();
	}

}
