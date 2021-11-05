package gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

/**
 * Expresa un patrón Adapter: adaptando una matriz de elementos de tipo String que representa la información de una obra, a la clase TableModel
 *
 */
@SuppressWarnings("serial")
public class ModeloTablaObras extends AbstractTableModel { // katalogoa bistaratzeko taularen modeloa

	private final ArrayList<ArrayList<String>> matrizObras;

	private final ArrayList<String> nombresColumnas = new ArrayList<String>();

	/**
	 * Constructora: Asocia la lista de listas de Obras y define los valores de las columnas.
	 * @param matrizObras matriz a la que aplicar la adaptación
	 */
	public ModeloTablaObras(ArrayList<ArrayList<String>> matrizObras) {
		nombresColumnas.add("Tipo Obra");
		nombresColumnas.add("N. Reg.");
		nombresColumnas.add("Signatura");
		nombresColumnas.add("Titulo");
		nombresColumnas.add("Auto./Direc./Inter.");
		nombresColumnas.add("Productora/Editorial");
		nombresColumnas.add("Descripcion");
		nombresColumnas.add("Otros");

		this.matrizObras = matrizObras;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	public String getColumnName(int column) {
		return nombresColumnas.get(column);
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return nombresColumnas.size();
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return matrizObras.size();
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		return matrizObras.get(rowIndex).get(columnIndex);
	}

}