package br.ufc.trabalho_final.criptografia;

import java.util.Base64;

public class Criptografia {

	public static String codifica(String msg) {
	        return Base64.getEncoder().encodeToString(msg.getBytes());
	    }
	
	public static String decodifica(String msg){
		byte[] decoded = Base64.getDecoder().decode(msg);
		return new String(decoded);
	}

}
