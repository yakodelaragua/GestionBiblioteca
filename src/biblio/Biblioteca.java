package biblio;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import exception.ObraDesconocida;
import exception.ObraEnPrestamo;
import exception.ObraNoPrestada;
import obras.CD;
import obras.DVD;
import obras.Diccionario;
import obras.Enciclopedia;
import obras.IPrestable;
import obras.Libro;
import obras.Obra;
import obras.Revista;

/**
 * Singleton Biblioteca: Realiza la gesti�n completa de los fondos de la
 * biblioteca.
 * 
 * @author Julen Solla (Adaptaci�n de su propuesta)
 *
 */
public class Biblioteca {
	private final String NOM_FICHERO_BIBLIOTECA = "CATALOGO.txt"; // Nombre del fichero donde se guarda la informacion
																	// de las obras
	private final String NOM_FICHERO_PRESTAMOS = "obrasPrestadas.txt"; // Nombre del fichero donde se guarda el informe
																		// de las obras prestadas
	private Scanner es;
	private ArrayList<Obra> catalogo = new ArrayList<>();
	private int maxRegistro = 0; // Guardamos el numero de registro mas alto de la biblioteca. Lo incrementamos
									// para cada nuevo registro
	private ArrayList<Prestamo> prestamos = new ArrayList<>(); // Contiene losprestamos activos de las Obras

	private static Biblioteca instanciaBiblio; // (1) Caracter�stica del Singleton: Atributo estatico para nuestra unica
												// instancia de Biblioteca

	/**
	 * Constructora privada
	 */
	private Biblioteca() {// (2) Caracter�stica del Singleton: Constructora privada para gestionarla
							// internamente.
	}

	/**
	 * Devuelve la instancia unica de la Biblioteca
	 * 
	 * @return La instancia unica de la Biblioteca
	 */
	public static Biblioteca getInstance() { // (3) Caracter�stica del Singleton: m�todo est�tico que devuelve la unica
												// instancia de la clase
		if (instanciaBiblio == null)
			instanciaBiblio = new Biblioteca(); // Solo se inicializa una vez
		return instanciaBiblio;
	}

	/**
	 * A�ade la nuevaObra al cat�logo en orden creciente del n�mero de registro
	 * 
	 * @param nuevaObra Obra a insertar
	 */
	private void insertarOrdenado(Obra nuevaObra) {
		int i = 0;
		while (i < catalogo.size() && catalogo.get(i).getNumRegistro() < nuevaObra.getNumRegistro()) {
			i++;
		}
		catalogo.add(i, nuevaObra);
	}

	/**
	 * Carga, en orden creciente de n�mero de registro, el cat�logo con las obras
	 * contenidas en el fichero catalogo.txt.
	 */
	public void cargarCatalogoDelFichero() {
		boolean hayFichero = false;
		System.out.println("Cargando obras de la biblioteca ...");
		while (!hayFichero) {// Se repite el bucle hasta que el usuario se
								// asegure de que el fichero catalogo.txt est� en la carpeta del proyecto
			try {
				es = new Scanner(new FileReader(NOM_FICHERO_BIBLIOTECA));
				String linea;
				catalogo.clear(); // Incializamos/vaciamos los ArrayList
				prestamos.clear();
				while (es.hasNextLine()) {
					linea = es.nextLine(); // Lee una l�nea completa del fichero
					System.out.println(linea);
					String[] lineaPartes = linea.split(" ");
					// los valores siguientes corresponden a: Integer.parseInt(lineaPartes[1])=>
					// c�digo de registro (int), lineaPartes[2]=> signatura,
					// lineaPartes[3]=> t�tulo,Boolean.parseBoolean(lineaPartes[4]) => si est� o no
					// prestada (boolean); si esta prestada lineaPartes[5]=>
					// usuario, lineaPartes[6]=>fechaPrestamo, lineaPartes[7]=>fechaDevolucion
					// insertarOrdenado(new Obra(Integer.parseInt(lineaPartes[1]), lineaPartes[2],
					// lineaPartes[3], Boolean.parseBoolean(lineaPartes[4])));

					if (lineaPartes[0].equals("REVISTA")) {
						insertarOrdenado(new Revista(lineaPartes));
					} else if (lineaPartes[0].equals("DICCIONARIO")) {
						insertarOrdenado(new Diccionario(lineaPartes));
					} else if (lineaPartes[0].equals("ENCICLOPEDIA")) {
						insertarOrdenado(new Enciclopedia(lineaPartes));
					} else if (lineaPartes[0].equals("LIBRO")) {
						insertarOrdenado(new Libro(lineaPartes));
					} else if (lineaPartes[0].equals("DVD")) {
						insertarOrdenado(new DVD(lineaPartes));
					} else if (lineaPartes[0].equals("CD")) {
						insertarOrdenado(new CD(lineaPartes));
					} else {
						System.out.println(linea);
					}

					if (Boolean.parseBoolean(lineaPartes[4]) && lineaPartes.length >= 8) {
						prestamos.add(new Prestamo(Integer.parseInt(lineaPartes[1]), lineaPartes[5], lineaPartes[6],
								lineaPartes[7]));
					}
				}
				maxRegistro = catalogo.get(catalogo.size() - 1).getNumRegistro(); // obtiene el n�mero de registro m�s
																					// alto del cat�logo

				System.out.println("... se ha cargado el catalogo de obras del fichero " +  NOM_FICHERO_BIBLIOTECA);
				hayFichero = true;
				es.close();
			} catch (FileNotFoundException e) {
				System.out.println(
						"... no se ha encontrado el fichero.\nPor favor deja el fichero " + NOM_FICHERO_BIBLIOTECA
								+ " en la carpeta raiz del proyecto\nPulsa INTRO para volverlo a intentar:");
				try {
					System.in.read(); // Intenta leer un INTRO por teclado
				} catch (Exception ei) {
					// No se hace nada
				}
			}
		}
	}

