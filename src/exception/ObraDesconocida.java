package exception;

/**
 * Excepción definida para controlar adecuadamente la gestión de una obra que no existe en el catálogo de la Biblioteca.
 */
@SuppressWarnings("serial")
public class ObraDesconocida extends Exception {
	public ObraDesconocida(String men) {
		super(men);
	}
}
