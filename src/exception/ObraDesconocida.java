package exception;

/**
 * Excepci�n definida para controlar adecuadamente la gesti�n de una obra que no existe en el cat�logo de la Biblioteca.
 */
@SuppressWarnings("serial")
public class ObraDesconocida extends Exception {
	public ObraDesconocida(String men) {
		super(men);
	}
}
