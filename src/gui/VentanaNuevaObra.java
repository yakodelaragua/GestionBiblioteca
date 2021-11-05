package gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

/**
 * Representa la ventana para catalogar una nueva obra. A trav�s de un desplegable se
 * seleciona el tipo de obra a catalogar y se ajusta la ventana a los valores que se deben
 * aportar para su creaci�n.
 */

@SuppressWarnings("serial")
public class VentanaNuevaObra extends JDialog {

	private JPanel panelObraNueva;

	private JTextField txtNumRegistro;
	private JTextField txtSignatura;
	private JTextField txtTitulo;
	private JTextField txtAutInterProd;
	private JTextField txtEditProd;
	private JTextField txtDescripcion;
	private JTextField txtNumVolumnenes;
	private JTextField txtNumRevista;
	private JTextField txtISSN;
	private JTextField txtTema;
	private JTextField txtIdioma;

	JCheckBox chkEstaPrestado;

	private JLabel lblNumRegistro;
	private JLabel lblAutInterProd;
	private JLabel lblEditProd;
	private JLabel lblDescripcion;
	private JLabel lblNumVolumenes;
	private JLabel lblIdioma;
	private JLabel lblTema;
	private JLabel lblISSN;

	private boolean guardar = false;

	private JComboBox<String> cbxTipoObra;

	private String[] itemsCombobox = { "Revista", "Libro", "Enciclopedia", "Diccionario", "DVD", "CDMusica" };