	/**
	 * Guarda las obras del cat�logo en el fichero catalogo.txt. Una obra por l�nea.
	 */
	public void guardarCatalogoEnFichero() {
		System.out.println("Guardando el catalogo ...");
		FileWriter fw;
		try {
			fw = new FileWriter(NOM_FICHERO_BIBLIOTECA);

			for (int i = 0; i < catalogo.size(); i++) {
				Obra obr = catalogo.get(i);
				fw.write(obr.toString());
				
				if(obr instanceof IPrestable) {
					if (obr.estaPrestada()) { // Si hay prestamo tambien guardamos
											// los datos
						try {
							fw.write(" " + prestamos.get(obtenerPosPrestamo(obr.getNumRegistro())).toString());
						} catch (ObraNoPrestada e1) {
							System.out.println("No hay datos del prestamo para la obra " + obr.getNumRegistro());
						}
					}
				}
				
				
				fw.write("\n");
			}
			fw.close();
			System.out.println("... obras guardas en el fichero");
			System.out.println();
		} catch (IOException e) {
			System.out.println("... no se han podido guardar las obras en el fichero");
			System.out.println();
			e.printStackTrace();
		}
	}

	/**
	 * Indica cu�ntas obras est�n registradas en el cat�logo
	 * 
	 * @return el numero de obras registradas en el cat�logo
	 */
	public int cuantasObras() {
		return catalogo.size();
	}

	/**
	 * Devuelve el siguiente numero de registro para catalogar una nueva obra.
	 * 
	 * @return el siguiente numero de registro mas alto del catalogo
	 */
	public int getSiguienteNumeroRegistro() {
		return maxRegistro++; // Se incrementa y luego se devuelve su valor
	}

	/**
	 * Devuelve el indice correspondiente a la posicion de la obra en el catalogo
	 * seg�n el registro dado
	 * 
	 * @param registro el n�mero de registro de la obra a buscar
	 * @return el indice correspondiente a la posicion de la obra de registro dado
	 *         en el catalogo
	 * @throws ObraDesconocida si no est� la obra con ese registro en el cat�logo
	 */
	private int obtenerPosObra(int registro) throws ObraDesconocida {
		int i = 0;
		while (i < catalogo.size() && catalogo.get(i).getNumRegistro() < registro)
			i++;
		if (i < catalogo.size() && catalogo.get(i).getNumRegistro() == registro) // Se ha encontrado la obra con ese
																					// registro
			return i;
		else
			throw new ObraDesconocida("No hay en el catalogo ninguna obra con el n�mero de registro: " + registro);
	}

	/**
	 * Devuelve el indice correspondiente a la posicion de un prestamo
	 * 
	 * @param registro el numero de registro de la obra
	 * @return el indice correspondiente a la posicion del prestamo en la lista de
	 *         pr�stamos
	 * @throws ObraDesconocida si esa obra no est� prestada
	 */
	private int obtenerPosPrestamo(int registro) throws ObraNoPrestada {
		int i = 0;
		while (i < prestamos.size() && registro != prestamos.get(i).getNumRegistro())
			i++;
		if (i < prestamos.size() && registro == prestamos.get(i).getNumRegistro()) // Se ha encontrado el prestamo de la
																					// obra registro
			return i;
		else
			throw new ObraNoPrestada("La obra " + registro + " no esta prestada");// puede ser
	}

