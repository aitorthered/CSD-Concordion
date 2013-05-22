package com.example;

public class ServiceLetter {

	public String getLetter(String dNI) {
		int dni = Integer.parseInt(dNI);
		String juegoCaracteres = "TRWAGMYFPDXBNJZSQVHLCKET";
		int modulo = dni % 23;
		char letra = juegoCaracteres.charAt(modulo);
		return String.valueOf(letra);
	}
}
