package exception;

/**
 * Excepci�n definida para controlar aspectos para tomar en pr�stamo una obra cuando esta ya est� prestada
 */
@SuppressWarnings("serial")
public class ObraEnPrestamo extends Exception {
	public ObraEnPrestamo(String men) {
		super(men);
	}
}
