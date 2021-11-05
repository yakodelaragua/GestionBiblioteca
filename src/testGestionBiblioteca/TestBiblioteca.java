package testGestionBiblioteca;

import biblio.Biblioteca;
import exception.ObraDesconocida;
import exception.ObraEnPrestamo;
import exception.ObraNoPrestada;
import obras.Libro;
import obras.Obra;

public class TestBiblioteca {

	private static Biblioteca biblio = Biblioteca.getInstance();
	
	/**
	 * Realiza la prueba para comprobar el correcto funcionamiento de la operaci�n getSiguienteNumeroRegistro()
	 * @param esperado valor esperado
	 */
	public static void casoGetSiguienteNumeroRegistro(int esperado) {
		System.out.println("Caso para get el ultimo NumReg");
		int resultado = biblio.getSiguienteNumeroRegistro(); //Obtiene el 11
		if (resultado == esperado) {
			System.out.println("PASA: Debe dar "+esperado+" y se obtiene "+ resultado);
		} else {
			System.out.println("ERROR");
			
		}
	}

	
	/**
	 * Realiza las pruebas necesarias para verificar el correcto funcionamiento de la operaci�n catalogarObra(o)
	 * @param o obra que se desea catalogar
	 */
	public static void casoCatalogarObra(Obra o) {
		System.out.println("Caso para catalogar obra");
		int numObrasAntes = biblio.cuantasObras();
		int numRegistro = biblio.getSiguienteNumeroRegistro(); //Obtiene el 12
		biblio.catalogarObra(o); // cataloga con el 13
		try {
			int num=numRegistro + 1;
			Obra obtenida= biblio.obtenObra(num);
			int numObrasDespues = biblio.cuantasObras();
			if (numObrasAntes == numObrasDespues - 1 & obtenida.getNumRegistro()==num & obtenida.getSignatura().equalsIgnoreCase(o.getSignatura())&
					obtenida.getTitulo().equals(o.getTitulo()))
				System.out.println("PASA: Se ha catalogado con n�mero de registro "+ num);
		} catch (ObraDesconocida e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}

	}

	/**
	 * Realiza las pruebas necesarias para comprobar el correcto funcionamiento de la operaci�n descatalogarObra(numRegistro)
	 * @param numRegistro n�mero de registro de la obra a descatalogar
	 * @param saltar true sii debe saltar la excepci�n ObraDesconocida
	 */
	public static void casoDescatalogarObra(int numRegistro, boolean saltar) {
		System.out.println("Caso para descatalogar obras");
		try {
			biblio.descatalogarObra(numRegistro);
			try {
				biblio.obtenObra(numRegistro);
				System.out.println("ERROR");
			} catch (ObraDesconocida e) {
				if (saltar == false) {
					System.out.println("PASA: Ha descatalogado correctamente");
				} else {
					System.out.println("ERROR");
				}
			}
		} catch (ObraDesconocida e) {
			if (saltar = true) {
				System.out.println("PASA: Ha saltado la excepci�n ObraDesconocida para la obra "+numRegistro);
			} else {
				System.out.println("ERROR");
			}
		}
	}

	/**
	 * Realiza la prueba necesaria para comprobar el correcto funcionamiento de la operaci�n cuantasObras()
	 * @param esperado valor esperado
	 */
	public static void casoCuantasObras(int esperado) {
		System.out.println("Caso para cuantas obras");
		int resultado = biblio.cuantasObras();
		if (esperado == resultado) {
			System.out.println("PASA: el n�mero de obras es "+esperado+" y ha obtenido "+resultado);
		} else {
			System.out.println("ERROR: deber�a ser "+ esperado + " y obtiene "+ resultado);
		}
	}

	/**
	 * Realiza las pruebas necesarias para comprobar el correcto funcionamiento de la operaci�n obtenObra(numRegistro)
	 * @param numRegistro numero de registro de la obra a obtener
	 * @param saltarDesconocida true sii debe saltar la excepci�n ObraDesconocida
	 */
	public static void casoObtenerObra(int numRegistro, boolean saltarDesconocida) {
		System.out.println("Caso para obtener obra");
		try {
			Obra obra= biblio.obtenObra(numRegistro);
			if (!saltarDesconocida && obra.getNumRegistro()==numRegistro) {
				System.out.println("PASA: Se ha obtenido la obra de n�mero "+numRegistro); 
			} else {
				System.out.println("ERROR: No obtiene la obra de n�mero indicado");
			}
		} catch (ObraDesconocida e) {
			if (saltarDesconocida ) {
				System.out.println("PASA: Ha saltado la excepci�n ObraDesconocida de n�mero "+ numRegistro);
			} else {
				System.out.println("ERROR");
			}
		}
	}

