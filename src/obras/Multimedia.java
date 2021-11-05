package obras;

import java.util.ArrayList;

/**
 * Multimedia: tipo abstracto que agrupa la informacion de los Multimedia del
 * fondo bibliografico de una biblioteca, desciende de Obra
 * 
 * @author Yara Diaz de Cerio Arzamendi
 *
 */
public abstract class Multimedia extends Obra implements IPrestable {
	private String productora;

	/**
	 * Constructora: Inicializa Multimedia con los datos dados
	 * 
	 * @param numRegistro numero de registro para Multimedia
	 * @param signatura   signatura Multimedia
	 * @param titulo      titulo Multimedia
	 * @param productora  productora del Multimedia
	 */
	public Multimedia(int numRegistro, String signatura, String titulo, String productora) {
		super(numRegistro, signatura, titulo);
		this.productora = productora;
	}

	/**
	 * Constructora: Inicializa tipo Multimedia con los datos pasados en un String
	 * 
	 * @param datos String que contiene los datos del Multimedia
	 */
	public Multimedia(String[] datos) {
		super(datos);
		this.productora = datos[4];
	}

	/**
	 * Devuelve un string con la informacion del Multimedia en formato: numRegistro
	 * signatura Titulo productora
	 */
	public String toString() {
		return super.toString() + " " + this.productora;
	}

	/**
	 * Devuelve ArrayList de String con los datos correspondientes a cada atributo
	 */
	public ArrayList<String> formarLista() {
		ArrayList<String> l = super.formarLista();
		l.add(4, " ");
		l.add(5, this.productora);
		l.add(6, " ");
		l.add(7, " ");
		return l;
	}

	/**
	 * Devuelve la productora del Multimedia
	 * 
	 * @return productora
	 */
	public String getProductora() {
		return productora;
	}

	/**
	 * Modifica la productora cambiandola por el String introducido
	 * 
	 * @param productora
	 */
	public void setProductora(String productora) {
		this.productora = productora;
	}

	/**
	 * Devuelve ArrayList con 7 valores: Tipo Obra, numero de registro, Signatura,
	 * Titulo, Usuario, Fecha Prestamo, Fecha Devolucion, ultimos 3 en blanco
	 */

	public ArrayList<String> formarListaPrestable() {
		ArrayList<String> a = new ArrayList<>();

		a.add(String.valueOf(this.getNumRegistro()));
		a.add(getSignatura());
		a.add(getTitulo());
		a.add(" ");
		a.add(" ");
		a.add(" ");

		return a;
	}

}
