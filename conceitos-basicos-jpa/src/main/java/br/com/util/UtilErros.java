package br.com.util;

public class UtilErros {
	
	/**
	 * Metodo captura mensagem de erros para tratamentos
	 * @param e
	 * @return
	 */
	public static String getMensagemErro(Exception e) {
		while(e.getCause() != null) {
			e = (Exception) e.getCause();
		}
		
		String retorno = e.getMessage();
		return retorno;
	}
}
