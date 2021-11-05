package obras;

import java.util.ArrayList;

/**
 * TAD DVD: tipo que agrupa la informacion del DVD del fondo bibliografico de una biblioteca, desciende de Multimedia
 * @author Yara Diaz de Cerio Arzamendi
 *
 */

public class DVD extends Multimedia{
	private String director;
	
	/**
	 * Constructora: Inicializa DVD con los datos dados
	 * @param numRegistro numero de registro para DVD
	 * @param signatura signatura DVD
	 * @param titulo titulo DVD
	 * @param productora productora del DVD
	 * @param director del DVD
	 */
	public DVD(int numRegistro, String signatura, String titulo, String productora, String director) {
		super(numRegistro, signatura, titulo, productora);
		this.director = director;
	}
	
	/**
	  * Constructora: Inicializa tipo DVD con los datos pasados en un String
	  * @param datos String que contiene los datos del DVD
	  */
	public DVD(String[] datos) {
		super(datos);
		this.director = datos[5];
	}

	/**
	 * Devuelve un string con la informacion del CD en formato: numRegistro signatura Titulo productora director
	 */
	public String toString() {
		return "DVD " + super.toString() + " " + this.director;
	}
	
	/**
	 * Devuelve ArrayList de String con los datos correspondientes a cada atributo
	 */
	public ArrayList<String> formarLista(){
		ArrayList<String> l = super.formarLista();
		l.set(0, this.getClass().getSimpleName().toUpperCase());
		l.set(4, director);
		return l;
	}

	/**
	 * Devuelve el director
	 * @return
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * Modifica el director cambiandolo por el introducido
	 * @param director director del DVD
	 */
	public void setDirector(String director) {
		this.director = director;
	}
	
	/**
	 * Devuelve ArrayList con 7 valores: Tipo Obra, numero de registro, Signatura, Titulo,
	 * Usuario, Fecha Prestamo, Fecha Devolucion
	 */
	public ArrayList<String> formarListaPrestable() {
		ArrayList<String> a = super.formarListaPrestable();
		
    
		
		a.add(0, this.getClass().getSimpleName().toUpperCase());
		
		
		return a;
	}
	
	
	
}
