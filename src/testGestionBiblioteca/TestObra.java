package testGestionBiblioteca;

import java.util.ArrayList;

import exception.ObraEnPrestamo;
import exception.ObraNoPrestada;
import obras.*;


public class TestObra {
	/**
	 * Realiza la prueba necesaria para comprobar el correcto funcionamiento de la operaci�n getNumRegistro()
	 * @param p Obra a comprobar
	 * @param esperado resultado esperado
	 */
	public static void casoNumReg( Obra p, int esperado) {
		int resultado = p.getNumRegistro();
		System.out.println("Caso get para NumReg");
		if (resultado == esperado) {
			System.out.println("PASA");
		} else {
			System.out.println("ERROR");
			System.out.println(resultado);

		}
	}
	
	/**
	 * Realiza la prueba necesaria para comprobar el correcto funcionamiento de la operaci�n getSignatura()
	 * @param p Obra a comprobar
	 * @param esperado resultado esperado
	 */
	public static void casoSignature (Obra p, String esperado) {
		String resultado = p.getSignatura();
		System.out.println("Caso get para signature");
		if (resultado.equals(esperado)) {
			System.out.println("PASA");
		} else {
			System.out.println("ERROR");
		}
	}
	
	/**
	 * Realiza la prueba necesaria para comprobar el correcto funcionamiento de la operaci�n getTitulo()
	 * @param p Obra a comprobar
	 * @param esperado resultado esperado
	 */
	public static void casoTitulo (Obra p, String esperado) {
		String resultado = p.getTitulo();
		System.out.println("Caso get para titulo");
		if (resultado.equals(esperado)) {
			System.out.println("PASA");
		} else {
			System.out.println("ERROR");
		}
	}
	
	/**
	 * Realiza la prueba necesaria para comprobar el correcto funcionamiento de la operaci�n estaPrestada()
	 * @param p Obra a comprobar
	 * @param esperado resultado esperado
	 */
	public static void casoPrestado (Obra p, boolean esperado) {
		boolean resultado = p.estaPrestada();
		System.out.println("Caso get para prestado");
		if (resultado == esperado) {
			System.out.println("PASA");
		} else {
			System.out.println("ERROR");
		}
	}
	
	/**
	 * Realiza las pruebas necesarias para comprobar el correcto funcionamiento de la operaci�n realizarPrestamo()
	 * @param caso n�mero del caso que se est� probando
	 * @param p Obra a comprobar
	 * @param saltarException true sii debe saltar la excepci�n ObraEnPrestamo
	 */
	public static void casoHacerPrestamo (int caso, Obra p, boolean saltarException) {
		System.out.println("Caso "+caso+" para prestar una obra");
		boolean resultado1 = p.estaPrestada();
		try {
			p.realizarPrestamo();
			boolean resultado2 = p.estaPrestada();
			if (resultado1 != resultado2 && !saltarException) {
				System.out.println("PASA: Se ha prestado correctamente la obra");
			} else if(!(p instanceof IPrestable)) {
				System.out.println("PASA: No se puede prestar este tipo de obra");
			}else {
				System.out.println("ERROR: O ya estaba prestada la obra y se ha vuelto a prestar, o no ha saltado la excepci�n");
			} 
		} catch (ObraEnPrestamo e) {
			if (saltarException) {
				System.out.println("PASA: Ha saltado la excepci�n. La obra ya estaba prestada.");
			} else {
				System.out.println("ERROR: No deber�a saltar la excepci�n. La obra no esta prestada");
			}
		}
	}
		
