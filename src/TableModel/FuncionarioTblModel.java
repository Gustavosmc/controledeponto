package TableModel;

import java.sql.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Control.DataConverter;
import Model.Funcionario;

public class FuncionarioTblModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Funcionario> data;

	public FuncionarioTblModel(List<Funcionario> data) {
		this.data = data;
	}

	private String colunas[] = { "Código", "Nome", "Endereço", "C.P.F.", "R.G.", "Dt.Nasc.", "Tel. Celular",
			"Tel. Fixo" };

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	public Funcionario getFuncionario(int row) {
		return data.get(row);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Funcionario func = data.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return func.getId();
		case 1:
			return func.getNome();
		case 2:
			return func.getEndereco().getLogradouro() + " nº " + func.getEndereco().getNumero() + " - "
					+ func.getEndereco().getBairro();
		case 3:
			return func.getCpf();
		case 4:
			return func.getRg();
		case 5:
			if (func.getDataNasc() == null)
				return "";
			else
				return DataConverter.dateToString(Date.valueOf(func.getDataNasc()));
		case 6:
			return func.getTelefoneCelular();
		case 7:
			return func.getTelefoneFixo();
		default:
			throw new IndexOutOfBoundsException("Coluna Inválida!!!");
		}
	}

	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}

}
