package gui;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import java.util.Date;
import java.awt.Component;
import java.awt.Dimension;

import biblio.Biblioteca;
import exception.ObraDesconocida;
import exception.ObraNoPrestada;
import obras.IPrestable;

/**
 * Representa la ventana que permite gestionar las obras prestables: tomar en pr�stamo y
 * devolver pr�stamo, y guardar cat�logo.
 */
@SuppressWarnings("serial")
public class VentanaGestionPrestables extends JDialog {

	private JTable tabla;
	private JLabel lblListaObras, lblNotaInferior1, lblNotaInferior2, lblNotaInferior3;
	private JButton btnGuardar;
	private JPanel panelBotones = new JPanel();
	private JOptionPane aviso;
	// Crea men� asociado a la tabla (despliegue botones):
	private final JPopupMenu menuTextoTabla = new JPopupMenu();

	private Biblioteca lib = Biblioteca.getInstance();

	/**
	 * Constructora: Creaci�n de la ventana de gesti�n de pr�stamos
	 */
	public VentanaGestionPrestables() {

		setBounds(100, 100, 650, 400);
		getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		lblListaObras = new JLabel("Obras prestables");
		lblListaObras.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(lblListaObras);
		lblListaObras.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.setTitle("Biblioteca - Informacion de obras prestables");

		// Modelo de la tabla de Prestamos:
		ModeloTablaPrestables modelo = new ModeloTablaPrestables(lib.listaDeListasDePrestables());
		this.tabla = new JTable(modelo);

		crearListenerTomarPrestamo();
		crearListenerDevolverPrestamo();
		this.tabla.setComponentPopupMenu(menuTextoTabla);

		// Dimensiones de cada columna de la tabla
		this.getContentPane().add(new JScrollPane(this.tabla));
		setResize();

		this.lblNotaInferior1 = new JLabel(
				"NOTA: Para realizar el prestamo/devolucion de una obra es necesario marcarla,");
		this.getContentPane().add(this.lblNotaInferior1);
		this.lblNotaInferior2 = new JLabel("      pulsar el boton derecho y seleccionar la opcion deseada.");
		this.getContentPane().add(this.lblNotaInferior2);
		this.lblNotaInferior3 = new JLabel("       Para ir a la ventana principal, cierre esta ventana.");
		this.getContentPane().add(this.lblNotaInferior3);

		this.panelBotones.setLayout(new BoxLayout(this.panelBotones, BoxLayout.LINE_AXIS));
		this.panelBotones.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));
		this.panelBotones.add(Box.createHorizontalGlue());

		crearBotonListenerGuardar();

		this.panelBotones.add(Box.createRigidArea(new Dimension(10, 0)));
		this.getContentPane().add(this.panelBotones);
		this.pack();

	}

	/**
	 * Crea men� Tomar en Pr�stamo y su funcionalidad, a�adi�ndola al men� de la tabla
	 */
	private void crearListenerTomarPrestamo() {
		JMenuItem mnuTomarPrestamo = new JMenuItem("Tomar en prestamo");
		mnuTomarPrestamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensaje = "";
				int numRegistro = Integer.parseInt(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
				try {
					if (((IPrestable) lib.obtenObra(numRegistro)).estaPrestada())
						mensaje = "Esta obra esta actualmente prestada. No se puede volver a prestar antes de su devolucion";// Ale
				} catch (ObraDesconocida e1) {
					e1.printStackTrace(); // ***nunca deber�a saltar esta excepci�n ...
				}

				if (!mensaje.equals("")) {
					JOptionPane.showMessageDialog(null, mensaje, "Biblioteca - Prestamo no permitido",
							JOptionPane.OK_OPTION);
				} else {
					crearVentanaNuevoPrestamo();
				}
			}

		});

		menuTextoTabla.add(mnuTomarPrestamo);
	}

	/**
	 * Crea men� Devolver Prestamo y su funcionalidad, a�adi�ndola al men� de la tabla
	 */
	private void crearListenerDevolverPrestamo() {
		JMenuItem mnuDevolverPrestamo = new JMenuItem("Devolver prestamo");
		mnuDevolverPrestamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.YES_OPTION;
				int numRegistro = Integer.parseInt(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
				if (tabla.getModel().getValueAt(tabla.getSelectedRow(), 4).equals("")) {
					// Obra no prestada
					JOptionPane.showMessageDialog(null,
							"La obra de numero de registro " + numRegistro
									+ " no se puede devolver ya que no esta en prestamo.",
							"Biblioteca - Obra no prestada", JOptionPane.OK_OPTION);
				} else {
					respuesta = JOptionPane.showConfirmDialog(null,
							"Se va a proceder a la devolucion de la obra de numero de registro " + numRegistro
									+ ", esta seguro?",
							"Biblioteca - Confirmacion devolucion de obra", JOptionPane.YES_NO_OPTION);

					if (respuesta == JOptionPane.YES_OPTION) {
						try {
							lib.devolverPrestamo(numRegistro);
							refrescarTabla();

							JOptionPane.showMessageDialog(tabla,
									"Se ha devuelto la obra con numero de registro " + numRegistro);
						} catch (ObraDesconocida p) {
							System.out
									.println("No se ha encontrado en la biblioteca una obra con el numero de registro "
											+ numRegistro);
						} catch (ObraNoPrestada e1) {
							System.out.println("La Obra de la biblioteca con el numero de registro " + numRegistro
									+ ", esta ya prestada.");
						}
					}
				}
			}
		});
		menuTextoTabla.add(mnuDevolverPrestamo);
	}

	/**
	 * Crea el bot�n guardar el cat�logo con su funcionalidad incluy�ndolo en el Panel de
	 * Botones.
	 */
	private void crearBotonListenerGuardar() {
		btnGuardar = new JButton("Guardar catalogo");
		panelBotones.add(this.btnGuardar);
		ActionListener actGuardar = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lib.guardarCatalogoEnFichero();
				aviso = new JOptionPane("Se ha guardado en el fichero el catalogo actualizado.",
						JOptionPane.INFORMATION_MESSAGE);
				JDialog venAviso = aviso.createDialog("Biblioteca - Informacion");
				venAviso.setAlwaysOnTop(true);
				venAviso.setVisible(true);
			}
		};
		btnGuardar.addActionListener(actGuardar);
	}

	/**
	 * Crea la Ventana para poder realizar el pr�stamo. Incorpora como datos la fecha del
	 * pr�stamos y la de devoluci�n
	 */
	private void crearVentanaNuevoPrestamo() {
		try {
			Date fActual = lib.fechaPrestamo();
			Date fDevolucion = lib.fechaDevolucion();
			VentanaNuevoPrestamo dlgNuevoPrestamo = // Crea ventana para prestamo con datos
					new VentanaNuevoPrestamo(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString(),
							tabla.getModel().getValueAt(tabla.getSelectedRow(), 2).toString(),
							tabla.getModel().getValueAt(tabla.getSelectedRow(), 3).toString(), fActual, fDevolucion);
			dlgNuevoPrestamo.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
			dlgNuevoPrestamo.setVisible(true);
			dlgNuevoPrestamo.dispose();

			if (dlgNuevoPrestamo.pulsadoGuardar()) {
				lib.realizarPrestamo(dlgNuevoPrestamo.getTxtNumRegistro(), dlgNuevoPrestamo.getTxtUsuario(), fActual,
						fDevolucion);

				refrescarTabla();

				JOptionPane.showMessageDialog(dlgNuevoPrestamo, "Actualizada obra en prestamo.");
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Determina los valores de ancho deseado para cada cumna de la tabla que se visualiza
	 */
	private void setResize() {
		this.tabla.getColumnModel().getColumn(0).setMinWidth(100);
		this.tabla.getColumnModel().getColumn(0).setMaxWidth(100);
		this.tabla.getColumnModel().getColumn(1).setMinWidth(50);
		this.tabla.getColumnModel().getColumn(1).setMaxWidth(50);
		this.tabla.getColumnModel().getColumn(2).setMinWidth(100);
		this.tabla.getColumnModel().getColumn(2).setMaxWidth(100);
		this.tabla.getColumnModel().getColumn(5).setMinWidth(100);
		this.tabla.getColumnModel().getColumn(5).setMaxWidth(100);
		this.tabla.getColumnModel().getColumn(4).setMinWidth(100);
		this.tabla.getColumnModel().getColumn(4).setMaxWidth(100);
	}

	/**
	 * Vuelve a crear la VentanaGestionPrestamos actualizada y eliminando la actual.
	 */
	private void refrescarTabla() {
		tabla.setModel(new ModeloTablaPrestables(lib.listaDeListasDePrestables()));
		setResize();
	}

}
