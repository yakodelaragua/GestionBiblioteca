package obras;

import java.util.ArrayList;

import exception.ObraEnPrestamo;
import exception.ObraNoPrestada;

/**
 * Clase abstracta Obra: tipo que agrupa la informaci�n de la obra del fondo
 * bibliogrofico de una biblioteca.
 * 
 * @author Yara Diaz de Cerio
 *
 */
public abstract class Obra {
	private int numRegistro;
	private String signatura, titulo;
	private boolean prestada;

	/**
	 * Constructora b�sica. No se inicializa nada
	 */
	public Obra() {
		// no hay operaciones
	}

	/**
	 * Constructora: Inicializa una Obra con los datos pasados en un String
	 * 
	 * @param datos String que contiene los datos de la obra
	 */
	public Obra(String[] datos) {
		this.numRegistro = Integer.parseInt(datos[1]);
		this.signatura = datos[2];
		this.titulo = datos[3];
	}

	/**
	 * Constructora: Inicializa una Obra con los datos dados y la marca como no
	 * prestada
	 * 
	 * @param numRegistro N�mero de registro �nico para la obra
	 * @param signatura   Signatura de la obra
	 * @param titulo      T�tulo de la obra
	 */

	public Obra(int numRegistro, String signatura, String titulo) {
		this.numRegistro = numRegistro;
		this.signatura = signatura;
		this.titulo = titulo;
		this.prestada = false;
	}

	/**
	 * Constructora: Inicializa una Obra con los datos dados
	 * 
	 * @param numRegistro N�mero de registro �nico para la obra
	 * @param signatura   Signatura de la obra
	 * @param titulo      T�tulo de la obra
	 * @param prestada    Indica si la obra esta prestada o no
	 */

	public Obra(int numRegistro, String signatura, String titulo, boolean prestada) {
		this.numRegistro = numRegistro;
		this.signatura = signatura;
		this.titulo = titulo;
		this.prestada = prestada;
	}

	// Getters y setters
	/**
	 * Devuelve el n�mero de registro de la obra
	 * 
	 * @return el n�mero de registro de la obra
	 */
	public int getNumRegistro() {
		return numRegistro;
	}

	/**
	 * Modifica el n�mero de registro de la obra con numRegistro dado
	 * 
	 * @param numRegistro el n�mero de registro de la Obra
	 */
	public void setNumRegistro(int numRegistro) {
		this.numRegistro = numRegistro;
	}

	/**
	 * Devuelve la signatura de la obra
	 * 
	 * @return la signatura de la obra
	 */
	public String getSignatura() {
		return signatura;
	}

	/**
	 * Modifica la signatura de la obra con signatura dada
	 * 
	 * @param signatura La signatura de la obra
	 */
	public void setSignatura(String signatura) {
		this.signatura = signatura;
	}

	/**
	 * Devuelve el t�tulo de la instancia obra
	 * 
	 * @return El t�tulo de la obra
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Modifica el t�tulo de la obra con el titulo dado
	 * 
	 * @param titulo El t�tulo de la obra
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Indica si la obra esta prestada o no
	 * 
	 * @return true sii la obra esta prestada
	 */

	public boolean estaPrestada() {
		return prestada;
	}

	/**
	 * Modifica si la obra esta prestada o no con el valor de prestada dado
	 * 
	 * @param prestada el valor de pr�stamo de la obra (true-prestada, false-no
	 *                 prestada)
	 */

	public void setPrestada(boolean prestada) {
		if(this instanceof IPrestable) {
			this.prestada = prestada;
		} else {
			System.out.println("Solo se puden prestar los elementos multimedia y los libros ");
		}
		
	}
	// Fin de getters y setters

	
	/**
	 * Devuelve un string con la informaci�n de la obra en formato: OBRA #registro
	 * SIGNATURA Titulo_Obra true/false
	 */

	public String toString() {
		return this.numRegistro + " " + this.signatura + " " + this.titulo.replaceAll(" ", "_");
	}

	/**
	 * Devuelve un string con la informaci�n de la obra en formato: OBRA #registro
	 * SIGNATURA Titulo_Obra true/false
	 */
	/*
	 * public String toString() { return this.numRegistro + " " + this.signatura +
	 * " " + this.titulo.replaceAll(" ", "_"); }
	 * 
	 * /** Imprime por pantalla informaci�n de la obra en formato: numRegistro,
	 * signatura, titulo y si prestada o no
	 */
	public void imprimir() {
		System.out.println(this.numRegistro + " " + this.signatura + "- " + this.titulo);
	}

	/**
	 * Marca la obra como prestada. Muestra un error por pantalla si la obra ya se
	 * encontraba prestada.
	 * 
	 * @throws ObraEnPrestamo si la obra ya est� prestada
	 */

	public void realizarPrestamo() throws ObraEnPrestamo {
		if (this.estaPrestada())
			throw new ObraEnPrestamo("Aviso: La obra " + this.numRegistro + " ya se encuentra prestada");
		else if (this instanceof IPrestable) {
			this.prestada = true;
		} else
			System.out.println("Solo se puden prestar los elementos multimedia y los libros ");
	}

	/**
	 * Marca la obra como no prestada. Muestra un error por pantalla si la obra no
	 * se encontraba prestada.
	 * 
	 * @throws ObraNoPrestada si la Obra no est� prestada
	 */

	public void devolverPrestamo() throws ObraNoPrestada {
		if(this instanceof IPrestable) {
			if (this.estaPrestada()) {
				this.prestada = false;
			} 
			else {
				throw new ObraNoPrestada("Aviso: La obra " + this.numRegistro + " no se encontraba prestada");
			}
		} else {
			System.err.println("Este tipo de obras no se pueden prestar");
		}
		

	}

	/**
	 * Determina si dos obras son iguales por su n�mero de registro
	 */
	public boolean equals(Object otraObra) {
		return numRegistro == ((Obra) otraObra).getNumRegistro();
	}

	/**
	 *  Devuelve ArrayList de String con los datos correspondientes a cada atributo
	 * @return ArrayList con datos de la obra
	 */
	public ArrayList<String> formarLista() {
		ArrayList<String> l = new ArrayList<String>();
		l.add(0, " ");
		l.add(1, Integer.toString(numRegistro));
		l.add(2, this.signatura);
		l.add(3, this.titulo);
		return l;
	}
	
}
