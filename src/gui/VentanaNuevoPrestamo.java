package gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

/**
 * Representa la ventana para realizar un pr�stamo con la informaci�n de la obra
 * seleccionada. Solicita rellenar el nombre del usuario e incluye un bot�n para guardar
 * la informaci�n y otro para cancelar el pr�stamo.
 */
@SuppressWarnings("serial")
public class VentanaNuevoPrestamo extends JDialog {

	private JPanel panelNuevoPrestamo; // panel del formulario gr�fico del pr�stamo

	private JTextField txtnumRegistro;
	private JTextField txtSignatura;
	private JTextField txtTitulo;
	private JTextField txtUsuario;
	private JTextField txtFechaPrestamo;
	private JTextField txtFechaDevolucion;

	private JLabel lblPrestamo;
	private JLabel lblNumRegistro;
	private JLabel lblSignatura;
	private JLabel lblTitulo;
	private JLabel lblUsuario;
	private JLabel lblFechaPrestamo;
	private JLabel lblFechaDevolucion;

	private boolean guardar = false;

	/**
	 * Constructora que guarda la informaci�n de la obra seleccionada de n�mero de registro,
	 * signatura, t�tulo, fecha del pr�stamo y fecha de devoluci�n, para mostrarlo en una
	 * ventana.
	 * @param numRegistro numero de registro de la obra que se desea tomar en pr�stamo
	 * @param sig signatura de la obra que se desea tomar en pr�stamo
	 * @param titulo t�tulo de la obra que se desea tomar en pr�stamo
	 * @param fPrestamo fecha del pr�stamo (fecha actual) de la obra que se desea tomar en
	 *        pr�stamo
	 * @param fDevolucion fecha de devoluci�n (contando que el pr�stamo se haga en el d�a de
	 *        hoy) de la obra que se desea tomar en pr�stamo
	 */
	public VentanaNuevoPrestamo(String numRegistro, String sig, String titulo, Date fPrestamo, Date fDevolucion) {
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setBounds(100, 100, 610, 313);
		panelNuevoPrestamo = new JPanel();
		panelNuevoPrestamo.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelNuevoPrestamo.setLayout(new FormLayout(
				new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));
		setContentPane(panelNuevoPrestamo);

		this.setTitle("Biblioteca - Tomar en pr�stamo");

		lblPrestamo = new JLabel("DATOS del Pr�stamo");
		lblPrestamo.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelNuevoPrestamo.add(lblPrestamo, "10, 2");

		lblNumRegistro = new JLabel("Numero de registro");
		panelNuevoPrestamo.add(lblNumRegistro, "2, 4, right, default");

		txtnumRegistro = new JTextField();
		panelNuevoPrestamo.add(txtnumRegistro, "4, 4, fill, default");
		txtnumRegistro.setColumns(10);
		txtnumRegistro.setEditable(false);
		txtnumRegistro.setVisible(true);
		txtnumRegistro.setEnabled(false);
		txtnumRegistro.setText(numRegistro);

		lblSignatura = new JLabel("Signatura");
		panelNuevoPrestamo.add(lblSignatura, "8, 4, right, default");

		txtSignatura = new JTextField();
		panelNuevoPrestamo.add(txtSignatura, "10, 4, fill, default");
		txtSignatura.setColumns(10);
		txtSignatura.setEditable(false);
		txtSignatura.setVisible(true);
		txtSignatura.setEnabled(false);
		txtSignatura.setText(sig);

		lblTitulo = new JLabel("Titulo");
		panelNuevoPrestamo.add(lblTitulo, "2, 6, right, default");

		txtTitulo = new JTextField();
		panelNuevoPrestamo.add(txtTitulo, "4, 6, 7, 1, fill, default");
		txtTitulo.setColumns(10);
		txtTitulo.setEditable(false);
		txtTitulo.setVisible(true);
		txtTitulo.setEnabled(false);
		txtTitulo.setText(titulo);

		lblUsuario = new JLabel("Nombre del usuario");
		panelNuevoPrestamo.add(lblUsuario, "2, 8, right, default");

		txtUsuario = new JTextField();
		panelNuevoPrestamo.add(txtUsuario, "4, 8, 7, 1, fill, default");
		txtUsuario.setColumns(10);
		txtUsuario.setVisible(true);
		txtUsuario.setEnabled(true);

		lblFechaPrestamo = new JLabel("Fecha del prestamo");
		panelNuevoPrestamo.add(lblFechaPrestamo, "2, 10, right, default");

		txtFechaPrestamo = new JTextField();
		panelNuevoPrestamo.add(txtFechaPrestamo, "4, 10, 2, 1, fill, default");
		txtFechaPrestamo.setColumns(10);
		txtFechaPrestamo.setEditable(false);
		txtFechaPrestamo.setVisible(true);
		txtFechaPrestamo.setEnabled(false);
		SimpleDateFormat dF = new SimpleDateFormat("yyyy-MM-dd");
		txtFechaPrestamo.setText(dF.format(fPrestamo)); 

		lblFechaDevolucion = new JLabel("Fecha de devolucion");
		panelNuevoPrestamo.add(lblFechaDevolucion, "2, 12, right, default");

		txtFechaDevolucion = new JTextField();
		panelNuevoPrestamo.add(txtFechaDevolucion, "4, 12, 2, 1, fill, default");
		txtFechaDevolucion.setColumns(10);
		txtFechaDevolucion.setEditable(false);
		txtFechaDevolucion.setVisible(true);
		txtFechaDevolucion.setEnabled(false);
		txtFechaDevolucion.setText(dF.format(fDevolucion));

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicializacionFormularioVentana();
				guardar = false;
				setVisible(false);
			}
		});

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensaje = "";

				for (Component control : panelNuevoPrestamo.getComponents()) {
					if ((control instanceof JTextField) && (control.isVisible()) && (control.isEnabled())) {
						if (((JTextField) control).getText().equals("")) {
							mensaje = "Es necesario indicar el nombre del usuario que quiere hacer el prestamo.";
							break;
						}
					}
				}

				if (mensaje.equals("")) {
					guardar = true;
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, mensaje, "Biblioteca - Error de validacion",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		panelNuevoPrestamo.add(btnGuardar, "4, 20");
		panelNuevoPrestamo.add(btnCancelar, "14, 20");
	}

	/**
	 * Determina si se ha pulsado al bot�n de guardar
	 * @return true sii se ha pulsado el bot�n guardar
	 */
	public boolean pulsadoGuardar() {
		return guardar;
	}

	/**
	 * Operaci�n de inicializaci�n: Limpia con un String vac�o todos los componente de texto
	 * de la ventana
	 */
	private void inicializacionFormularioVentana() {
		for (Component control : panelNuevoPrestamo.getComponents()) {
			if (control instanceof JTextField) {
				((JTextField) control).setText("");
			}
		}
	}

	// M�todos que acceden a la informaci�n de la ventana
	/**
	 * Obtiene el n�mero de registro de la obra
	 * @return numero de registro de la obra
	 */
	public int getTxtNumRegistro() {
		return Integer.parseInt(txtnumRegistro.getText());
	}

	/**
	 * Obtiene la signatura de la obra a prestar
	 * @return signatura de la obra
	 */
	public String getTxtSignatura() {
		return txtSignatura.getText();
	}

	/**
	 * Obtiene el titulo de la obra que se quiere obteber en pr�stamo
	 * @return titulo de la obra
	 */
	public String getTxtTitulo() {
		return txtTitulo.getText();
	}

	/**
	 * Obtiene el nombre del socio
	 * @return nombre del socio que hace el pr�stamo
	 */
	public String getTxtUsuario() {
		return txtUsuario.getText();
	}

	/**
	 * Obtiene la fecha del pr�stamo
	 * @return fecha del pr�stamo
	 */
	public String getTxtFechaPrestamo() {
		return txtFechaPrestamo.getText();
	}

	/**
	 * Obtiene la fecha de devoluci�n
	 * @return fecha de devoluci�n
	 */
	public String getTxtFechaDevolucion() {
		return txtFechaDevolucion.getText();
	}

}