	/**
	 * Realiza las pruebas necesarias para comprobar el correcto funcionamiento de la operaci�n devolverPrestamo()
	 * @param caso n�mero del caso que se est� probando
	 * @param p Obra a comprobar
	 * @param saltarException true sii debe saltar la excepci�n ObraNoPrestada
	 */
	public static void casoDevolverPrestamo (int caso, Obra p, boolean saltarException) {
		System.out.println("Caso " + caso + " para devolver una obra");
		boolean resultado1 = p.estaPrestada();
		try {
			p.devolverPrestamo();
			boolean resultado2 = p.estaPrestada();
			if (resultado1 != resultado2 && !saltarException) {
				System.out.println("PASA: La obra se ha devuelto correctamente");
			} else if(!(p instanceof IPrestable)) {
				System.out.println("PASA: No se puede prestar este tipo de obra");
			} else {
				System.out.println("ERROR: O no estaba prestada la obra y se ha devuelto, o no ha saltado la excepci�n");
			}
		} catch (ObraNoPrestada e) {
			if (saltarException) {
				System.out.println("PASA: Ha saltado la excepcion. La obra no estaba prestada");
			} else {
				System.out.println("ERROR: No deber�a saltar la excepci�n. La obra si esta prestada");
			}
			
		}
	}

	/**
	 * Realiza la prueba necesaria para comprobar el correcto funcionamiento de la operaci�n toString()
	 * @param p Obra a comprobar
	 * @param esperado resultado esperado
	 */
	public static void casoToString (Obra p, String esperado) {
		String resultado = p.toString();
		System.out.println("Caso para toString");
		if (resultado.equals(esperado) ) {
			System.out.println("PASA");
		} else {
			System.out.println("ERROR: el resultado esperado es "+esperado+" y el obtenido ha sido "+resultado);
		}
	}
	
	/**
	 * Realiza la prueba necesaria para comprobar el correcto funcionamiento de la operacin formarLista
	 * @param p Obra a comprobar
	 * @param esperado resultado esperado
	 */
	public static void casoFormarLista(Obra p, ArrayList<String> esperado) {
		ArrayList<String> resultado = p.formarLista();
		System.out.println("Caso formar lista");
		
		if (resultado.equals(esperado) ) {
			System.out.println("PASA");
		} else {
			System.out.println("ERROR: el resultado esperado es " + esperado.toString()+" y el obtenido ha sido " + resultado.toString());
		}
		
	}
	
	/**
	 * Realiza las pruebas necesarias para comprobar el correcto funcionamiuento de la operacion formarListaPrestable()
	 * @param p Obra prestable para comprobar
	 * @param esperado resultado esperado
	 */
	public static void casoFormarListaPrestable(IPrestable p, ArrayList<String> esperado) {
		ArrayList<String> resultado = p.formarListaPrestable();
		System.out.println("Caso formar lista prestable");
		if (resultado.equals(esperado) ) {
			System.out.println("PASA");
		} else {
			System.out.println("ERROR: el resultado esperado es " + esperado.toString()+" y el obtenido ha sido " + resultado.toString());
		}


	}
	
	/**
	 * Realiza la prueba necesaria para comprobar el correcto funcionamiento de la operacin casoAutor
	 * @param p Obra a comprobar
	 * @param esperado resultado esperado
	 */
	public static void casoAutor(Texto p, String esperado) {
		String resultado = p.getAutor();
		System.out.println("Caso get para autor");
		if (resultado.equals(esperado)) {
			System.out.println("PASA");
		} else {
			System.out.println("ERROR");
			System.out.println(resultado);

		}
	}
	
	/**
	 * Realiza la prueba necesaria para comprobar el correcto funcionamiento de la operacin casoVolumenes
	 * @param p Obra a comprobar
	 * @param esperado resultado esperado
	 */
	public static void casoNumVolumenes( Coleccion p, int esperado) {
		int resultado = p.getNumVolumenes();
		System.out.println("Caso get para NumVolumenes");
		if (resultado == esperado) {
			System.out.println("PASA");
		} else {
			System.out.println("ERROR");
			System.out.println(resultado);

		}
	}
	
	/**
	 * Realiza la prueba necesaria para comprobar el correcto funcionamiento de la operacin casoEditorial
	 * @param p Obra a comprobar
	 * @param esperado resultado esperado
	 */
	public static void casoEditorial( Texto p, String esperado) {
		String resultado = p.getEditorial();
		System.out.println("Caso get para editorial");
		if (resultado.equals(esperado)) {
			System.out.println("PASA");
		} else {
			System.out.println("ERROR");
			System.out.println(resultado);

		}
	}
	
