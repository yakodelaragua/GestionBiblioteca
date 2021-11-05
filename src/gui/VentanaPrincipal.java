package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import biblio.Biblioteca;

/**
 * Representa la ventana principal de la aplicaci�n
 */
@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {
	private final String GESTION_CATALOGO = "GESTIONAR Catalogo de Obras";
	private final String GESTION_PRESTAMOS= "      GESTIONAR Prestamos        ";
	private final String GUARDAR_CATALOGO  = "            Guardar cambios             ";
	private final String SALIR           = "     SALIR DE LA APLICACION     ";

	// Atributos que caraterizan la ventana principal
	private JLabel etiquetaCabecera; // nombre de la ventana
	private JButton btnGestionObras, btnGuardar, btnGestionPrestamos, btnSalir; // botones de funciionalidad de la aplicaci�n
	private JOptionPane aviso; // panel de ventana-aviso revision catalogo
	private JDialog ventanAviso; // ventana-aviso

	// Men� principal
	private JMenu menuPrincipal = new JMenu("Menu"); //texto del bot�n men�

	 // Atributos que caracterizan los items del men�
	private JMenuItem mnuGestionObras = new JMenuItem(GESTION_CATALOGO);
	private JMenuItem mnuGestionPrestamos =   new JMenuItem(GESTION_PRESTAMOS);
	private JMenuItem mnuGuardar =     new JMenuItem(GUARDAR_CATALOGO);
	private JMenuItem mnuSalir =        new JMenuItem(SALIR);

	private Biblioteca bib = Biblioteca.getInstance(); //Para su su uso por cualquier m�todo de la clase
	
	

	/**
	 * Constructora: Crea la ventana principal con un men� y botones.
	 */
	public VentanaPrincipal() {

		this.setTitle("Biblioteca - Ventana principal");
		this.getContentPane().setLayout(
				new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		etiquetaCabecera = new JLabel ("Biblioteca");
		etiquetaCabecera.setFont(new Font ("Arial", 1, 16));
		etiquetaCabecera.setAlignmentX(CENTER_ALIGNMENT);
		this.add(etiquetaCabecera);


		menuPrincipal.add(mnuGestionObras);
		menuPrincipal.addSeparator();
		menuPrincipal.add(mnuGestionPrestamos);
		menuPrincipal.addSeparator();
		menuPrincipal.add(mnuGuardar);
		menuPrincipal.addSeparator();
		menuPrincipal.add(mnuSalir);

		// A�ade un men� de barra, le a�ade el Men� creado y se lo asocia a la ventana
		JMenuBar menuBarra = new JMenuBar();
		menuBarra.add(menuPrincipal);
		this.setJMenuBar(menuBarra);

		// Crea y a�ade Botones de men� a la ventana
		btnGestionObras = new JButton(GESTION_CATALOGO);
		btnGestionPrestamos =   new JButton(GESTION_PRESTAMOS);
		btnGuardar =     new JButton(GUARDAR_CATALOGO);
		btnSalir =        new JButton(SALIR);
		this.add(btnGestionObras);
		this.add(btnGestionPrestamos);
		this.add(btnGuardar);
		this.add(btnSalir);

		this.pack();

		// A�ade todos los listener asociados a men�s y botones.
		crearListenerGestionarObras();
		crearListenerGestionarPrestamos();
		crearListenerGuardar();
		crearListenerSalir();

	}

	/**
	 * Crea el listener asociado a la gesti�n de obras. Lo a�ade al men� y al bot�n
	 */
	private void crearListenerGestionarObras() {
		ActionListener actGestionObras = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					crearVentanaGestionObras();
			}
		};
		mnuGestionObras.addActionListener(actGestionObras);
		btnGestionObras.addActionListener(actGestionObras);
	}
	
	/**
	 * Crea el listener asociado a la gesti�n de pr�stamos. Lo a�ade al men� y al bot�n
	 */
	private void crearListenerGestionarPrestamos() {
		ActionListener actGestionPrestamos = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearVentanaGestionPrestables();			
				}
		};
		mnuGestionPrestamos.addActionListener(actGestionPrestamos);
		btnGestionPrestamos.addActionListener(actGestionPrestamos);
	}

	/**
	 * Crea el listener asociado al bot�n de guardar cat�logo
	 */
	private void crearListenerGuardar() {
		ActionListener actGuardar = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					bib.guardarCatalogoEnFichero();
					aviso = new JOptionPane("Se ha guardado el catalogo actualizado en el fichero", 
							JOptionPane.INFORMATION_MESSAGE);
					JDialog oharLeihoa = aviso.createDialog("Biblioteca - Informacion");
					oharLeihoa.setAlwaysOnTop(true);
					oharLeihoa.setVisible(true);
			}
		};
		mnuGuardar.addActionListener(actGuardar);
		btnGuardar.addActionListener(actGuardar);
	}

	/**
	 * Crea el listener asociado al bot�n Salir de la aplicaci�n
	 */
	private void crearListenerSalir() {
		ActionListener actSalir = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aviso = new JOptionPane("Adios, hasta la proxima",
						JOptionPane.INFORMATION_MESSAGE);
				ventanAviso = aviso.createDialog("Biblioteca - Despedida");
				ventanAviso.setAlwaysOnTop(true);
				ventanAviso.setVisible(true);
				System.exit(0);
			}
		};
		mnuSalir.addActionListener(actSalir);
		btnSalir.addActionListener(actSalir);
	}

	/**
	 * Crea la ventana de Gesti�n Obras con su tabla de obras.
	 * NOTA: necesaria p�blica para que desde otra ventana pueden volver a generarla cuando se producen cambios.
	 */
	public void crearVentanaGestionObras() {
		try {
			VentanaGestionObras katLeihoa = new VentanaGestionObras();
			katLeihoa.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			katLeihoa.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
			katLeihoa.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea la ventana de Gesti�n de Pr�stables con su tabla de prestables.
	 * NOTA: necesaria p�blica para que desde otra ventana pueden volver a generarla cuando se producen cambios.
	 */
	public void crearVentanaGestionPrestables() {
			try {
				VentanaGestionPrestables maileguZerrendaLeihoa = new VentanaGestionPrestables();
				maileguZerrendaLeihoa.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				maileguZerrendaLeihoa.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
				maileguZerrendaLeihoa.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
