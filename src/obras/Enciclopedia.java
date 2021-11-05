package obras;

import java.util.ArrayList;

/**
 * TAD Enciclopedia: tipo que agrupa la informacion del Enciclopedia del fondo bibliografico de una biblioteca, desciende de Coleccion
 * @author Yara Diaz de Cerio Arzamendi
 *
 */

public class Enciclopedia extends Coleccion {
	private String tematica;
	
	/**
	  * Constructora: Inicializa Enciclopedia con los datos dados
	 * @param numRegistro numero de registro para la Enciclopedia
	 * @param signatura signatura de la Enciclopedia
	 * @param titulo titulo de la Enciclopedia
	 * @param autor autor de la Enciclopedia
	 * @param editorial editorial de la Enciclopedia
	 * @param numVolumenes numero de volumenes de la Enciclopedia
	 * @param tematica tematica de la Enciclopedia
	 */
	public Enciclopedia(int numRegistro, String signatura, String titulo, String autor, String editorial, int numVolumenes, String tematica) {
		 super(numRegistro, signatura, titulo, autor, editorial, numVolumenes);
		 this.tematica = tematica;
	}

	/**
	  * Constructora: Inicializa tipo Enciclopedia con los datos pasados en un String
	  * @param datos String que contiene los datos de la Enciclopedia
	  */
	public Enciclopedia(String[] datos) {
		super(datos);
		this.tematica = datos[7];
	}
	
	/**
	 * Devuelve un string con la informacion de la Enciclopedia en formato: numRegistro signatura Titulo autor editorial numeroVolumenes idioma
	 */
	public String toString() {
		return "ENCICLOPEDIA " + super.toString() + " " + this.tematica;
	}
	
	/**
	 * Devuelve ArrayList de String con los datos correspondientes a cada atributo
	 */
	public ArrayList<String> formarLista(){
		ArrayList<String> l = super.formarLista();
		l.set(0, this.getClass().getSimpleName().toUpperCase());
		String a = l.get(7);
		l.set(7, a + "Tema: " + this.tematica);
		return l;
	}

	/**
	 * Devuelve la tematica de la enciclopedia
	 * @return tematica
	 */
	public String getTematica() {
		return tematica;
	}

	/**
	 * Modifica la tematica cambiandola por la introducida
	 * @param tematica
	 */
	public void setTematica(String tematica) {
		this.tematica = tematica;
	}
	
	
}
