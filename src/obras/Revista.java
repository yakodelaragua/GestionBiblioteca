package obras;

import java.util.ArrayList;

/**
 * TAD Revista: tipo que agrupa la informacion de las revistas del fondo bibliografico de una biblioteca, desciende de Obra
 * @author Yara Diaz de Cerio Arzamendi
 *
 */
public class Revista extends Obra {
	 private int numRevista;
	 private String cod;
	 
	 /**
	  * Constructora: Inicializa una revista con los datos dados
	  * @param numRegistro numero de registro para la revista
	  * @param signatura signatura de la revista
	  * @param titulo titulo de la revista
	  * @param numRevista numero de la revista
	  * @param cod Codigo ISSN de la revista
	  */
	 public Revista(int numRegistro, String signatura, String titulo, int numRevista, String cod) {
		 super(numRegistro, signatura, titulo);
		 this.numRevista = numRevista;
		 this.cod = cod;
	 }
	 
	 /**
	  * Constructora: Inicializa una Revista con los datos pasados en un String
	  * @param datos String que contiene los datos de la revista
	  */
	 public Revista(String[] datos) {
		 super(datos);
		 this.numRevista = Integer.parseInt(datos[4]);
		 this.cod = datos[5];
	 }

		 /**
		 * Devuelve un string con la informacion de la revista en formato: REVISTA numRegistro signatura Titulo numRevista ISSN
		 */
	public String toString() {
		return this.getClass().getSimpleName().toUpperCase() + " " + super.toString() + " " + this.numRevista + " " + this.cod;
	}
	
	/**
	 * Devuelve ArrayList de String con los datos correspondientes a cada atributo
	 */
	public ArrayList<String> formarLista(){
		ArrayList<String> l = super.formarLista();
		l.set(0, this.getClass().getSimpleName().toUpperCase());
		l.add(4, " ");
		l.add(5, " ");
		l.add(6, " ");
		l.add(7, "Num. Rev.: " + Integer.toString(numRevista) + "; ISSN: " + cod);
		return l;
	}

	/**
	 * Devuelve el numero de revista
	 * @return numero de revista
	 */
	public int getNumRevista() {
		return numRevista;
	}

	/**
	 * Modifica el numero de la revista cambiandolo por el introducido
	 * @param numRevista
	 */
	public void setNumRevista(int numRevista) {
		this.numRevista = numRevista;
	}

	/**
	 * Devuelve el codigo de la revista
	 * @return codigo
	 */
	public String getCod() {
		return cod;
	}

	/**
	 * Modifica el codigo de la revista cambiandolo por el introducido
	 * @param cod codigo de revista
	 */
	public void setCod(String cod) {
		this.cod = cod;
	}
	
	
	 
}