	/**
	 * Inserta una nueva obra en el cat�logo d�ndole un n�mero de registro mayor de
	 * todos y no prestada
	 * 
	 * @param nuevaObra obra a incluir en el catalogo
	 */
	public void catalogarObra(Obra nuevaObra) {
		nuevaObra.setNumRegistro(getSiguienteNumeroRegistro());
		nuevaObra.setPrestada(false);
		catalogo.add(nuevaObra); // Insertamos la bueva obra al final
	}

	/**
	 * Elimina la obra de n�mero de registro dado del cat�logo
	 * 
	 * @param registro el numero de registro de la obra a eliminar
	 * @throws ObraDesconocida si la obra de numero de registro dado no est� en el
	 *                         cat�logo
	 */
	public void descatalogarObra(int registro) throws ObraDesconocida {
		catalogo.remove(obtenerPosObra(registro));
		System.out.println("Obra " + registro + " eliminada correctamente del catalogo.");
	}

	/**
	 * Devuelve la Obra con el numero de registro dado
	 * 
	 * @param registro el n�mero de registro de la obra a obtener
	 * @return la obradel cat�logo con numero de registro dado
	 * @throws ObraDesconocida si la obra de numero de registro dado no est� en el
	 *                         cat�logo
	 */
	public Obra obtenObra(int registro) throws ObraDesconocida {
		return catalogo.get(obtenerPosObra(registro));
	}

	/**
	 * Muestra por pantalla las obras registradas en el cat�logo ordenadas
	 * ascendentemente por n�mero de registro.
	 */
	public void imprimirCatalogo() {
		System.out.println("\n\t\tCATALOGO\n\tReg | Sig. |  Titulo,  �prestado?\n"
				+ "---------------------------------------------------");
		for (int i = 0; i < cuantasObras(); i++) {
			System.out.print(i + ":\t");
			catalogo.get(i).imprimir();
		}
	}

	/**
	 * Devuelve la fecha de hoy para un nuevo pr�stamo
	 * 
	 * @return la fecha de hoy
	 */
	public Date fechaPrestamo() {
		return new Date();
	}

	/**
	 * Devuelve la fecha de devoluci�n para un nuevo pr�stamo
	 * 
	 * @return la fecha de creaci�n del pr�stamo + 12 dias
	 */
	public Date fechaDevolucion() {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(new Date()); // hoy
		calendario.add(Calendar.DATE, 12); // 12 d�as m�s
		return calendario.getTime();
	}

	/**
	 * Crea un nuevo pr�stamo para la Obra con el n�mero de registro dado
	 * 
	 * @param numRegistro el n�mero de registro de la Obra a prestar
	 * @param usuario     el nombre del usuario que solicita el pr�stamo
	 * @throws ObraDesconocida si la obra de n�mero de registro dado no est� en el
	 *                         cat�logo
	 * @throws ObraEnPrestamo  si la obra de n�mero de registro dado ya est�
	 *                         prestada
	 */
	public void tomarEnPrestamo(int numRegistro, String usuario) throws ObraDesconocida, ObraEnPrestamo {
		Obra obra = obtenObra(numRegistro); // puede elevar excepci�n ObraDesconocida que no se trata y se sigue
											// elevando
		if (obra.estaPrestada())
			throw new ObraEnPrestamo("La obra " + numRegistro + " ya esta prestada");
		obra.setPrestada(true);
		Prestamo prest = new Prestamo(numRegistro, usuario, fechaPrestamo(), fechaDevolucion());
		prestamos.add(prest);
		System.out.println("Prestamo creado para la obra " + numRegistro);
	}
	
