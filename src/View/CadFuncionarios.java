package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import com.toedter.calendar.JDateChooser;

import Control.FormIsVisible;
import Control.FuncionarioDAO;
import Model.CargaHoraria;
import Model.Endereco;
import Model.Funcionario;
import TableModel.FuncionarioTblModel;
import net.miginfocom.swing.MigLayout;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class CadFuncionarios extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private JFormattedTextField txtTel1;
	private JFormattedTextField txtTel2;
	public static boolean isAlter = false;
	public static Funcionario func = new Funcionario();
	private FuncionarioDAO dao = new FuncionarioDAO();

	private JTextField tfCtps;
	private JFormattedTextField txtCpf;
	private JTextField txtEndereco;
	private JTextField txtRG;
	private JFormattedTextField tfCep;
	JDateChooser dtNasc;
	private JTextField txtNomeMae;
	private JTextField tfNumero;
	private JTextField tfBairro;
	private JTextField tfCidade;
	private JFormattedTextField tfEstado;
	private JTextField tfEmail;
	private JTextField tfNomePai;
	private JFormattedTextField tfHoraInicial;
	private JFormattedTextField tfHoraFinal;
	private JFormattedTextField tfIntervalo;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbSexo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadFuncionarios frame = new CadFuncionarios();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CadFuncionarios() {
		setBounds(100, 100, 679, 500);
		getContentPane().setFont(new Font("Dialog", Font.PLAIN, 14));
		setFrameIcon(new ImageIcon(Main.class.getResource("/images/ciaps24x24.png")));
		getContentPane().setLayout(new MigLayout("", "[126.00px,grow][158.00,grow][grow][grow]",
				"[][246px,grow][][][][][][][][][18.00][][][][][]"));
		setClosable(true);
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		getContentPane().add(toolBar, "cell 0 0 4 1,growx");

		JButton btnSalvar = new JButton("SALVAR");
		btnSalvar.setToolTipText("Confirmar e/ou salvar informações do funcionário.");
		btnSalvar.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		btnSalvar.setIcon(new ImageIcon(CadFuncionarios.class.getResource("/images/Confirm.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (txtNome.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campo Nome de preenchimento obrigatório.", "Atenção",
							JOptionPane.WARNING_MESSAGE);
					txtNome.requestFocus();
					return;
				}
				if (dtNasc.getDateEditor().getDate() == null) {
					JOptionPane.showMessageDialog(null, "Data de nascimento inválida", "Atenção",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				dtNasc.requestFocus();
				func.setDataNasc(new Date(dtNasc.getDate().getTime()).toLocalDate());
				if (cmbSexo.getSelectedItem().toString().equals("Masculino"))
					func.setSexo('M');
				else if (cmbSexo.getSelectedItem().toString().equals("Feminino"))
					func.setSexo('F');
				else if (cmbSexo.getSelectedItem().toString().equals("Outros"))
					func.setSexo('O');
				func.setCtps(tfCtps.getText());
				func.setNome(txtNome.getText().trim());
				func.setNomeMae(txtNomeMae.getText().trim());
				func.setNomePai(tfNomePai.getText().trim());
				Endereco end = new Endereco();
				end.setLogradouro(txtEndereco.getText());
				end.setBairro(tfBairro.getText().trim());
				end.setNumero(tfNumero.getText().trim());
				end.setCidade(tfCidade.getText().trim());
				end.setUf(tfEstado.getText().trim());
				end.setCep(tfCep.getText());
				CargaHoraria ch = new CargaHoraria();
				if (!tfHoraInicial.getText().equals("  :  :00"))
					ch.setHoraInicio(Time.valueOf(tfHoraInicial.getText()));
				else {
					JOptionPane.showMessageDialog(null, "Campo hora inicial é de preenchimento obrigatório!", "Atenção",
							JOptionPane.WARNING_MESSAGE);
					tfHoraInicial.requestFocus();
					return;
				}
				if (!tfHoraFinal.getText().equals("  :  :00"))
					ch.setHoraFim(Time.valueOf(tfHoraFinal.getText()));
				else {
					JOptionPane.showMessageDialog(null, "Campo hora final é de preenchimento obrigatório!", "Atenção",
							JOptionPane.WARNING_MESSAGE);
					tfHoraFinal.requestFocus();
					return;
				}
				if (!tfIntervalo.getText().equals("  :  :00"))
					ch.setIntervalo(Time.valueOf(tfIntervalo.getText()));
				else {
					JOptionPane.showMessageDialog(null, "Campo intervalo é de preenchimento obrigatório!", "Atenção",
							JOptionPane.WARNING_MESSAGE);
					tfIntervalo.requestFocus();
					return;
				}
				func.setEndereco(end);
				func.setCargaHoraria(ch);
				func.setCpf(txtCpf.getText());
				func.setRg(txtRG.getText().trim());
				func.setTelefoneCelular(txtTel1.getText());
				func.setTelefoneFixo(txtTel2.getText());
				func.setEmail(tfEmail.getText());

				if (isAlter) {
					dao.salvar(func);
					if (FormIsVisible.isVisible("View.Funcionarios")) {
						Funcionarios.tbl = new FuncionarioTblModel(dao.listAllFuncionarios());
						Funcionarios.refreshTbl(Funcionarios.tbl);
					}
					func = null;
					isAlter = false;
					dispose();
				} else {
					func.setId(0);
					dao.salvar(func);
					if (FormIsVisible.isVisible("View.Funcionarios")) {
						Funcionarios.tbl = new FuncionarioTblModel(dao.listAllFuncionarios());
						Funcionarios.refreshTbl(Funcionarios.tbl);
					}
					func = null;
					dispose();

				}
			}
		});
		toolBar.add(btnSalvar);

		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setToolTipText("Cancela a criação ou edição do funcionário e volta a tela de funcionários cadastrados.");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				func = null;
				isAlter = false;
				dispose();
			}
		});
		btnCancelar.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		btnCancelar.setIcon(new ImageIcon(CadFuncionarios.class.getResource("/images/Cancelar.png")));
		toolBar.add(btnCancelar);
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(lblNome, "cell 0 1");

		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(lblSexo, "cell 3 1");

		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(txtNome, "cell 0 2 3 1,growx");
		txtNome.setColumns(10);

		cmbSexo = new JComboBox();
		cmbSexo.setModel(new DefaultComboBoxModel(new String[] { "Masculino", "Feminino", "Outros" }));
		cmbSexo.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		getContentPane().add(cmbSexo, "cell 3 2,growx");

		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(lblEndereo, "cell 0 3");

		JLabel lblNmero = new JLabel("Número");
		lblNmero.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(lblNmero, "cell 3 3");

		txtEndereco = new JTextField();
		txtEndereco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEndereco.setColumns(10);
		getContentPane().add(txtEndereco, "cell 0 4 3 1,growx");

		tfNumero = new JTextField();
		tfNumero.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfNumero.setColumns(10);
		getContentPane().add(tfNumero, "cell 3 4,growx");

		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(lblBairro, "cell 0 5");

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(lblCidade, "cell 1 5");

		JLabel lblCep = new JLabel("Estado");
		lblCep.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(lblCep, "cell 2 5");

		JLabel lblCep_1 = new JLabel("Cep.");
		lblCep_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(lblCep_1, "cell 3 5");

		tfBairro = new JTextField();
		tfBairro.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfBairro.setColumns(10);
		getContentPane().add(tfBairro, "cell 0 6,growx");

		tfCidade = new JTextField();
		tfCidade.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfCidade.setColumns(10);
		getContentPane().add(tfCidade, "cell 1 6,growx");

		tfEstado = new JFormattedTextField();
		try {
			tfEstado.setFormatterFactory(
					new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("UU")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tfEstado.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfEstado.setColumns(10);
		getContentPane().add(tfEstado, "cell 2 6,growx");

		tfCep = new JFormattedTextField();
		tfCep.setFont(new Font("Tahoma", Font.PLAIN, 14));
		try {
			tfCep.setFormatterFactory(
					new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###-###")));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		getContentPane().add(tfCep, "cell 3 6,growx");

		JLabel lblNomeDaMe = new JLabel("Nome da Mãe");
		lblNomeDaMe.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(lblNomeDaMe, "cell 0 7");

		JLabel lblNomeDoPai = new JLabel("Nome do Pai");
		lblNomeDoPai.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(lblNomeDoPai, "cell 2 7");

		txtNomeMae = new JTextField();
		txtNomeMae.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNomeMae.setColumns(10);
		getContentPane().add(txtNomeMae, "cell 0 8 2 1,growx");
		tfNomePai = new JTextField();
		tfNomePai.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfNomePai.setColumns(10);
		getContentPane().add(tfNomePai, "cell 2 8 2 1,growx");

		JLabel LBLCPF = new JLabel("Dt. Nasc.");
		LBLCPF.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(LBLCPF, "cell 0 9");

		JLabel label = new JLabel("C.P.F.");
		label.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(label, "cell 1 9");

		JLabel lblRg = new JLabel("R.G.");
		lblRg.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(lblRg, "cell 2 9");

		JLabel lblNiss = new JLabel("C.T.P.S Nº");
		lblNiss.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(lblNiss, "cell 3 9");
		dtNasc = new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
		dtNasc.setFont(new Font("Tahoma", Font.BOLD, 11));
		getContentPane().add(dtNasc, "cell 0 10,growx");

		txtCpf = new JFormattedTextField();
		try {
			txtCpf.setFormatterFactory(
					new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(txtCpf, "cell 1 10,growx");

		txtRG = new JTextField();
		txtRG.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtRG.setColumns(10);
		getContentPane().add(txtRG, "cell 2 10,growx");

		tfCtps = new JTextField();
		tfCtps.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfCtps.setColumns(10);
		getContentPane().add(tfCtps, "cell 3 10,growx");
		JLabel lblNewLabel = new JLabel("Telefone Celular");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(lblNewLabel, "cell 0 11");

		JLabel lblTelRecado = new JLabel("Telefone Fixo");
		lblTelRecado.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(lblTelRecado, "cell 1 11");

		txtTel1 = new JFormattedTextField();
		txtTel1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		try {
			txtTel1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
					new javax.swing.text.MaskFormatter("(##)9 ####-####")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(lblEmail, "cell 2 11 2 1");
		getContentPane().add(txtTel1, "cell 0 12,growx");

		txtTel2 = new JFormattedTextField();
		txtTel2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		try {
			txtTel2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
					new javax.swing.text.MaskFormatter("(##)9 ####-####")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		getContentPane().add(txtTel2, "cell 1 12,growx");

		tfEmail = new JTextField();
		tfEmail.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfEmail.setColumns(10);
		getContentPane().add(tfEmail, "cell 2 12 2 1,growx");

		JLabel lblNewLabel_1 = new JLabel("Carga Horaria:");
		getContentPane().add(lblNewLabel_1, "cell 0 13");

		JLabel lblHoraInicial = new JLabel("Hora Inicial");
		getContentPane().add(lblHoraInicial, "cell 0 14");

		JLabel lblHoraFinal = new JLabel("Hora Final");
		getContentPane().add(lblHoraFinal, "cell 1 14");

		JLabel lblIntervalo = new JLabel("Intervalo");
		getContentPane().add(lblIntervalo, "cell 2 14");

		tfHoraInicial = new JFormattedTextField();
		tfHoraInicial.setFont(new Font("Dialog", Font.PLAIN, 14));
		try {
			tfHoraInicial.setFormatterFactory(
					new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##:00")));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		getContentPane().add(tfHoraInicial, "cell 0 15,growx");

		tfHoraFinal = new JFormattedTextField();
		tfHoraFinal.setFont(new Font("Dialog", Font.PLAIN, 14));
		try {
			tfHoraFinal.setFormatterFactory(
					new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##:00")));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		getContentPane().add(tfHoraFinal, "cell 1 15,growx");

		tfIntervalo = new JFormattedTextField();
		tfIntervalo.setFont(new Font("Dialog", Font.PLAIN, 14));
		try {
			tfIntervalo.setFormatterFactory(
					new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##:00")));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		getContentPane().add(tfIntervalo, "cell 2 15,growx");
		setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { txtNome, cmbSexo, txtEndereco, tfNumero, tfBairro, tfCidade, tfEstado, tfCep,
						txtNomeMae, tfNomePai, dtNasc, dtNasc.getCalendarButton(), txtCpf, txtRG, tfCtps, txtTel1,
						txtTel2, tfEmail, tfHoraInicial, tfHoraFinal, tfIntervalo, btnSalvar, btnCancelar }));
		if (isAlter) {
			setTitle("Alterar cadastro de " + func.getNome());
			preencheCampos();

		} else {
			setTitle("Cadastrar novo funcionário");
		}
		txtNome.requestFocus();
	}

	public void preencheCampos() {
		txtNome.setText(func.getNome());
		if (func.getSexo() == 'M') {
			cmbSexo.setSelectedItem("Masculino");
		} else if (func.getSexo() == 'F') {
			cmbSexo.setSelectedItem("Feminino");
		} else if (func.getSexo() == 'O') {
			cmbSexo.setSelectedItem("Outros");
		}
		txtCpf.setText(func.getCpf());
		txtEndereco.setText(func.getEndereco().getLogradouro());
		txtNomeMae.setText(func.getNomeMae());
		txtRG.setText(func.getRg());
		txtTel1.setText(func.getTelefoneCelular());
		txtTel2.setText(func.getTelefoneFixo());
		tfBairro.setText(func.getEndereco().getBairro());
		tfCep.setText(func.getEndereco().getCep());
		tfCidade.setText(func.getEndereco().getCidade());
		tfCtps.setText(func.getCtps());
		tfEmail.setText(func.getEmail());
		tfEstado.setText(func.getEndereco().getUf());
		tfNomePai.setText(func.getNomePai());
		tfNumero.setText(func.getEndereco().getNumero());
		if (func.getDataNasc() != null)
			dtNasc.setDate(Date.valueOf(func.getDataNasc()));
		tfHoraInicial.setText(String.valueOf(func.getCargaHoraria().getHoraInicio()));
		tfHoraFinal.setText(String.valueOf(func.getCargaHoraria().getHoraFim()));
		tfIntervalo.setText(String.valueOf(func.getCargaHoraria().getIntervalo()));
	}

}
