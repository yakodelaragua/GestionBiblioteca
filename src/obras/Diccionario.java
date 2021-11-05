package obras;

import java.util.ArrayList;

/**
 * TAD Diccionario: tipo que agrupa la informacion del Diccionario del fondo bibliografico de una biblioteca, desciende de Coleccion
 * @author Yara Diaz de Cerio Arzamendi
 *
 */
public class Diccionario extends Coleccion{
	private String idioma;
	
	/**
	  * Constructora: Inicializa Diccionario con los datos dados
	 * @param numRegistro numero de registro para el Diccionario
	 * @param signatura signatura del Diccionario
	 * @param titulo titulo del Diccionario
	 * @param autor autor del Diccionario
	 * @param editorial editorial del Diccionario
	 * @param numVolumenes numero de volumenes del Diccionario
	 * @param idioma idioma del diccionario
	 */
	public Diccionario(int numRegistro, String signatura, String titulo, String autor, String editorial, int numVolumenes, String idioma) {
		 super(numRegistro, signatura, titulo, autor, editorial, numVolumenes);
		 this.idioma = idioma;
	}

	/**
	  * Constructora: Inicializa tipo Diccionario con los datos pasados en un String
	  * @param datos String que contiene los datos del Diccionario
	  */
	public Diccionario(String[] datos) {
		super(datos);
		this.idioma = datos[7];
	}
	
	/**
	 * Devuelve un string con la informacion del Diccionario en formato: numRegistro signatura Titulo autor editorial numeroVolumenes idioma
	 */
	public String toString() {
		return "DICCIONARIO " + super.toString() + " " + this.idioma;
	}
	
	/**
	 * Devuelve ArrayList de String con los datos correspondientes a cada atributo
	 */
	public ArrayList<String> formarLista(){
		ArrayList<String> l = super.formarLista();
		l.set(0, this.getClass().getSimpleName().toUpperCase());
		String a = l.get(7);
		l.set(7, a + "Idioma: " + this.idioma);
		
		return l;
	}

	/**
	 * Devuelve el idioma del diccionario
	 * @return idioma
	 */
	public String getIdioma() {
		return idioma;
	}

	/**
	 * Modifica el idioma cambiandolo por el introducido
	 * @param idioma idioma del Diccionario
	 */
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
	
}
