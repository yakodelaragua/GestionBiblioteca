package obras;

import java.util.ArrayList;

/**
 * TAD CD: tipo que agrupa la informacion del CD del fondo bibliografico de una biblioteca.
 * @author Yara Diaz de Cerio Arzamendi
 *
 */

public class CD extends Multimedia {
	private String interprete;
	
	/**
	 * Constructora: Inicializa CD con los datos dados
	 * @param numRegistro numero de registro para CD
	 * @param signatura signatura CD
	 * @param titulo titulo CD
	 * @param productora productora del CD
	 * @param interprete interprete del CD
	 */
	public CD(int numRegistro, String signatura, String titulo, String productora, String interprete) {
		super(numRegistro, signatura, titulo, productora);
		this.interprete = interprete;
	}

	/**
	  * Constructora: Inicializa tipo CD con los datos pasados en un String
	  * @param datos String que contiene los datos del CD
	  */
	public CD(String[] datos) {
		super(datos);
		this.interprete = datos[5];
	}
	
	/**
	 * Devuelve un string con la informacion del CD en formato: numRegistro signatura Titulo productora interprete
	 */
	public String toString() {
		return "CD " + super.toString() + " " + this.interprete;
	}
	
	/**
	 * Devuelve ArrayList de String con los datos correspondientes a cada atributo
	 */
	public ArrayList<String> formarLista(){
		ArrayList<String> l = super.formarLista();
		l.set(0, this.getClass().getSimpleName().toUpperCase());
		l.set(4, this.interprete);
		return l;
	}

	/**
	 * Devuelve el interprete del CD
	 * @return interprete
	 */
	public String getInterprete() {
		return interprete;
	}

	/**
	 * Modifica el interprete cambiandolo por el introducido
	 * @param interprete
	 */
	public void setInterprete(String interprete) {
		this.interprete = interprete;
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