	/**
	 * Realiza la prueba necesaria para comprobar el correcto funcionamiento de la operacin casoIdioma
	 * @param p Obra a comprobar
	 * @param esperado resultado esperado
	 */
	public static void casoIdioma(Diccionario p, String esperado) {
		String resultado = p.getIdioma();
		System.out.println("Caso get para Idioma");
		if (resultado == esperado) {
			System.out.println("PASA");
		} else {
			System.out.println("ERROR");
			System.out.println(resultado);

		}
	}
	
	/**
	 * Realiza la prueba necesaria para comprobar el correcto funcionamiento de la operacion getProductora()
	 * @param p Multimedia a comprobar
	 * @param esperado resultado esperado
	 */
	public static void casoProductora(Multimedia p, String esperado) {
		String resultado = p.getProductora();
		System.out.println("Caso get para Productora");
		if (resultado == esperado) {
			System.out.println("PASA");
		} else {
			System.out.println("ERROR");
			System.out.println(resultado);

		}
	}
	
	
	/**
	 * Realiza la prueba necesaria para comprobar el correcto funcionamiento de la operacion getInterpreta()
	 * @param p CD a comprobar
	 * @param esperado resultado esperado
	 */
	public static void casoInterprete(CD p, String esperado) {
		String resultado = p.getInterprete();
		System.out.println("Caso get para Interprete");
		if (resultado == esperado) {
			System.out.println("PASA");
		} else {
			System.out.println("ERROR");
			System.out.println(resultado);

		}
	}
	
	/**
	 * Realiza la prueba necesaria para comprobar el correcto funcionamiento de la operacion getDirector()
	 * @param p DVD a comprobar
	 * @param esperado resultado esperado
	 */
	public static void casoDirector(DVD p, String esperado) {
		String resultado = p.getDirector();
		System.out.println("Caso get para Director");
		if (resultado == esperado) {
			System.out.println("PASA");
		} else {
			System.out.println("ERROR");
			System.out.println(resultado);

		}
	}
	
	
	/**
	 * Realiza la prueba necesaria para comprobar el correcto funcionamiento de la operacion getTematica()
	 * @param p Enciclopedia a comprobar
	 * @param esperado resultado esperado
	 */
	public static void casoTematica(Enciclopedia p, String esperado) {
		String resultado = p.getTematica();
		System.out.println("Caso get para Tematica");
		if (resultado == esperado) {
			System.out.println("PASA");
		} else {
			System.out.println("ERROR");
			System.out.println(resultado);

		}
	}
	
	
	/**
	 * Realiza la prueba necesaria para comprobar el correcto funcionamiento de la operacion getDescripcion()
	 * @param p Libro a comprobar
	 * @param esperado resultado esperado
	 */
	public static void casoDescripcion(Libro p, String esperado) {
		String resultado = p.getDescripcion();
		System.out.println("Caso get para Descripcion");
		if (resultado == esperado) {
			System.out.println("PASA");
		} else {
			System.out.println("ERROR");
			System.out.println(resultado);

		}
	}
	
	/**
	 * Realiza la prueba necesaria para comprobar el correcto funcionamiento de la operacion getNumRevista()
	 * @param p Revista a comprobar
	 * @param esperado resultado esperado
	 */
	public static void casoNumRevista(Revista p, int esperado) {
		int resultado = p.getNumRevista();
		System.out.println("Caso get para NumRevista");
		if (resultado == esperado) {
			System.out.println("PASA");
		} else {
			System.out.println("ERROR");
			System.out.println(resultado);

		}
	}
	
	
	/**
	 * Realiza la prueba necesaria para comprobar el correcto funcionamiento de la operacion getCod()
	 * @param p Revista a comprobar
	 * @param esperado resultado esperado
	 */
	public static void casoCod(Revista p, String esperado) {
		String resultado = p.getCod();
		System.out.println("Caso get para Cod");
		if (resultado == esperado) {
			System.out.println("PASA");
		} else {
			System.out.println("ERROR");
			System.out.println(resultado);

		}
	}
	
