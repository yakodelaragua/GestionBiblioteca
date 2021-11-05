package exception;

/**
 * Excepción definida para controlar aspectos para tomar en préstamo una obra cuando esta ya está prestada
 */
@SuppressWarnings("serial")
public class ObraEnPrestamo extends Exception {
	public ObraEnPrestamo(String men) {
		super(men);
	}
}
