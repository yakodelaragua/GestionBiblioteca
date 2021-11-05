package obras;

import java.util.ArrayList;


/**
 * TAD Libro: tipo que agrupa la informacion del Libro del fondo bibliografico de una biblioteca, desciende de Texto
 * @author Yara Diaz de Cerio Arzamendi
 *
 */
public class Libro extends Texto implements IPrestable{
	String descripcion;
	
	/**
	 * Constructora: Inicializa Libro con los datos dados
	 * @param numRegistro numero de registro para el Libro
	 * @param signatura signatura del Libro
	 * @param titulo titulo del Libro
	 * @param autor autor del Libro
	 * @param editorial editorial del Libro
	 * @param descripcion descripcion del Libro
	 */
	public Libro(int numRegistro, String signatura, String titulo, String autor, String editorial, String descripcion) {
		 super(numRegistro, signatura, titulo, autor, editorial);
		 this.descripcion = descripcion;
	}

	/**
	  * Constructora: Inicializa tipo Libro con los datos pasados en un String
	  * @param datos String que contiene los datos del Libro
	  */
	public Libro(String[] datos) {
		super(datos);
		this.descripcion = datos[6];
	}
	
	/**
	 * Devuelve un string con la informacion del Libro en formato: numRegistro signatura Titulo autor editorial descripcion
	 */
	public String toString() {
		return this.getClass().getSimpleName().toUpperCase() + " " + super.toString() + " "  + descripcion;
	}
	
	/**
	 * Devuelve ArrayList de String con los datos correspondientes a cada atributo
	 */
	public ArrayList<String> formarLista(){
		ArrayList<String> l = super.formarLista();
		l.set(0, this.getClass().getSimpleName().toUpperCase());
		l.add(6, descripcion);
		l.add(7, " ");
		return l;
	}

	/**
	 * Devuelve la descripcion del libro
	 * @return descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Modifica la descripcion del Libro con el String dado
	 * @param descripcion descripcion del libro
	 */
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

	/**
	 * Devuelve ArrayList con 7 valores: Tipo Obra, numero de registro, Signatura, Titulo,
	 * Usuario, Fecha Prestamo, Fecha Devolucion
	 */

	public ArrayList<String> formarListaPrestable() {
		ArrayList<String> a = new ArrayList<>();
		
		a.add(this.getClass().getSimpleName().toUpperCase());
		a.add(String.valueOf(this.getNumRegistro()));
		a.add(getSignatura());
		a.add(getTitulo());
		a.add(" ");
		a.add(" ");
		a.add(" ");
		
		return a;
	}

	
	
	
	
}