	/**
	 * Constructora: que genera la ventana para poder introducir una nueva Obra. Inicialmente
	 * aparecen los campos de Revista
	 */
	public VentanaNuevaObra() {
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setBounds(100, 100, 610, 313);
		panelObraNueva = new JPanel();
		panelObraNueva.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelObraNueva.setLayout(new FormLayout(
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
		setContentPane(panelObraNueva);
		this.setTitle("Biblioteca - Catalogar obra");

		definirVisualizarComponentesComunes();

		definirRestoComponentesEspecificos();

		incluirBotones();

		desvisualizaComponentesEspecificos();

		// Visualizar componentes de Revista (primera opci�n)
		lblNumRegistro.setVisible(true);
		txtNumRevista.setVisible(true);
		lblISSN.setVisible(true);
		txtISSN.setVisible(true);
	}

	/**
	 * Determina si se ha pulsado el bot�n Guardar
	 * @return true, si se ha pulsado el bot�n Guardar; false, en caso contrario.
	 */
	public boolean pulsadoGuardar() {
		return guardar;
	}

	// M�todos para obtener los valores de la ventana:

	/**
	 * Obtiene el tipo de obra seleccionado
	 * @return tipo de obra selecionado
	 */
	public String getTipoObra() {
		return cbxTipoObra.getSelectedItem().toString();
	}

	/**
	 * Obtienen el n�mero de registro de la obra seleccionada
	 * @return n�mero de registro de la obra seleccionada
	 */
	public int getTxtNumRegistro() {
		return Integer.parseInt(txtNumRegistro.getText());
	}

	/**
	 * Obtienen la signatura de la obra seleccionada
	 * @return signatura de la obra seleccionada
	 */
	public String getTxtSignatura() {
		return txtSignatura.getText();
	}

	/**
	 * Obtienen el t�tulo de la obra seleccionada
	 * @return t�tulo de la obra seleccionada
	 */
	public String getTxtTitulo() {
		return txtTitulo.getText();
	}

	/**
	 * Obtiene dependiendo de la obra seleccionada el autor/director/int�rprete
	 * @return autor/director/int�rprete de la obra seleccionada
	 */
	public String getTxtAutInterProd() {
		return txtAutInterProd.getText();
	}

	/**
	 * Obtiene dependiendo de la obra seleccionada la editorial/productor
	 * @return editorial/producto de la obra seleccionada
	 */
	public String getTxtEditProd() {
		return txtEditProd.getText();
	}

	/**
	 * Obtiene el n�mero de vol�menes de la colecci�n seleccionada
	 * @return n�mero de vol�menes de la colecci�n seleccionada
	 */
	public int getTxtNumVolumnenes() {
		return Integer.parseInt(txtNumVolumnenes.getText());
	}

	/**
	 * Obtiene el n�mero de revista de la revista seleccionada
	 * @return n�mero de revista de la revista seleccionada
	 */
	public int getTxtNumRevista() {
		return Integer.parseInt(txtNumRevista.getText());
	}

	/**
	 * Obtiene el ISSN de la revista seleccionada
	 * @return ISSN de la revista seleccionada
	 */
	public String getTxtISSN() {
		return txtISSN.getText();
	}

	/**
	 * Obtiene la descripci�n del libro seleccionado
	 * @return descripci�n del libro seleccionado
	 */
	public String getTxtDescripcion() {
		return txtDescripcion.getText();
	}

	/**
	 * Obtiene la tem�tica de la enciclopedia seleccionada
	 * @return tem�tica de la enciclopedia seleccionada
	 */
	public String getTxtTema() {
		return txtTema.getText();
	}

	/**
	 * Obtiene el idioma del diccionario seleccionado
	 * @return hizkuntzen koadroan sartutako balioa
	 */
	public String getTxtIdioma() {
		return txtIdioma.getText();
	}

	/**
	 * Obtiene la etiqueta correspondiente a la Descripcion del libro
	 * @return etiqueta que contiene la descripci�n del libro
	 */
	public JLabel getLblDescripcion() {
		return lblDescripcion;
	}

	/**
	 * Actualiza la descripci�n de la etiqueta
	 * @param lblDescripcion descripci�n con al que actualizar la etiqueta.
	 */
	public void setLblDescripcion(JLabel lblDescripcion) {
		this.lblDescripcion = lblDescripcion;
	}

	// Operaciones privadas
	/**
	 * Agrupa todas las manipulaciones comunes a toda obra necesarias para estructurar la
	 * ventana
	 */
	private void definirVisualizarComponentesComunes() {

		JLabel lblAlea = new JLabel("OBRA ");
		lblAlea.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelObraNueva.add(lblAlea, "10, 2", 0);

		JLabel lblErregZenbakia = new JLabel("Numero de registro");
		panelObraNueva.add(lblErregZenbakia, "2, 4, right, default");
		txtNumRegistro = new JTextField();
		panelObraNueva.add(txtNumRegistro, "4, 4, fill, default");
		txtNumRegistro.setColumns(10);
		txtNumRegistro.setEditable(false);
		txtNumRegistro.setVisible(true);
		txtNumRegistro.setEnabled(false);

		JLabel lblSignatura = new JLabel("Signatura");
		panelObraNueva.add(lblSignatura, "8, 4, right, default", 3);
		txtSignatura = new JTextField();
		panelObraNueva.add(txtSignatura, "10, 4, fill, default");
		txtSignatura.setColumns(10);

		JLabel lblIzenburua = new JLabel("Titulo");
		panelObraNueva.add(lblIzenburua, "2, 6, right, default");
		txtTitulo = new JTextField();
		panelObraNueva.add(txtTitulo, "4, 6, 7, 1, fill, default");
		txtTitulo.setColumns(10);

		JLabel lblMota = new JLabel("Tipo");
		panelObraNueva.add(lblMota, "12, 4, right, default");

		cbxTipoObra = new JComboBox<String>();
		cbxTipoObra.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					desvisualizaComponentesEspecificos();
					visualizaCamposEspecificos(e);
				}
			}

		});
		cbxTipoObra.setModel(new DefaultComboBoxModel<String>(itemsCombobox));
		panelObraNueva.add(cbxTipoObra, "14, 4, 7, 1, fill, default");

	}

	/**
	 * Estructura y completa la informaci�n espec�fica de cada tipo de Obra.
	 */
	private void definirRestoComponentesEspecificos() {
		lblAutInterProd = new JLabel();
		panelObraNueva.add(lblAutInterProd, "2, 8, right, default");
		txtAutInterProd = new JTextField();
		panelObraNueva.add(txtAutInterProd, "4, 8, 7, 1, fill, default");
		txtAutInterProd.setColumns(10);

		lblNumVolumenes = new JLabel("Num. Vol.");
		panelObraNueva.add(lblNumVolumenes, "12, 8, right, default");
		txtNumVolumnenes = new JTextField();
		panelObraNueva.add(txtNumVolumnenes, "14, 8, fill, default");
		txtNumVolumnenes.setColumns(10);

		lblEditProd = new JLabel();
		panelObraNueva.add(lblEditProd, "2, 10, right, default");
		txtEditProd = new JTextField();
		panelObraNueva.add(txtEditProd, "4, 10, 7, 1, fill, default");
		txtEditProd.setColumns(10);

		setLblDescripcion(new JLabel("Descripcion"));
		panelObraNueva.add(getLblDescripcion(), "2, 12, right, default");
		txtDescripcion = new JTextField();
		panelObraNueva.add(txtDescripcion, "4, 12, 7, 1, fill, default");
		txtDescripcion.setColumns(10);

		lblTema = new JLabel("Tematica");
		panelObraNueva.add(lblTema, "2, 14, right, default");
		txtTema = new JTextField();
		panelObraNueva.add(txtTema, "4, 14, 7, 1, fill, default");
		txtTema.setColumns(10);

		lblIdioma = new JLabel("Idioma");
		panelObraNueva.add(lblIdioma, "12, 14, right, default");
		txtIdioma = new JTextField();
		panelObraNueva.add(txtIdioma, "14, 14, fill, default");
		txtIdioma.setColumns(10);

		lblNumRegistro = new JLabel("Num. revista");
		panelObraNueva.add(lblNumRegistro, "2, 16, right, default");
		txtNumRevista = new JTextField();
		panelObraNueva.add(txtNumRevista, "4, 16, fill, default");
		txtNumRevista.setColumns(10);

		lblISSN = new JLabel("ISSN");
		panelObraNueva.add(lblISSN, "12, 16, right, default");
		txtISSN = new JTextField();
		panelObraNueva.add(txtISSN, "14, 16, fill, default");
		txtISSN.setColumns(10);
	}

	/**
	 * Incluye los botonos con sus listener para las funcionalidades: Guardar el cat�logo,
	 * Cancelar y Limpiar el formulario
	 */
	private void incluirBotones() {
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicializarVaciosTotosLosComponenetes();
				guardar = false;
				setVisible(false);
			}
		});

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensaje = "";

				// Es obligatorio que est� completo el panel
				for (Component control : panelObraNueva.getComponents()) {
					if ((control instanceof JTextField) && (control.isVisible()) && (control.isEnabled())) {
						if (((JTextField) control).getText().equals("")) {
							mensaje = "Es necesario completar cada uno de los apartados requeridos.";
							break;
						}
					}
				}

				if ((txtNumVolumnenes.isVisible() && !esNumero(txtNumVolumnenes.getText()))
						|| (txtNumRevista.isVisible() && !esNumero(txtNumRevista.getText()))) {
					mensaje = mensaje + " Los campos num�ricos deben incluir valores reales/enteros correctos.";
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

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicializarVaciosTotosLosComponenetes();
			}
		});

		panelObraNueva.add(btnGuardar, "4, 20");
		panelObraNueva.add(btnLimpiar, "12, 20");
		panelObraNueva.add(btnCancelar, "14, 20");
	}

	/**
	 * Determina si n corresponde con el valor de un n�mero entero
	 * @param n valor a determinar si representa a un entero
	 * @return true sii n corresponde a un n�mero entero.
	 */
	private static boolean esNumero(String n) {
		try {
			Integer.parseInt(n);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Inicializa todos los campos de la ventana a rellenar tipo texto con valores vac�os. El
	 * comboBox con la primera opci�n: "Revista" y con la opci�n de seleccionado a falso
	 */
	private void inicializarVaciosTotosLosComponenetes() {
		for (Component componente : panelObraNueva.getComponents()) {
			if (componente instanceof JTextField) {
				((JTextField) componente).setText("");
			} else if (componente instanceof JComboBox<?>) {
				((JComboBox<?>) componente).setSelectedIndex(0);
			} else if (componente instanceof JCheckBox) {
				((JCheckBox) componente).setSelected(false);
			}
		}
	}

	/**
	 * Hace que no se visualicen los componentes espec�ficos del panel
	 */
	private void desvisualizaComponentesEspecificos() {
		lblAutInterProd.setVisible(false);
		txtAutInterProd.setVisible(false);

		lblEditProd.setVisible(false);
		txtEditProd.setVisible(false);

		lblNumVolumenes.setVisible(false);
		txtNumVolumnenes.setVisible(false);

		lblNumRegistro.setVisible(false);
		txtNumRevista.setVisible(false);

		lblISSN.setVisible(false);
		txtISSN.setVisible(false);

		getLblDescripcion().setVisible(false);
		txtDescripcion.setVisible(false);

		lblTema.setVisible(false);
		txtTema.setVisible(false);

		lblIdioma.setVisible(false);
		txtIdioma.setVisible(false);
	}

	/**
	 * Visualiza los campos espec�ficos correspondientes al tipo de Obra seleccionado en el
	 * comBox.
	 * @param e Item seleccionado del comboBox correspondiente a un tipo de Obra
	 */
	private void visualizaCamposEspecificos(ItemEvent e) {
		switch (e.getItem().toString()) {
		case "Revista":
			lblNumRegistro.setVisible(true);
			txtNumRevista.setVisible(true);
			lblISSN.setVisible(true);
			txtISSN.setVisible(true);
			break;
		case "Libro":
			lblAutInterProd.setText("Autor");
			lblAutInterProd.setVisible(true);
			txtAutInterProd.setVisible(true);

			lblEditProd.setText("Editorial");
			lblEditProd.setVisible(true);
			txtEditProd.setVisible(true);
			lblDescripcion.setVisible(true);
			txtDescripcion.setVisible(true);
			break;
		case "Enciclopedia":
			lblAutInterProd.setText("Autor");
			lblAutInterProd.setVisible(true);
			txtAutInterProd.setVisible(true);
			lblEditProd.setText("Editorial");
			lblEditProd.setVisible(true);
			txtEditProd.setVisible(true);
			lblNumVolumenes.setVisible(true);
			txtNumVolumnenes.setVisible(true);
			lblTema.setVisible(true);
			txtTema.setVisible(true);
			break;
		case "Diccionario":
			lblAutInterProd.setText("Autor");
			lblAutInterProd.setVisible(true);
			txtAutInterProd.setVisible(true);
			lblEditProd.setText("Editorial");
			lblEditProd.setVisible(true);
			txtEditProd.setVisible(true);
			lblNumVolumenes.setVisible(true);
			txtNumVolumnenes.setVisible(true);
			lblIdioma.setVisible(true);
			txtIdioma.setVisible(true);
			break;
		case "DVD":
			lblAutInterProd.setText("Direccion");
			lblAutInterProd.setVisible(true);
			txtAutInterProd.setVisible(true);
			lblEditProd.setText("Productora");
			lblEditProd.setVisible(true);
			txtEditProd.setVisible(true);
			break;
		case "CDMusica":
			lblAutInterProd.setText("Interprete");
			lblAutInterProd.setVisible(true);
			txtAutInterProd.setVisible(true);
			lblEditProd.setText("Productora");
			lblEditProd.setVisible(true);
			txtEditProd.setVisible(true);
			break;
		default:
		}
	}
}
