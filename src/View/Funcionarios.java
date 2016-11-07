package View;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import Control.FormIsVisible;
import Control.FuncionarioDAO;
import TableModel.FuncionarioTblModel;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

public class Funcionarios extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnEditar;

	@Override
	public void dispose() {
		if (FormIsVisible.isVisible("View.Funcionario")) {
			try {
				Main.frmCadFuncionario.dispose();

				super.dispose();
			} catch (Exception e) {
			}

		}

	}

	private JTextField txtPesq;
	private static JLabel lblTot;
	private static JTable table;
	private JInternalFrame frmCadFuncionarios;
	@SuppressWarnings("rawtypes")
	private static JComboBox cmbPesq;
	public static FuncionarioTblModel tbl;
	private static FuncionarioDAO dao = new FuncionarioDAO();
	static Dimension dimension;
	private JRadioButton rdIgual;
	private JRadioButton rdInicio;
	private JRadioButton rdContenha;
	private JRadioButton rdFim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Funcionarios frame = new Funcionarios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Funcionarios() {
		setFrameIcon(new ImageIcon(Main.class.getResource("/images/ciaps24x24.png")));
		setTitle("CADASTRO DE FUNCIONÁRIOS");
		setClosable(true);
		setBounds(100, 100, 811, 445);

		txtPesq = new JTextField();
		txtPesq.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		txtPesq.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					findFuncionario(cmbPesq.getSelectedItem().toString(), txtPesq.getText());
				} else if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
					txtPesq.setText("");
					findFuncionario("", "");
				}
			}
		});
		txtPesq.setColumns(10);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JLabel lblPesquisarPor = new JLabel("Pesquisar Por:");
		lblPesquisarPor.setFont(new Font("DejaVu Sans", Font.BOLD, 14));

		cmbPesq = new JComboBox();
		cmbPesq.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		cmbPesq.setAutoscrolls(true);
		cmbPesq.setModel(new DefaultComboBoxModel(new String[] { "Código", "Nome", "C.P.F.", "R.G.", "Endereço" }));
		cmbPesq.setSelectedIndex(0);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				findFuncionario(cmbPesq.getSelectedItem().toString(), txtPesq.getText());
			}
		});
		btnNewButton.setIcon(new ImageIcon(Main.class.getResource("/images/Buscar.png")));

		lblTot = new JLabel("");

		JPanel panel = new JPanel();

		JPanel pnlMetodoDeBusca = new JPanel();
		pnlMetodoDeBusca.setName("");
		pnlMetodoDeBusca.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Op\u00E7\u00F5es de pesquisa:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1170, Short.MAX_VALUE)
						.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 1170, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPesquisarPor)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cmbPesq, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtPesq))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pnlMetodoDeBusca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 337, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTot, GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
							.addGap(339)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(pnlMetodoDeBusca, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPesquisarPor, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(cmbPesq, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtPesq, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))
					.addGap(22)
					.addComponent(lblTot, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
					.addContainerGap())
		);

		rdIgual = new JRadioButton("Igual");
		rdIgual.setToolTipText("Esta opção garante que seja(m) retornado o(s) registro(s) que for(em) igual(is) aos infomados na caixa de texto.");
		pnlMetodoDeBusca.add(rdIgual);
		rdIgual.setFont(new Font("DejaVu Sans", Font.BOLD, 14));

		rdInicio = new JRadioButton("No início");
		rdInicio.setToolTipText("Esta opção garante que seja(m) retornado o(s) registro(s) que se inicia(m) com os valores infomados na caixa de texto.");
		pnlMetodoDeBusca.add(rdInicio);
		rdInicio.setFont(new Font("DejaVu Sans", Font.BOLD, 14));

		rdContenha = new JRadioButton("Contenha");
		rdContenha.setToolTipText("Esta opção garante que seja(m) retornado o(s) registro(s) que contenha(m) no início meio ou fim os valores iguais aos infomados na caixa de texto.");
		pnlMetodoDeBusca.add(rdContenha);
		rdContenha.setSelected(true);
		rdContenha.setFont(new Font("DejaVu Sans", Font.BOLD, 14));

		rdFim = new JRadioButton("No fim");
		rdFim.setToolTipText("Esta opção garante que seja(m) retornado o(s) registro(s) que termina(m) com os valores infomados na caixa de texto.");
		pnlMetodoDeBusca.add(rdFim);
		rdFim.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		ButtonGroup group = new ButtonGroup();
		group.add(rdIgual);
		group.add(rdInicio);
		group.add(rdContenha);
		group.add(rdFim);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editFuncionario();
				}
			}
		});
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// Font fon = new Font("Tahoma", 0, 16);
		// table.setFont(fon);
		tbl = new FuncionarioTblModel(dao.listAllFuncionarios());
		refreshTbl(tbl);
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JButton btnNovo = new JButton("NOVO");
		btnNovo.setToolTipText("Cadastrar um novo funcionário.");
		btnNovo.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (!FormIsVisible.isVisible("View.CadFuncionarios")) {
					Main.frmCadFuncionario = new CadFuncionarios();
					Main.frmCadFuncionario.setVisible(true);
					Main.frmCadFuncionario.show();
					Main.desktopPane.add(Main.frmCadFuncionario);
					Main.centralizar(Main.frmCadFuncionario);
					try {
						Main.frmCadFuncionario.setSelected(true);
					} catch (PropertyVetoException ex) {
						JOptionPane.showMessageDialog(null, "Erro ao criar form Edit Usuarios");
					}
				} else {
					Main.frmCadFuncionario.dispose();
					Main.frmCadFuncionario = new CadFuncionarios();
					Main.frmCadFuncionario.setVisible(true);
					Main.frmCadFuncionario.show();
					Main.desktopPane.add(Main.frmCadFuncionario);
					Main.centralizar(Main.frmCadFuncionario);
					try {
						Main.frmCadFuncionario.setSelected(true);
					} catch (PropertyVetoException ex) {
						JOptionPane.showMessageDialog(null, "Erro ao criar form Edit Usuarios");
					}
				}
			}
		});
		btnNovo.setIcon(new ImageIcon(Main.class.getResource("/images/Add.png")));
		toolBar.add(btnNovo);

		btnEditar = new JButton("EDITAR");
		btnEditar.setToolTipText("Alterar informações do funcionário.");
		btnEditar.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (table.getSelectedRow() >= 0) {
					if (!FormIsVisible.isVisible("View.CadFuncionarios")) {

						CadFuncionarios.isAlter = true;
						CadFuncionarios.func = tbl.getFuncionario(table.getSelectedRow());
						Main.frmCadFuncionario = new CadFuncionarios();
						Main.frmCadFuncionario.setVisible(true);
						Main.frmCadFuncionario.show();
						Main.desktopPane.add(Main.frmCadFuncionario);
						Main.centralizar(Main.frmCadFuncionario);
						try {
							Main.frmCadFuncionario.setSelected(true);

						} catch (PropertyVetoException ex) {
							JOptionPane.showMessageDialog(null, "Erro ao criar form Edit Usuarios");
						}
					} else {
						try {
							Main.frmCadFuncionario.dispose();
						} catch (Exception e) {
							// TODO: handle exception
						}
						CadFuncionarios.isAlter = true;
						CadFuncionarios.func = tbl.getFuncionario(table.getSelectedRow());
						Main.frmCadFuncionario = new CadFuncionarios();
						Main.frmCadFuncionario.setVisible(true);
						Main.frmCadFuncionario.show();
						Main.desktopPane.add(Main.frmCadFuncionario);
						Main.centralizar(Main.frmCadFuncionario);

						try {
							frmCadFuncionarios.setSelected(true);
						} catch (PropertyVetoException ex) {
							JOptionPane.showMessageDialog(null, "Erro ao criar form Edit Usuarios");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um funcionário na tabela ");
				}
			}
		});
		btnEditar.setIcon(new ImageIcon(Main.class.getResource("/images/Edit.png")));
		toolBar.add(btnEditar);
		getContentPane().setLayout(groupLayout);
		getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { table, scrollPane, toolBar,
				btnNovo, btnEditar, lblPesquisarPor, cmbPesq, txtPesq, btnNewButton, lblTot }));
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { txtPesq, getContentPane(), scrollPane,
				table, toolBar, btnNovo, btnEditar, lblPesquisarPor, cmbPesq, btnNewButton, lblTot }));
	}

	public static void refreshTbl(FuncionarioTblModel t) {

		if (t.getRowCount() > 0) {
			// dimension = Main.desktopPane.getSize();
			table.setModel(t);
			table.setFillsViewportHeight(true);
			table.getColumnModel().getColumn(0)
					.setPreferredWidth(60/* (int) (dimension.width * 0.09) */);
			table.getColumnModel().getColumn(1)
					.setPreferredWidth(250/* (int) (dimension.width * 0.22) */);
			table.getColumnModel().getColumn(2)
					.setPreferredWidth(425/* (int) (dimension.width * 0.22) */);
			table.getColumnModel().getColumn(3)
					.setPreferredWidth(130/* (int) (dimension.width * 0.10) */);
			table.getColumnModel().getColumn(4)
					.setPreferredWidth(100/* (int) (dimension.width * 0.11) */);
			table.getColumnModel().getColumn(5)
					.setPreferredWidth(100/* (int) (dimension.width * 0.11) */);
			table.getColumnModel().getColumn(6)
					.setPreferredWidth(130/* (int) (dimension.width * 0.11) */);
			table.getColumnModel().getColumn(7)
					.setPreferredWidth(130/* (int) (dimension.width * 0.11) */);
			lblTot.setText(String.valueOf(t.getRowCount()) + " registro(s).");
			table.setRowSelectionInterval(0, 0);
		}
	}

	private void findFuncionario(String col, String par) {
		String metodo = "";
		if (rdIgual.isSelected())
			metodo = "igual";
		else if (rdInicio.isSelected())
			metodo = "inicio";
		else if (rdContenha.isSelected())
			metodo = "contenha";
		else if (rdFim.isSelected())
			metodo = "fim";
		else
			JOptionPane.showMessageDialog(null, "Selecione uma das opções\n Igual\n No início\n Contenha\n No fim",
					"Método de pesquisa inválica", JOptionPane.WARNING_MESSAGE);
		;
		if (col == "" && par == "") {
			tbl = new FuncionarioTblModel(dao.listAllFuncionarios());
			refreshTbl(tbl);
			return;
		}
		if (col == "Código") {
			try {
				Integer.parseInt(par);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Número [" + par + "] inválido...");
				return;
			}
		}
		tbl = new FuncionarioTblModel(dao.getListOfFuncionariosBy(col, par, metodo));
		if (tbl.getRowCount() > 0) {
		} else {
			JOptionPane.showMessageDialog(null,
					"Nenhum registro encontrado na base de dados para o valor " + par + " no campo " + col);
			tbl = new FuncionarioTblModel(dao.listAllFuncionarios());
		}
		refreshTbl(tbl);
	}

	private void editFuncionario() {

		if (table.getSelectedRow() >= 0) {
			btnEditar.doClick();
		} else {
			JOptionPane.showMessageDialog(null, "Selecione um funcionário na tabela", "Atenção!!!",
					JOptionPane.WARNING_MESSAGE);
			table.requestFocus();
		}
	}
}
