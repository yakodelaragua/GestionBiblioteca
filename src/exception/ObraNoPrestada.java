package exception;

/**
 * Excepción definida para controlar aspectos de devolución cuando la obra no está prestada
  */
@SuppressWarnings("serial")
public class ObraNoPrestada extends Exception {
	public ObraNoPrestada(String men) {
		super(men);
	}
}
