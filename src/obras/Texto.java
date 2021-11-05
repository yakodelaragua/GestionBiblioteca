package obras;

import java.util.ArrayList;

/**
 * Texto: tipo abstracto que agrupa la informacion del Texto del fondo bibliografico de una biblioteca, desciende de Obra
 * @author Yara Diaz de Cerio Arzamendi
 *
 */

public abstract class Texto extends Obra {
	private String autor;
	private String editorial;
	
	/**
	 * Constructora: Inicializa texto con los datos dados
	 * @param numRegistro numero de registro para el texto
	 * @param signatura signatura del texto
	 * @param titulo titulo del texto
	 * @param autor autor del texto
	 * @param editorial editorial del texto
	 */
	public Texto(int numRegistro, String signatura, String titulo, String autor, String editorial) {
		 super(numRegistro, signatura, titulo);
		 this.autor = autor;
		 this.editorial = editorial;
	}

	/**
	  * Constructora: Inicializa tipo Texto con los datos pasados en un String
	  * @param datos String que contiene los datos del Texto
	  */
	public Texto(String[] datos) {
		super(datos);
		this.autor = datos[4];
		this.editorial = datos[5];
	}

	/**
	 * Devuelve un string con la informacion del Texto en formato: numRegistro signatura Titulo autor editorial
	 */
	public String toString() {
		return super.toString() + " " + this.autor + " " + this.editorial;
	}
	
	/**
	 * Devuelve ArrayList de String con los datos correspondientes a cada atributo
	 */
	public ArrayList<String> formarLista(){
		ArrayList<String> l = super.formarLista();
		l.add(4, autor);
		l.add(5, editorial);
		return l;
	}

	/**
	 * Devuelce el autor del texto
	 * @return autor
	 */
	public String getAutor() {
		return autor;
	}

	/**
	 * Modifica el autor cambiandolo por el introducido
	 * @param autor
	 */
	public void setAutor(String autor) {
		this.autor = autor;
	}

	/**
	 * Devuelve la editorial
	 * @return editorial
	 */
	public String getEditorial() {
		return editorial;
	}

	/**
	 * Modifica la editorial del libro
	 * @param editorial
	 */
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	
	
	
}