	/**
	 * Crea un nuevo prestamo para la obra con el numero de registro, usuario, fecha prestamo y fecha de devolucion dados
	 * @param numRegistro numero de registro
	 * @param usuario usuario
	 * @param fechaPrestamo fecha de prestamo
	 * @param fechaDevolucion fecha de devolucionn
	 * @throws ObraDesconocida
	 * @throws ObraEnPrestamo
	 */
	public void realizarPrestamo(int numRegistro, String usuario, Date fechaPrestamo, Date fechaDevolucion) throws ObraDesconocida, ObraEnPrestamo {
		Obra obra = obtenObra(numRegistro);
		
		if (obra.estaPrestada()) {
			throw new ObraEnPrestamo("La obra " + numRegistro + " ya esta prestada");
		}
		
		if(obra instanceof IPrestable) {
			obra.setPrestada(true);
			Prestamo prest = new Prestamo(numRegistro, usuario, fechaPrestamo, fechaDevolucion);
			prestamos.add(prest);
			System.out.println("Prestamo creado para la obra " + numRegistro);
		} else {
			System.err.println("Solo se puden prestar los elementos multimedia y los libros ");
		}
	}

	/**
	 * Elimina el pr�stamo para la obra con el n�mero de registro dado
	 * 
	 * @param numRegistro el n�mero de registro de la obra a devolver
	 * @throws ObraDesconocida si la obra de n�mero de registro dado no est� en el
	 *                         cat�logo
	 * @throws ObraNoPrestada  si la obra de n�mero de registro dado no est�
	 *                         prestada
	 */
	public void devolverPrestamo(int numRegistro) throws ObraDesconocida, ObraNoPrestada {
		Obra obra = obtenObra(numRegistro); // puede elevar excepci�n ObraDesconocida que no se trata y se sigue
											// elevando
		if (obra.estaPrestada()) { // Nos aseguramos que est� prestada
			prestamos.remove(obtenerPosPrestamo(numRegistro));
			obra.setPrestada(false);
			System.out.println("Obra " + numRegistro + " devuelta correctamente");
		} else {
			throw new ObraNoPrestada("La obra " + numRegistro + " no esta prestada");
		}
	}

	/**
	 * Devuelve el n�mero de pr�stamos actuales
	 * 
	 * @return el n�mero de pr�stamos actuales
	 */
	public int cuantosPrestamos() {
		return prestamos.size();
	}

	/**
	 * Genera en el fichero "obrasPrestadas.txt" la informaci�n de las obras que se
	 * encuentran prestadas actualmente
	 */
	public void generarInformePrestados() {
		System.out.println("Generando informe de prestamos ...");
		FileWriter fw;
		try {
			fw = new FileWriter(NOM_FICHERO_PRESTAMOS);

			fw.write("------ Biblioteca: Lista de obras en prestamo ------\n\n" + "Num.Reg.\t Signatura\t\tTitulo\n"
					+ "--------\t----------\t-----------------------\n");
			for (Prestamo p : prestamos) {
				try {
					Obra obra = obtenObra(p.getNumRegistro());
					fw.write("\t" + obra.getNumRegistro() + "\t\t" + obra.getSignatura() + "\t\t" + obra.getTitulo()
							+ "\n");
				} catch (ObraDesconocida e) {
					System.out.println(e.getMessage());
				}
			}
			fw.close();

			System.out.println("... informe guardado en el fichero\n");
		} catch (IOException e) {
			System.out.println("... no se ha podido guardar el informe en el fichero\n");
			e.printStackTrace();
		}
	}

	
	/**
	 * Devuelve la informacion de cada Obra de la biblioteca en formato ArrayList
	 * 
	 * @return lista de con toda su informacion de las obras
	 */
	public ArrayList<ArrayList<String>> listaDeListasDeObras() {
		ArrayList<ArrayList<String>> listaObras = new ArrayList<ArrayList<String>>();

		for (int i = 0; i < catalogo.size(); i++) {
			listaObras.add(catalogo.get(i).formarLista());
		}

		return listaObras;
	}

	/**
	 * Devuelve la informacion de cada Obra prestable de la biblioteca en formato
	 * ArrayList
	 * 
	 * @return lista con toda la informacion de las obras prestables
	 */
	public ArrayList<ArrayList<String>> listaDeListasDePrestables() {
		ArrayList<ArrayList<String>> listaPrestables = new ArrayList<ArrayList<String>>();
		
		for (Obra o: catalogo) {
			  if(o instanceof IPrestable) {
				  IPrestable p = (IPrestable)o;
				  
				  boolean b = false;
			
				  for(Prestamo pr: prestamos) {
					  if(o.getNumRegistro() == pr.getNumRegistro()) {
						  b = true;
						  listaPrestables.add(pr.completarListaPrestamo(p.formarListaPrestable()));
						  listaPrestables.toString();
					  } 
				  }
				 
				 if(b == false) {
					 listaPrestables.add(p.formarListaPrestable());
				 } else b = false;
			}
		  }
		  
		return listaPrestables;
	}

}
