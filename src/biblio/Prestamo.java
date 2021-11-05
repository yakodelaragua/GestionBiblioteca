package biblio;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * TAD Prestamo: contiene la informaci�n de un pr�stamo: el n�mero de registro
 * de la obra en pr�stamo, el nombre del usuario, y las fechas de pr�stamo y
 * devoluci�n.
 * 
 * @author Julen Solla (Breve adaptaci�n de su propuesta)
 *
 */
public class Prestamo {
	private int numRegistro;
	private String usuario;
	private Date fechaPrestamo, fechaDevolucion; // fechaDevolucion sera fechaPrestamo + 12 dias
	private Format df = new SimpleDateFormat("yyyy-MM-dd"); // Lo usamos para formatear las fechas en los String

	/**
	 * Constructora: Inicializa el pr�stamo con n�mero de registro 0 y sin datos
	 */
	public Prestamo() {
		this.numRegistro = 0;
		this.usuario = null;
		this.fechaPrestamo = null;
		this.fechaDevolucion = null;
	}

	/**
	 * Constructora: Inicializa el pr�stamo con los valores proporcionados
	 * 
	 * @param numRegistro     numero del registro de la obra en prestamo
	 * @param usuario         nombre del usuario que lo tiene en prestamo
	 * @param fechaPrestamo   dia en el que se hace el prestamo
	 * @param fechaDevolucion fecha m�xima en la que se debe realizar la devolcui�n:
	 *                        fechaPrestamo + 12 dias
	 */
	public Prestamo(int numRegistro, String usuario, Date fechaPrestamo, Date fechaDevolucion) {
		this.numRegistro = numRegistro;
		this.usuario = usuario;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
	}

	/**
	 * Constructora: Inicializa el pr�stamo con los valores proporcionados
	 * 
	 * @param numRegistro     numero del registro de la obra en prestamo
	 * @param usuario         nombre del usuario que lo tiene en prestamo
	 * @param fechaPrestamo   dia en el que se hace el prestamo. En
	 *                        formato(yyyy-MM-dd)
	 * @param fechaDevolucion fecha m�xima en la que se debe realizar la devolcui�n:
	 *                        fechaPrestamo + 12 dias. En formato (yyyy-MM-dd)
	 */
	public Prestamo(int numRegistro, String usuario, String fechaPrestamo, String fechaDevolucion) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.numRegistro = numRegistro;
		this.usuario = usuario;
		try {
			this.fechaPrestamo = dateFormat.parse(fechaPrestamo);
		} catch (ParseException e) {
			System.out.println("Ha habido un problema al convertir la fecha " + fechaPrestamo);
		}
		try {
			this.fechaDevolucion = dateFormat.parse(fechaDevolucion);
		} catch (ParseException e) {
			System.out.println("Ha habido un problema al convertir la fecha " + fechaDevolucion);
		}
	}

	// Getters y setters
	/**
	 * Obtiene el n�mero de registro de la obra en pr�stamo
	 * 
	 * @return n�mero de registro de la obra en pr�stamo
	 */
	public int getNumRegistro() {
		return numRegistro;
	}

	/**
	 * Modifica el n�mero de registro de la obra en pr�stamo
	 * 
	 * @param numRegistro numero de registro de la obra a prestar
	 */
	public void setNumRegistro(int numRegistro) {
		this.numRegistro = numRegistro;
	}

	/**
	 * Obtiene el nombre de usuario del pr�stamo
	 * 
	 * @return el nombre de usuario del pr�stamo
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Modifica el nombre de usuario del pr�stamo
	 * 
	 * @param usuario nombre de usuario que quiere hacer el pr�stamo
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Obtiene la fecha de realizacion del pr�stamo
	 * 
	 * @return la fecha de realizacion del pr�stamo
	 */
	public Date getFechaPrestamo() {
		return fechaPrestamo;
	}

	/**
	 * Modifica la fecha de realizaci�n del pr�stamo
	 * 
	 * @param fechaPrestamo fecha de realizacion del pr�stamo
	 */
	public void setFechaPrestamo(Date fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	/**
	 * Obtiene la fecha de devolucion del pr�stamo
	 * 
	 * @return la fecha de devolucion del pr�stamo
	 */
	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}

	/**
	 * Modifica la fecha de devolucion del pr�stamo
	 * 
	 * @param fechaDevolucion la fecha de devolucion del pr�stamo (12 d�as posterior
	 *                        a la del pr�stamo)
	 */
	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
	// Fin de getters y setters

	/**
	 * Devuelve un String con los valores de usuario, fechaPrestamo y
	 * fechaDevolucion separados por espacios
	 */
	public String toString() {
		return this.usuario + " " + df.format(this.fechaPrestamo) + " " + df.format(this.fechaDevolucion);
	}

	/**
	 * Imprime por pantalla los valores de los campos numRegistro, usuario,
	 * fechaPrestamo (yyyy-MM-dd) y fechaDevolucion (yyyy-MM-dd)
	 */
	public void imprimir() {
		System.out.println(this.numRegistro + " prestado a " + this.usuario + ", fecha del prestamo: "
				+ df.format(this.fechaPrestamo) + "; fecha de devolucion: " + df.format(this.fechaDevolucion));
	}

	/**
	 * Determina si dos prestamos son iguales por el n�mero de registro de la obra
	 * prestada
	 */
	public boolean equals(Object otroPrestamo) {
		return numRegistro == ((Prestamo) otroPrestamo).getNumRegistro();
	}

	/**
	 * Dado ArrayList con 4 valores: Tipo, numero de registro, signatura y titulo
	 * Devuelve ArrayList con esos 4 anadiendo usuario, fecha de prestamo y fecha de
	 * devolucion
	 */
	public ArrayList<String> completarListaPrestamo(ArrayList<String> lista) {
		lista.set(4, getUsuario());
		lista.set(5, getFechaPrestamo().toString());
		lista.set(6, getFechaDevolucion().toString());

		return lista;
	}

}