	/**
	 * Realiza las pruebas necesarias para comprobar el correcto funcionamiento de la operaci�n devolverPrestamo(numRegistro)
	 * @param numRegistro n�mero de registro de la obra a devolver
	 * @param saltaDesconocida true sii debe saltar la excepci�n ObraDesconocida
	 * @param saltaNoPrestada true sii debe saltar la excepci�n ObraNoPrestada
	 */
	public static void casoDevolverPrestamo(int numRegistro, boolean saltaDesconocida, boolean saltaNoPrestada) {
		System.out.println("Caso para devolver pr�stamo");
		Obra o;
		try {
			o = biblio.obtenObra(numRegistro);
			boolean prestadaPrevio= o.estaPrestada();
			biblio.devolverPrestamo(numRegistro);
			if (!saltaDesconocida & !saltaNoPrestada & prestadaPrevio & !o.estaPrestada()) {
				System.out.println("PASA: Obra " + numRegistro + " devuelta correctamente");
			} else {
				System.out.println("ERROR");
			}
		} catch (ObraNoPrestada e) {
			if (!saltaDesconocida & saltaNoPrestada) {
				System.out.println("PASA: La obra " + numRegistro + " no est� prestada");
			} else {
				System.out.println("ERROR");
			}
		} catch (ObraDesconocida e) {
			if (saltaDesconocida) {
				System.out.println("PASA: La obra " + numRegistro + " no est� en el cat�logo");
			} else {
				System.out.println("ERROR");
			}
		}
	}

	/**
	 * Realiza las pruebas necesarias para comprobar el correcto funcionamiento de la operaci�n tomarEnPrestamo(numRegistro, "Juan")
	 * @param numRegistro n�mero de registro de la obra a devolver
	 * @param usuario nombre del usuario que quiere tomar en pr�stamo la obra
	 * @param saltaDesconocida true sii debe saltar la excepci�n ObraDesconocida
	 * @param saltaEnPrestamo true sii debe saltar la excepci�n ObraEnPrestamo
	 */
	public static void casoTomarPrestamo(int numRegistro, String usuario, boolean saltaDesconocida, boolean saltaEnPrestamo) {
		System.out.println("Caso para tomar pr�stamo");
		Obra o;
		try {
			o = biblio.obtenObra(numRegistro);
			boolean prestadaPrevio = o.estaPrestada();
			biblio.tomarEnPrestamo(numRegistro, "Juan");
			if (!saltaDesconocida & !saltaEnPrestamo && !prestadaPrevio & o.estaPrestada()) {
				System.out.println("PASA: Se ha prestado correctamente la obra " + numRegistro);
			} else {
				System.out.println("ERROR: No se deber�a haber prestado");
			}
		} catch (ObraEnPrestamo e) {
			if (!saltaDesconocida & saltaEnPrestamo) {
				System.out.println("PASA: Ha saltado la excepci�n que la obra " + numRegistro + " ya estaba prestada");
			} else {
				System.out.println("ERROR: No deber�a haber saltado la excepci�n de obra prestada");
			}
		} catch (ObraDesconocida e) {
			if (saltaDesconocida) {
				System.out.println("PASA: Ha saltado la excepci�n, la obra " + numRegistro + " no est� en el cat�logo");
			} else {
				System.out.println("ERROR: La obra si est� en el cat�logo");
			}
		}
	}

	/**
	 * Realiza la prueba necesaria para comprobar el correcto funcionamiento de la operaci�n cuantosPrestamos()
	 * @param esperado valor esperado
	 */
	public static void casoCuantosPrestamos(int esperado) {
		System.out.println("Caso para cuantas prestamos");
		int resultado = biblio.cuantosPrestamos();
		if (resultado == esperado) {
			System.out.println("PASA: Hay "+esperado+ " prestamos y hemos obtenido "+resultado);
		} else {
			System.out.println("ERROR: Deber�a dar "+esperado+" y el resultado ha sido "+resultado);
		}
	}

