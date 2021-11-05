package testGestionBiblioteca;

import java.util.ArrayList;
import java.util.Date;

import biblio.Prestamo;

public class TestPrestamo {
	
	/**
	 * Realiza la prueba necesaria para comprobar el correcto funcionamiento de la operacin casoUsuario
	 * @param p Prestamo a comprobar
	 * @param esperado resultado esperado
	 */
	public static void casoUsuario(Prestamo p, String esperado) {
		String resultado = p.getUsuario();
		System.out.println("Caso get para usuario");
		if (resultado.equals(esperado)) {
			System.out.println("PASA");
		} else {
			System.out.println("ERROR");
			System.out.println(resultado);

		}
	}
	
	/**
	 * Realiza la prueba necesaria para comprobar el correcto funcionamiento de la operacin casoFechaPrestamo
	 * @param p Prestamo a comprobar
	 * @param esperado resultado esperado
	 */
	public static void casoFechaPrestamo(Prestamo p, Date esperado) {
		Date resultado = p.getFechaPrestamo();
		System.out.println("Caso get para FechaPrestamo");
		if (resultado.equals(esperado)) {
			System.out.println("PASA");
		} else {
			System.out.println("ERROR");
			System.out.println(resultado);

		}
	}
	
	/**
	 * Realiza la prueba necesaria para comprobar el correcto funcionamiento de la operacin casoFechaDevolucion
	 * @param p Prestamo a comprobar
	 * @param esperado resultado esperado
	 */
	public static void casoFechaDevolucion(Prestamo p, Date esperado) {
		Date resultado = p.getFechaPrestamo();
		System.out.println("Caso get para FechaPrestamo");
		if (resultado.equals(esperado)) {
			System.out.println("PASA");
		} else {
			System.out.println("ERROR");
			System.out.println(resultado);

		}
	}
	
	/**
	 * Realiza la prueba necesaria para comprobar el correcto funcionamiento de la operacion toString()
	 * @param p Prestamo a comprobar
	 * @param esperado resultado esperado
	 */
	public static void casoToString (Prestamo p, String esperado) {
		String resultado = p.toString();
		System.out.println("Caso para toString");
		if (resultado.equals(esperado) ) {
			System.out.println("PASA");
		} else {
			System.out.println("ERROR: el resultado esperado es " + esperado + " y el obtenido ha sido " + resultado);
		}
	}
	
	/**
	 * Realiza las pruebas necesarias para comprobar el correcto funcionamiuento de la operacion completarListaPrestamo()
	 * @param p Obra prestable para comprobar
	 * @param esperado resultado esperado
	 */
	public static void casoCompletarListaPrestamo(Prestamo pr, ArrayList<String> p, ArrayList<String> esperado) {
		ArrayList<String> resultado = pr.completarListaPrestamo(p) ;
		
		System.out.println("Caso formar lista prestable");
		if (resultado.equals(esperado) ) {
			System.out.println("PASA");
		} else {
			System.out.println("ERROR: el resultado esperado es " + esperado.toString()+" y el obtenido ha sido " + resultado.toString());
		}


	}
	

	public static void main(String[] args) {
		System.out.println("Constructora vacia: ");
		Prestamo p = new Prestamo();
		Date d = new Date();
		d.getTime();
		Date d2 = new Date();
		d2.getTime();
		p.setFechaDevolucion(d2);
		p.setFechaPrestamo(d);
		p.setUsuario("a");
		casoToString(p, "a 2019-05-16 2019-05-16");
		System.out.println();
		
		System.out.println("Constructora con parametros de fecha: ");
		
		Prestamo p2 = new Prestamo(2, "a", d,  d2);
		casoToString(p2, "a 2019-05-16 2019-05-16");
		
		System.out.println();
		System.out.println("Constructora con parametros de fecha en String: ");
		String s = "2019-05-08";
		String s2 = "2019-05-12";
		d2.getTime();
		Prestamo p3 = new Prestamo(2, "a", s,  s2);
		casoToString(p3, "a 2019-05-08 2019-05-12");
		
		
		System.out.println();
		System.out.println("** Comprobamos los getters");
		casoUsuario(p2, "a");
		System.out.println();
		
		casoFechaPrestamo(p2, d);
		System.out.println();
		
		casoFechaDevolucion(p2, d2);
		System.out.println();
		
		ArrayList<String> a = new ArrayList<>();
		a.add("a");
		a.add("b");
		a.add("c");
		a.add("d");
		a.add(" ");
		a.add(" ");
		a.add(" ");
		
		ArrayList<String> esp = new ArrayList<>();
		esp.add("a");
		esp.add("b");
		esp.add("c");
		esp.add("d");
		esp.add("a");
		esp.add("Wed May 08 00:00:00 CEST 2019");
		esp.add("Sun May 12 00:00:00 CEST 2019");
		
		System.out.println("Otras: ");
		casoCompletarListaPrestamo(p3, a, esp);
		
		
		
		
		
	}
}
