package TableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Model.Usuarios;

public class UsuariosTblModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Usuarios> data;

	public UsuariosTblModel(List<Usuarios> data) {
		this.data = data;
	}

	private String colunas[] = { "Nome", "Fun��o", "Status" };

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	public Usuarios getUsuarios(int row) {
		return data.get(row);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Usuarios user = data.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return user.getNomeUsuario();
		case 1:
			return user.getTipoUser().getTipo();
		case 2:
			if (user.getStatus() == '1') {
				return "Habilitado";
			} else {
				return "Bloqueado";
			}
		default:
			throw new IndexOutOfBoundsException("Coluna Inv�lida!!!");
		}
	}

	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}

}