	public static void main(String[] args) throws ObraDesconocida, ObraEnPrestamo {
		System.out.println("** Comprobamos cargar e imprimir visualmente");
		System.out.println("Primero, cargaremos la biblioteca");
		biblio.cargarCatalogoDelFichero();
		System.out.println();
		System.out.println();

		System.out.println("_________________________________________________________________");
		System.out.println("Imprimimos el catalogo de manera ordenada:");
		biblio.imprimirCatalogo();
		System.out.println();
		System.out.println();

		casoGetSiguienteNumeroRegistro(9564); // "catalogoS.txt" tiene el 10 como mayor numRegistro de obras
		System.out.println();
		System.out.println();

		
		Obra o = new Libro(0, "PTx-001 ", "Principios de Pmoo","","","");
		casoCatalogarObra(o);
		System.out.println("_________________________________________________________________");
		System.out.println("Imprimimos el catalogo de manera ordenada:");
		biblio.imprimirCatalogo();
		System.out.println();
		System.out.println();

		System.out.println("** Comprobamos descatalogar");
		System.out.println("Probamos el caso de prueba para descatalogar obras que no salte excepcion");
		casoDescatalogarObra(13, false);
		System.out.println("Probamos el caso de prueba para descatalogar obras que salte excepcion");
		casoDescatalogarObra(13, true);
		System.out.println();
		System.out.println();
		

		System.out.println("** Comprobamos cuantasObras");
		casoCuantasObras(41);
		System.out.println();
		System.out.println();

		System.out.println("** Comprobamos obtenerObra");
		System.out.println("Probamos el caso de prueba obten obras sin que salte excepci�n");
		casoObtenerObra(7, true);
		System.out.println("Probamos el caso de prueba obten obras que salte excepci�n");
		casoObtenerObra(24, true);
		System.out.println();
		System.out.println();
		

		System.out.println("** Comprobamos catalogarObra");
		
		
		Obra o2 = new Libro(1, "MIKE-000", "PMOO_Lab","","","");//da igual el numero de registro ya que el catalogar obra se lo cambia
		biblio.catalogarObra(o2);
		System.out.println("_________________________________________________________________");
		System.out.println("Imprimimos el catalogo de manera ordenada:");
		biblio.imprimirCatalogo();
		System.out.println();
		System.out.println();
		
		biblio.tomarEnPrestamo(1254, "a");
		System.out.println("** Comprobamos devolverPrestamo");
		System.out.println("Devolvemos el prestamo de  una obra  prestado y existente, no deberia de dar error");
		casoDevolverPrestamo(1254, false, false);
		System.out.println();
		System.out.println("Devolvemos el prestamo de  una obra  NO existente, deberia de dar error");
		casoDevolverPrestamo(50, true, false);
		System.out.println();
		System.out.println("Devolvemos el prestamo de  una obra  NO prestado y existente, deberia de dar error");
		casoDevolverPrestamo(1021, false, true);
		System.out.println();
		System.out.println();
		
		
		
		System.out.println("** Comprobamos tomarEnPrestamo");
		System.out.println("Tomamos en pr�stamo una obra que existe y no esta prestada, no deberia de dar error");
		casoTomarPrestamo(2, "Juan", true, false);
		System.out.println();
		System.out.println("Tomamos en pr�stamo  una obra que NO existe, deberia de dar error");
		casoTomarPrestamo(400, "Juan", true, false);
		System.out.println();
		System.out.println("Tomamos en pr�stamo  una obra que existe y esta prestada, deberia de dar error");
		casoTomarPrestamo(2, "Juan", true, true);
		System.out.println();
		System.out.println();
		
		System.out.println("** Comprobamos cuantosPrestamos");
		System.out.println("_________________________________________________________________");
		System.out.println("Imprimimos el catalgo de manera ordenada:");
		biblio.imprimirCatalogo();
		System.out.println();
		System.out.println();
		System.out.println("Ahora probamos cuantos hay en prestamo");
		casoCuantosPrestamos(0);
	}
}