	public static void main(String[] args) {
		System.out.println("NOTA: TODO FUNCIONAR� CORRECTAMENTE SI SIEMPRE ESCRIBE PASA PARA CADA CASO");
		System.out.println();
		System.out.println();
		System.out.println("---------------------------PRUEBA REVISTA----------------------------");
		System.out.println("Voy a crear 2 revistas usando las dos constructoras");
		Revista r1 = new Revista(9, "A-001", "izenburua", 185, "985673-A");
		
		System.out.println("Probamos a imprimir el producto creado con la construtora con parametros");
		System.out.print("REVISTA 9 A-001 izenburua 185 985673");
		System.out.println(r1.toString());	
		System.out.println();
		System.out.println("Prueba esta prestada, no deberia de poderse prestar: ");
		r1.setPrestada(false);
		System.out.println();
		System.out.println("Imprimimos la revista creada con la otra constructora: ");
		String linea =  "REVISTA 10 DON2019-001 Donostia_2019 1 981-UU-003";
		String[] lineaPartes = linea.split(" ");
		
		Revista r2 = new Revista(lineaPartes);
		
		System.out.print("REVISTA 10 DON2019-001 Donostia_2019 1 981-UU-003");
		r2.imprimir();
		
		System.out.println();
		System.out.println();
		
		System.out.println("** Comprobamos los getters");
		casoNumReg(r1, 9);
		System.out.println();
		
		casoSignature(r2,"DON2019-001");
		System.out.println();
		
		casoTitulo(r2,"Donostia_2019");
		System.out.println();
		
		casoPrestado(r1,false);
		System.out.println();
		
		casoNumRevista(r1,185);
		System.out.println();
		
		casoCod(r2,"981-UU-003");
		System.out.println();
		System.out.println();
		
		
		
		System.out.println("** Comprobamos hacerPrestamos()");
		casoHacerPrestamo(1,r1, false); //no es posible prestar una revista
		System.out.println();
		
		System.out.println("** Comprobamos devolverPrestamo()");
		casoDevolverPrestamo(1,r1, false); //no es posible prestar una revista
		System.out.println();
		System.out.println();
		
		System.out.println("** Comprobamos toString()");
		casoToString(r2,"REVISTA 10 DON2019-001 Donostia_2019 1 981-UU-003");
		
		System.out.println();
		
		System.out.println();
		
		System.out.println("** Comprobamos formarLista()");
		ArrayList<String> a = new ArrayList<String>();
		a.add("REVISTA");
		a.add("10");
		a.add("DON2019-001");
		a.add("Donostia_2019");
		a.add(" ");
		a.add(" ");
		a.add(" ");
		a.add("Num. Rev.: 1; ISSN: 981-UU-003");
		casoFormarLista(r2, a);
		
		System.out.println();
		System.out.println();
		System.out.println("---------------------------PRUEBA LIBRO----------------------------");
		System.out.println("Voy a crear 2 libros usando las dos constructoras");
		Obra l1 = new Libro(1, "PTx-001", "Printze_Txikia", "Antoine_de_Saint-Exupery", "Txertoa", "Ipuina");
		
		System.out.println("Probamos a imprimir el producto creado con la construtora con parametros");
		System.out.print("1 PTx-001 Printze_Txikia Antoine_de_Saint-Exupery Txertoa Ipuina");
		System.out.println(r1.toString());	
		System.out.println();
		System.out.println("Prueba set prestada");
		l1.setPrestada(false);
		System.out.println();
		
		System.out.println("Imprimimos el libro creada con la otra constructora: ");
		String linea1 =  "LIBRO 2 signa1 nomlibro nomAut Txertoa Ipuina";
		String[] lineaPartes1 = linea1.split(" ");
		
		Libro l2 = new Libro(lineaPartes1);
		
		System.out.print("LIBRO 2 signa1 nomlibro nomAut Txertoa Ipuina");
		System.out.println(l2.toString());
		
		System.out.println();
		System.out.println();
		
		System.out.println("** Comprobamos los getters");
		casoNumReg(l1, 1);
		System.out.println();
		
		casoSignature(l2,"signa1");
		System.out.println();
		
		casoTitulo(l2,"nomlibro");
		System.out.println();
		
		casoAutor(l2, "nomAut");
		System.out.println();
		
		casoEditorial(l2, "Txertoa");
		System.out.println();
		
		casoPrestado(l1,false);
		System.out.println();
		
		casoDescripcion(l2,"Ipuina");
		System.out.println();
		System.out.println();
		
		
		
		System.out.println("** Comprobamos hacerPrestamos()");
		casoHacerPrestamo(1,l1, false); 
		System.out.println();
		casoHacerPrestamo(2,l1, true); 
		System.out.println();
		
		
		System.out.println("** Comprobamos devolverPrestamo()");
		casoDevolverPrestamo(1,l1, false); 
		System.out.println();
		
		System.out.println("** Comprobamos toString()");
		casoToString(l2, "LIBRO 2 signa1 nomlibro nomAut Txertoa Ipuina");
		
		System.out.println();
		
		System.out.println();
		
		System.out.println("** Comprobamos formarLista()");
		ArrayList<String> b = new ArrayList<String>();
		b.add("LIBRO");
		b.add("2");
		b.add("signa1");
		b.add("nomlibro");
		b.add("nomAut");
		b.add("Txertoa");
		b.add("Ipuina");
		b.add(" ");
		casoFormarLista(l2, b);
		
		System.out.println();
		System.out.println();
		
		System.out.println("** Comprobamos formarListaPrestable()");
		ArrayList<String> l = new ArrayList<String>();
		l.add("LIBRO");
		l.add("2");
		l.add("signa1");
		l.add("nomlibro");
		l.add("nomAut");
		l.add("Txertoa");
		l.add("Ipuina");
		l.add(" ");
		
		casoFormarLista(l2, l);

		System.out.println();
		System.out.println();
		System.out.println("---------------------------PRUEBA CD----------------------------");
		System.out.println("Voy a crear 2 CD usando las dos constructoras");
		Obra c1 = new CD(8, "CD-001", "izenburua", "ekoizlea", "interpretea");

		
		System.out.println("Probamos a imprimir el producto creado con la construtora con parametros");
		System.out.print("8 CD-001 izenburua ekoizlea interpretea");
		System.out.println(r1.toString());	
		System.out.println();
		System.out.println("Prueba set prestada");
		c1.setPrestada(false);
		System.out.println();
		
		System.out.println("Imprimimos el CD creada con la otra constructora: ");
		String linea2 =  "CD 1 b c d e";
		String[] lineaPartes2 = linea2.split(" ");
		
		CD c2 = new CD(lineaPartes2);
		
		
		System.out.println(c2.toString());
		
		System.out.println();
		System.out.println();
		
		System.out.println("** Comprobamos los getters");
		casoNumReg(c1, 8);
		System.out.println();
		
		casoSignature(c2,"b");
		System.out.println();
		
		casoTitulo(c2,"c");
		System.out.println();
		
		casoPrestado(c1,false);
		System.out.println();
		System.out.println();
		
		System.out.println("** Comprobamos hacerPrestamos()");
		casoHacerPrestamo(1, c1, false); 
		System.out.println();
		casoHacerPrestamo(2, c1, true); 
		System.out.println();
		
		
		System.out.println("** Comprobamos devolverPrestamo()");
		casoDevolverPrestamo(1,c1, false); 
		System.out.println();
		
		System.out.println("** Comprobamos toString()");
		casoToString(c2, "CD 1 b c d e");
		
		System.out.println();
		
		System.out.println();
		
		System.out.println("** Comprobamos formarLista()");
		ArrayList<String> c = new ArrayList<String>();
		c.add("CD");
		c.add("1");
		c.add("b");
		c.add("c");
		c.add("e");
		c.add("d");
		c.add(" ");
		c.add(" ");

		casoFormarLista(c2, c);

		System.out.println();
		System.out.println();
		
		System.out.println("** Comprobamos formarListaPrestable()");
		ArrayList<String> d = new ArrayList<String>();
		d.add("CD");
		d.add("1");
		d.add("b");
		d.add("c");
		d.add("e");
		d.add("d");
		d.add(" ");
		d.add(" ");
		
		casoFormarLista(c2, d);
		
		System.out.println();
		casoProductora(c2,"d");
		System.out.println();
		
		casoInterprete(c2,"e");
		System.out.println();
		

		
		
		
		System.out.println("---------------------------PRUEBA DVD----------------------------");
		System.out.println("Voy a crear 2 DVDs usando las dos constructoras");
		String[] s = new String[6];
		s[1]= "1";
		s[2]= "a";
		s[3]= "b";
		s[4]= "c";
		s[5]= "d";
		Obra dvd1 = new  DVD(1,"a","b","c","d");
		DVD dvd2 = new DVD(s);
		System.out.println("** Comprobamos los getters");
		casoNumReg(dvd1, 1);
		casoNumReg(dvd2, 1);
		System.out.println();
		
		casoSignature(dvd1,"a");
		casoSignature(dvd2,"a");
		System.out.println();
		
		casoTitulo(dvd1,"b");
		casoTitulo(dvd2,"b");
		System.out.println();
		
		casoDirector(dvd2,"b");
		System.out.println();
		
		
		
		
		casoPrestado(dvd1,false);
		casoPrestado(dvd2,false);
		System.out.println();
		System.out.println();
		
		
		System.out.println("** Comprobamos hacerPrestamos()");
		casoHacerPrestamo(1,dvd1, false); 
		casoHacerPrestamo(1,dvd2, false);
		System.out.println();
		casoHacerPrestamo(2,dvd1, true); 
		casoHacerPrestamo(2,dvd2, true); 
		System.out.println();
		
		
		System.out.println("** Comprobamos devolverPrestamo()");
		casoDevolverPrestamo(1,dvd1, false); 
		casoDevolverPrestamo(2,dvd2, false); 
		System.out.println();
		
		System.out.println("** Comprobamos toString()");
		casoToString(dvd1, "DVD 1 a b c d");
		casoToString(dvd2, "DVD 1 a b c d");
		
		System.out.println();
		
		System.out.println();
		
		System.out.println("** Comprobamos formarLista()");
		ArrayList<String> lDVD = new ArrayList<String>();
		lDVD.add("DVD");
		lDVD.add("1");
		lDVD.add("a");
		lDVD.add("b");
		lDVD.add("d");
		lDVD.add("c");
		lDVD.add(" ");
		lDVD.add(" ");
		
		casoFormarLista(dvd1, lDVD);
		casoFormarLista(dvd2, lDVD);
		
		System.out.println();
		System.out.println();
		System.out.println("---------------------------PRUEBA Enciclopedia----------------------------");
		System.out.println("Voy a crear 2 Enciclopedia usando las dos constructoras");
		Enciclopedia e1 = new Enciclopedia(1, "a", "b", "c", "d", 2, "f");

		
		System.out.println("Probamos a imprimir el producto creado con la construtora con parametros");
		
		System.out.println(e1.toString());	
		System.out.println();
		System.out.println("Prueba set prestada");
		e1.setPrestada(false);
		System.out.println();
		
		System.out.println("Imprimimos el CD creada con la otra constructora: ");
		String linea3 =  "CD 1 b c d e";
		String[] lineaPartes3 = linea3.split(" ");
		
		Obra e2 = new CD(lineaPartes3);
		
		
		System.out.println(e2.toString());
		
		System.out.println();
		System.out.println();
		
		System.out.println("** Comprobamos los getters");
		casoNumReg(e1, 1);
		System.out.println();
		
		casoSignature(e2,"b");
		System.out.println();

		casoTitulo(e2,"c");
		System.out.println();
		
		casoNumVolumenes(e1, 2);
		System.out.println();
		
		casoEditorial(e1, "d");
		System.out.println();
		
		casoPrestado(e1,false);
		System.out.println();
		
		
		casoTematica(e1,"f");
		System.out.println();
		
		System.out.println();
		
		
		System.out.println("** Comprobamos hacerPrestamos()");
		casoHacerPrestamo(1, e1, false); 
		System.out.println();
		casoHacerPrestamo(2, e1, true); 
		System.out.println();
		
		
		System.out.println("** Comprobamos devolverPrestamo()");
		casoDevolverPrestamo(1, e1, false); 
		System.out.println();
		
		System.out.println("** Comprobamos toString()");
		casoToString(e2, "CD 1 b c d e");
		
		System.out.println();
		
		System.out.println();
		
		System.out.println("** Comprobamos formarLista()");
		ArrayList<String> e = new ArrayList<String>();
		e.add("CD");
		e.add("1");
		e.add("b");
		e.add("c");
		e.add("e");
		e.add("d");
		e.add(" ");
		e.add(" ");

		casoFormarLista(e2, e);
		
		System.out.println("---------------------------PRUEBA DICCIONARIO----------------------------");
		System.out.println("Voy a crear 2 DVDs usando las dos constructoras");
		String[] sD = new String[8];
		sD[1]= "1";
		sD[2]= "a";
		sD[3]= "b";
		sD[4]= "c";
		sD[5]= "d";
		sD[6]= "2";
		sD[7]= "e";
		Diccionario d1 = new  Diccionario(1,"a","b","c","d",2,"e");
		Diccionario d2 = new Diccionario(sD);
		System.out.println("** Comprobamos los getters");
		casoNumReg(d1, 1);
		casoNumReg(d2, 1);
		System.out.println();
		
		casoSignature(d1,"a");
		casoSignature(d2,"a");
		System.out.println();
		
		casoTitulo(d1,"b");
		casoTitulo(d2,"b");
		System.out.println();
		
		casoAutor(d1,"c");
		casoAutor(d2,"c");
		System.out.println();
		
		casoEditorial(d1,"d");
		casoEditorial(d2,"d");
		System.out.println();
		
		casoNumVolumenes(d1,2);
		casoNumVolumenes(d2,2);
		System.out.println();
		
		casoIdioma(d1,"e");
		casoIdioma(d2,"e");
		System.out.println();
		
		casoPrestado(d1,false);
		casoPrestado(d2,false);
		System.out.println();
		System.out.println();
		
		System.out.println("** Comprobamos hacerPrestamos()");
		casoHacerPrestamo(1,d1, false); 
		casoHacerPrestamo(1,d2, false);
		System.out.println();
		casoHacerPrestamo(2,d1, true); 
		casoHacerPrestamo(2,d2, true); 
		System.out.println();
		
		
		System.out.println("** Comprobamos devolverPrestamo()");
		casoDevolverPrestamo(1,d1, false); 
		casoDevolverPrestamo(1,d2, false); 
		System.out.println();
		
		System.out.println("** Comprobamos toString()");
		casoToString(d1, "DICCIONARIO 1 a b c d 2 e");
		casoToString(d2, "DICCIONARIO 1 a b c d 2 e");
		
		System.out.println();
		
		System.out.println();
		
		System.out.println("** Comprobamos formarLista()");
		ArrayList<String> lD = new ArrayList<String>();
		lD.add("DICCIONARIO");
		lD.add("1");
		lD.add("a");
		lD.add("b");
		lD.add("c");
		lD.add("d");
		lD.add(" ");
		lD.add("Num. Vol. 2; Idioma: e");
		
		
		casoFormarLista(d1, lD);
		casoFormarLista(d2, lD);
		
	}
}
