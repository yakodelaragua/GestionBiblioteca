package exception;

/**
 * Excepci�n definida para controlar aspectos de devoluci�n cuando la obra no est� prestada
  */
@SuppressWarnings("serial")
public class ObraNoPrestada extends Exception {
	public ObraNoPrestada(String men) {
		super(men);
	}
}
