package biblio;

import javax.swing.JFrame;

import gui.VentanaPrincipal;

/**
 * Clase principal para activar la interfaz gr�fica de la gesti�n de la biblioteca.
 */
public class Principal {
	
	/**
	 * Programa que gestiona el lanzamiento de la aplicaci�n: carga la informaci�n
	 * del fondo bibliogr�fico de la biblioteca y activa los men�s de funcionalidades.
	 * @param args se ejecutar� sin par�metros
	 */
	public static void main(String[] args) {
		Biblioteca lib = Biblioteca.getInstance();
		lib.cargarCatalogoDelFichero();
		VentanaPrincipal leihoNagusia = new VentanaPrincipal();
		leihoNagusia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		leihoNagusia.setVisible(true);
	}
}