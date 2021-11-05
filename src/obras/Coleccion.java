package obras;

import java.util.ArrayList;

/**
 * Coleccion: tipo abstracto que agrupa la informacion de la coleccion del fondo bibliografico de una biblioteca, desciende de Texto.
 * @author Yara Diaz de Cerio Arzamendi
 *
 */
public abstract class Coleccion extends Texto {
	private int numVolumenes;
	
	/**
	 * Constructora: Inicializa Coleccion con los datos dados
	 * @param numRegistro numero de registro para la Coleccion
	 * @param signatura signatura de la Coleccion
	 * @param titulo titulo de la Coleccion
	 * @param autor autor de la Coleccion
	 * @param editorial editorial de la Coleccion
	 * @param numVolumenes numero de volumenes de la Coleccion
	 */
	public Coleccion(int numRegistro, String signatura, String titulo, String autor, String editorial, int numVolumenes) {
		 super(numRegistro, signatura, titulo, autor, editorial);
		 this.numVolumenes = numVolumenes;
	}
	
	/**
	  * Constructora: Inicializa tipo Coleccion con los datos pasados en un String
	  * @param datos String que contiene los datos de la coleccion
	  */
	public Coleccion(String[] datos) {
		super(datos);
		this.numVolumenes = Integer.parseInt(datos[6]);
	}

	/**
	 * Devuelve un string con la informacion de la Coleccion en formato: numRegistro signatura Titulo autor editorial numeroVolumenes
	 */
	public String toString() {
		return super.toString() + " " +  this.numVolumenes;
	}
	
	/**
	 * Devuelve ArrayList de String con los datos correspondientes a cada atributo
	 */
	public ArrayList<String> formarLista(){
		ArrayList<String> l = super.formarLista();
		l.add(6, " ");
		l.add(7, "Num. Vol. " + Integer.toString(this.numVolumenes) + "; ");
		return l;
	}

	/**
	 * Devuelve el numero de volumenes de la coleccion
	 * @return numero de volumenes
	 */
	public int getNumVolumenes() {
		return numVolumenes;
	}

	/**
	 * Modifica el valor del numero de volumenes por el dado
	 * @param numVolumenes numero de volumenes
	 */
	public void setNumVolumenes(int numVolumenes) {
		this.numVolumenes = numVolumenes;
	}
	
	
	
}
