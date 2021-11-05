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

import java.awt.Component;
import java.awt.Dimension;

import biblio.Biblioteca;
import exception.ObraDesconocida;
import exception.ObraNoPrestada;
import obras.*;

/**
 * Representa la ventana que permite gestionar las obras: catalogar y descatalogar obra, y
 * guardar cat�logo.
 */
@SuppressWarnings("serial")
public class VentanaGestionObras extends JDialog {

	private JTable tabla; // Las obras del cat�logo se visualizaran en una tabla
	private JLabel lblCatalogo, lblNotaInferior; // etiquetas
	private JButton btnNuevaObra, btnGuardar; // botones
	private JPanel panelBotones = new JPanel();
	private JOptionPane aviso;
	private JMenuItem mnuEliminarObra;

	private final JPopupMenu menuTextoTabla = new JPopupMenu();

	private ModeloTablaObras modelo;
	private Biblioteca bib = Biblioteca.getInstance();

	/**
	 * Constructora: Crea la ventana para la gesti�n del cat�logo de obras
	 */
	public VentanaGestionObras() {
		this.setTitle("Biblioteca - Gestion del catalogo ");
		this.setBounds(100, 100, 800, 400);
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		lblCatalogo = new JLabel("Catalogo de la Biblioteca");
		lblCatalogo.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(lblCatalogo);
		lblCatalogo.setFont(new Font("Tahoma", Font.BOLD, 14));

		modelo = new ModeloTablaObras(bib.listaDeListasDeObras());
		this.tabla = new JTable(modelo);

		crearBotonListenerEliminarObra();

		this.tabla.setComponentPopupMenu(menuTextoTabla);
		this.getContentPane().add(new JScrollPane(this.tabla));

		setResize();

		this.lblNotaInferior = new JLabel("NOTA: Para eliminar una obra "
				+ " pulse sobre la obra y con el boton derecho seleccione la operacion deseada."
				+ " Para ir a la ventana principal, cierre esta ventana.");
		this.getContentPane().add(this.lblNotaInferior);

		this.panelBotones.setLayout(new BoxLayout(this.panelBotones, BoxLayout.LINE_AXIS));
		this.panelBotones.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));
		this.panelBotones.add(Box.createHorizontalGlue());

		crearBotonListenerAnadirObra();

		crearBotonListenerGuardar();

		this.getContentPane().add(this.panelBotones);
		this.pack();
	}

	/**
	 * Crea men� Eliminar Obra y su funcionalidad, asoci�ndolo al men� de la tabla
	 */
	private void crearBotonListenerEliminarObra() {
		mnuEliminarObra = new JMenuItem("Eliminar obra");
		mnuEliminarObra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.YES_OPTION;
				int numRegistro = Integer.parseInt(tabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
				Obra o;
				try {
					o = bib.obtenObra(numRegistro);
					boolean borrar = true;
					if (o instanceof IPrestable && ((IPrestable) o).estaPrestada()) {
						borrar = false;
						respuesta = JOptionPane.showConfirmDialog(null,
								"La obra de numero de registro " + numRegistro
										+ " esta prestada, seguro que quieres eliminar el prestamo? ",
								"Biblioteca -  Confirmacion eliminacion de prestamo", JOptionPane.YES_NO_OPTION);
						if (respuesta == JOptionPane.YES_OPTION) {
							borrar = true;
							bib.devolverPrestamo(numRegistro);
						}
					}
					if (borrar) {
						respuesta = JOptionPane.showConfirmDialog(null,
								"Estas a punto de eliminar la obra con numero de registro " + numRegistro
										+ ", �estas seguro?",
								"Biblioteca -  Confirmacion eliminacion de una obra", JOptionPane.YES_NO_OPTION);

						if (respuesta == JOptionPane.YES_OPTION) {
							bib.descatalogarObra(numRegistro);

							refrescarTabla();

							JOptionPane.showMessageDialog(tabla,
									"Eliminada la obra de numero de registro " + numRegistro);

						}
					}
				} catch (ObraDesconocida p) {
					System.err.println(
							"No se ha encontrado en la biblioteca una obra con el numero de registro " + numRegistro);
				} catch (ObraNoPrestada p) {
					System.err.println(
							"No se ha encontrado el prestamo de la obra con  numero de registro " + numRegistro);

				}
			}
		});
		menuTextoTabla.add(mnuEliminarObra);
	}

	/**
	 * Crea el bot�n A�adir Obra con su funcionalidad incluy�ndolo en el Panel de Botones.
	 */
	private void crearBotonListenerAnadirObra() {
		this.btnNuevaObra = new JButton("Anadir obra");
		ActionListener actNuevaObra = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearVentanaNuevaObra();
			}
		};
		btnNuevaObra.addActionListener(actNuevaObra);
		panelBotones.add(this.btnNuevaObra);

		this.panelBotones.add(Box.createRigidArea(new Dimension(10, 0)));
	}

	/**
	 * Crea el bot�n guardar el cat�logo con su funcionalidad incluy�ndolo en el Panel de
	 * Botones.
	 */
	private void crearBotonListenerGuardar() {
		this.btnGuardar = new JButton("Guardar catalogo");
		ActionListener actGuardar = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bib.guardarCatalogoEnFichero();
				aviso = new JOptionPane("Guardado en fichero el catalogo actualizado", JOptionPane.INFORMATION_MESSAGE);
				JDialog venAviso = aviso.createDialog("Biblioteca - Informacion");
				venAviso.setAlwaysOnTop(true);
				venAviso.setVisible(true);

			}
		};
		btnGuardar.addActionListener(actGuardar);
		panelBotones.add(this.btnGuardar);

		this.panelBotones.add(Box.createRigidArea(new Dimension(10, 0)));

	}

	/**
	 * Crea la tabla con la informaci�n necesaria para una nueva Obra y si pulsa aceptar la
	 * visualiza.
	 */
	private void crearVentanaNuevaObra() {
		VentanaNuevaObra venNuevaObra = new VentanaNuevaObra();
		venNuevaObra.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
		venNuevaObra.setVisible(true);
		if (venNuevaObra.pulsadoGuardar()) {
			int numReg = bib.getSiguienteNumeroRegistro() + 1;
			System.out.println("El numero de registro para la nueva obra es: " + numReg);
			Obra obra;
			switch (venNuevaObra.getTipoObra()) {
			case "Revista":
				obra = new Revista(numReg, venNuevaObra.getTxtSignatura(), venNuevaObra.getTxtTitulo(),
						venNuevaObra.getTxtNumRevista(), venNuevaObra.getTxtISSN());
				break;
			case "Libro":
				obra = new Libro(numReg, venNuevaObra.getTxtSignatura(), venNuevaObra.getTxtTitulo(),
						venNuevaObra.getTxtAutInterProd(), venNuevaObra.getTxtEditProd(),
						venNuevaObra.getTxtDescripcion());
				break;
			case "Enciclopedia":
				obra = new Enciclopedia(numReg, venNuevaObra.getTxtSignatura(), venNuevaObra.getTxtTitulo(),
						venNuevaObra.getTxtAutInterProd(), venNuevaObra.getTxtEditProd(),
						venNuevaObra.getTxtNumVolumnenes(), venNuevaObra.getTxtTema());
				break;
			case "Diccionario":
				obra = new Diccionario(numReg, venNuevaObra.getTxtSignatura(), venNuevaObra.getTxtTitulo(),
						venNuevaObra.getTxtAutInterProd(), venNuevaObra.getTxtEditProd(),
						venNuevaObra.getTxtNumVolumnenes(), venNuevaObra.getTxtIdioma());
				break;
			case "DVD":
				obra = new DVD(numReg, venNuevaObra.getTxtSignatura(), venNuevaObra.getTxtTitulo(),
						venNuevaObra.getTxtEditProd(), venNuevaObra.getTxtAutInterProd());
				break;
			case "CDMusica":
				obra = new CD(numReg, venNuevaObra.getTxtSignatura(), venNuevaObra.getTxtTitulo(),
						venNuevaObra.getTxtEditProd(), venNuevaObra.getTxtAutInterProd());
				break;
			default:
				obra = null;// esta opci�n no puede darse de momento
			}
			bib.catalogarObra(obra);
			refrescarTabla();
			JOptionPane.showMessageDialog(venNuevaObra,
					"Se ha anadido la obra de numero de registro " + numReg + ".");
		}
		venNuevaObra.dispose();
	}

	/**
	 * Determina los valores de ancho deseado para cada columna de la tabla que se visualiza
	 */
	private void setResize() {
		this.tabla.getColumnModel().getColumn(0).setMinWidth(100);
		this.tabla.getColumnModel().getColumn(0).setMaxWidth(100);
		this.tabla.getColumnModel().getColumn(1).setMinWidth(50);
		this.tabla.getColumnModel().getColumn(1).setMaxWidth(50);
		this.tabla.getColumnModel().getColumn(2).setMinWidth(100);
		this.tabla.getColumnModel().getColumn(2).setMaxWidth(100);
		this.tabla.getColumnModel().getColumn(4).setMinWidth(100);
		this.tabla.getColumnModel().getColumn(4).setMaxWidth(100);
		this.tabla.getColumnModel().getColumn(5).setMinWidth(100);
		this.tabla.getColumnModel().getColumn(5).setMaxWidth(100);
		this.tabla.getColumnModel().getColumn(6).setMinWidth(100);
		this.tabla.getColumnModel().getColumn(6).setMaxWidth(100);
	}

	/**
	 * Vuelve a crear y visualizar la VentanaGestionObras actualizada y eliminando la actual.
	 */
	private void refrescarTabla() {
		tabla.setModel(new ModeloTablaObras(bib.listaDeListasDeObras()));
		setResize();
	}

}
