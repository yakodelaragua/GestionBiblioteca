package gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

/**
 * Expresa un patrón Adapter: adaptando a la clase TableModel una matriz de elementos de tipo String que representa la información de prestables 
 *
 */
@SuppressWarnings("serial")
public class ModeloTablaPrestables extends AbstractTableModel {

		private final ArrayList<ArrayList<String>> matrizPrestables;

		private final ArrayList<String> nombresColumnas = new ArrayList<String>();

		/**
		 * Constructora: Asocia la lista de listas de Préstamos y define los nombres de las columnas
		 * @param matrizPrestamos matriz a la que aplicar la adaptación
		 */
		public ModeloTablaPrestables(ArrayList<ArrayList<String>> matrizPrestamos) {
			nombresColumnas.add("Tipo Obra");
			nombresColumnas.add("N. Reg.");
			nombresColumnas.add("Signatura");
			nombresColumnas.add("Titulo");
			nombresColumnas.add("Usuario");
			nombresColumnas.add("Fecha Prestamo");
			nombresColumnas.add("Fecha Devolucion");

			this.matrizPrestables = matrizPrestamos;
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
			return matrizPrestables.size();
		}

		@Override
		/*
		 * (non-Javadoc)
		 * @see javax.swing.table.TableModel#getValueAt(int, int)
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			try {
			return matrizPrestables.get(rowIndex).get(columnIndex);
			} catch (Exception e) {
				System.out.println("Los valores solicitados en la tabla son: "+rowIndex+", "+columnIndex);
			}
			return new Object();
		}
	}

