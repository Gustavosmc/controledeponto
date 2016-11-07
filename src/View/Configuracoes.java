package View;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;

import Control.Seguranca;
import Control.TipoUserDao;
import Control.UsuariosDao;
import Model.TipoUsers;
import Model.Usuarios;
import TableModel.UsuariosTblModel;
import net.miginfocom.swing.MigLayout;

public class Configuracoes extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPasswordField txtSenhaAtual;
	private JPasswordField txtNovaSenha;
	private JPasswordField txtRepitaNovaSenha;
	private JTextField txtUsuairo;
	private static JTable table;
	JButton btnEditar;
	JButton btnNovo;
	@SuppressWarnings("rawtypes")
	public static JComboBox cmbTipoUsuario;
	public static List<TipoUsers> listTipoUsers = new ArrayList<>();

	private static UsuariosDao daoUser = new UsuariosDao();
	private static TipoUserDao daoTipoUser = new TipoUserDao();
	public static Usuarios user = new Usuarios();
	public static TipoUsers tipoUser = new TipoUsers();
	public static boolean isAlter = false;
	private static UsuariosTblModel tbl;
	private JTextField txtNomeCompleto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Configuracoes frame = new Configuracoes();
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
	@SuppressWarnings("rawtypes")
	public Configuracoes() {
		setTitle("CONFIGURA\u00C7\u00D5ES...");
		setFrameIcon(new ImageIcon(Main.class.getResource("/images/ciaps24x24.png")));
		setClosable(true);
		setBounds(100, 100, 592, 478);
		getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 0 0,grow");

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		scrollPane.setViewportView(tabbedPane);

		JPanel pnlAlterPassword = new JPanel();
		tabbedPane.addTab("Alterar Senha", null, pnlAlterPassword, null);
		pnlAlterPassword.setLayout(new MigLayout("", "[][][400px,grow][20px,grow]", "[][][][][][][][][]"));

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		pnlAlterPassword.add(toolBar, "flowx,cell 2 0 2 1,growx,aligny top");

		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {

				try {
					if (Seguranca.gerarMD5(txtSenhaAtual.getText()).equals(Main.getUser().getPassword())) {
						if (txtNovaSenha.getText().isEmpty() || txtRepitaNovaSenha.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null,
									"Os campos Nova senha e repetir senha s�o de preenchimento obrigat�rio!");
							return;
						}
						if (txtNovaSenha.getText().equals(txtRepitaNovaSenha.getText())) {
							user.setId(Main.getUser().getId());
							user.setNomeUsuario(Main.getUser().getNomeUsuario());
							user.setPassword(Seguranca.gerarMD5(txtNovaSenha.getText()));
							daoUser.atualizarSenha(user);
							Main.getUser().setPassword(user.getPassword());
							txtNovaSenha.setText("");
							txtRepitaNovaSenha.setText("");
							txtSenhaAtual.setText("");
						} else {
							JOptionPane.showMessageDialog(null, "As senhas n�o conferem!");
							txtRepitaNovaSenha.requestFocus();
						}
					} else {
						JOptionPane.showMessageDialog(null, "A senha atual informada � diferente da atual");
						txtSenhaAtual.requestFocus();
					}
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(Configuracoes.class.getResource("/images/Confirm.png")));
		btnNewButton.setMinimumSize(new Dimension(110, 35));
		btnNewButton.setMaximumSize(new Dimension(110, 35));
		toolBar.add(btnNewButton);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setIcon(new ImageIcon(Configuracoes.class.getResource("/images/Cancelar.png")));
		btnCancelar.setMinimumSize(new Dimension(110, 35));
		btnCancelar.setMaximumSize(new Dimension(110, 35));
		toolBar.add(btnCancelar);

		JLabel lblInfoUser = new JLabel("\u00A0");
		pnlAlterPassword.add(lblInfoUser, "cell 2 1 2 1");

		JLabel lblSenhaAtual = new JLabel("Senha Atual");
		lblSenhaAtual.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlAlterPassword.add(lblSenhaAtual, "cell 2 3");

		txtSenhaAtual = new JPasswordField();
		txtSenhaAtual.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlAlterPassword.add(txtSenhaAtual, "cell 2 4,growx");

		JLabel lblNovaSenha = new JLabel("Nova Senha");
		lblNovaSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlAlterPassword.add(lblNovaSenha, "cell 2 5");

		txtNovaSenha = new JPasswordField();
		txtNovaSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlAlterPassword.add(txtNovaSenha, "cell 2 6,growx");

		JLabel lblRepitaANova = new JLabel("Repita a Nova Senha");
		lblRepitaANova.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlAlterPassword.add(lblRepitaANova, "cell 2 7");

		txtRepitaNovaSenha = new JPasswordField();
		txtRepitaNovaSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlAlterPassword.add(txtRepitaNovaSenha, "cell 2 8,growx");

		JPanel pnlManutencao = new JPanel();
		tabbedPane.addTab("Manuten��o/Cadastro de Usu�rios do Sistema", null, pnlManutencao, null);
		pnlManutencao.setLayout(new MigLayout("", "[][grow][][][100px:n,grow][]", "[][][][][][][][::180px,grow]"));

		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		pnlManutencao.add(toolBar_1, "flowx,cell 1 0");

		btnNovo = new JButton("NOVO");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (btnNovo.getText().equals("NOVO")) {
					limpaCheckBoxies();
					btnNovo.setText("SALVAR");
					btnNovo.setIcon(new ImageIcon(Main.class.getResource("/images/Confirm.png")));
					txtUsuairo.setEnabled(true);
					txtNomeCompleto.setEnabled(true);
					txtUsuairo.requestFocus();
					btnEditar.setText("CANCELAR");
					btnEditar.setIcon(new ImageIcon(Main.class.getResource("/images/Cancelar.png")));
				} else {
					if (cmbTipoUsuario.getSelectedIndex() > 0) {
						tipoUser = listTipoUsers.get(cmbTipoUsuario.getSelectedIndex() - 1);
					} else {
						JOptionPane.showMessageDialog(null, "Selecione um Tipo para o usuario");
						cmbTipoUsuario.requestFocus();
						return;
					}
					if (txtUsuairo.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Campo Usu�rio de preenchimento obrigatorio!", "Aten��o",
								JOptionPane.WARNING_MESSAGE);
						txtUsuairo.requestFocus();
						return;
					}
					if (txtNomeCompleto.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Campo Nome completo de preenchimento obrigatorio!",
								"Aten��o", JOptionPane.WARNING_MESSAGE);
						txtNomeCompleto.requestFocus();
						return;
					} else {
						if (isAlter) {
							if (user != null) {
								// if(daoUser.findUserToUpdatePermissao(txtUsuairo.getText(),user.getId()).isEmpty())
								// {
								// u.setUserName(txtUsuairo.getText());
								// u.setTipoUsers(listTipoUsers.get(cmbTipoUsuario.getSelectedIndex()-1));
								// daoUser.atualizaPermissoesUsuario(u);
								// u = new Users();
							} else {
								JOptionPane.showMessageDialog(null,
										"J� tem um usu�rio cadastrado com este mesmo username", "Duplicidade !!!",
										JOptionPane.WARNING_MESSAGE);
								txtUsuairo.requestFocus();
								return;
							}
						} else {
							if (daoUser.findUsuarioByUserName(txtUsuairo.getText()).isEmpty()) {
								user.setUsername(txtUsuairo.getText());
								user.setTipoUser(tipoUser);
								try {
									user.setPassword(Seguranca.gerarMD5("123456"));
								} catch (NoSuchAlgorithmException e1) {
									JOptionPane.showMessageDialog(null, "Error ao gerar o hash MD5");
								}
								user.setId(0);
								user.setNomeUsuario(txtNomeCompleto.getText());
								user.setStatus('1');
								daoUser.salvar(user);
							} else {
								JOptionPane.showMessageDialog(null,
										"J� existe um usuario cadastrado com este username, escolha outro!",
										"Duplicidade!!!", JOptionPane.WARNING_MESSAGE);
								txtUsuairo.requestFocus();
								return;
							}
						}
						isAlter = false;
						tbl = new UsuariosTblModel(daoUser.listAllusuarios());
						refreshTbl(tbl);
						btnNovo.setText("NOVO");
						btnEditar.setText("HAB./BLOQUEAR");
						btnNovo.setIcon(new ImageIcon(Main.class.getResource("/images/Add.png")));
						btnEditar.setIcon(new ImageIcon(Main.class.getResource("/images/Edit.png")));
						txtUsuairo.setText("");
						txtUsuairo.setEnabled(false);
						txtNomeCompleto.setText("");
						txtNomeCompleto.setEnabled(false);

					}
				}
			}
		});
		btnNovo.setMinimumSize(new Dimension(60, 35));
		btnNovo.setMaximumSize(new Dimension(110, 35));
		btnNovo.setIcon(new ImageIcon(Configuracoes.class.getResource("/images/Add.png")));
		toolBar_1.add(btnNovo);

		btnEditar = new JButton("HAB./BLOQUEAR");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (Main.getUser().getTipoUser().getId() == 1) {
					if (btnEditar.getText().equals("HAB./BLOQUEAR")) {
						if (table.getSelectedRow() >= 0) {
							isAlter = true;
							user = tbl.getUsuarios(table.getSelectedRow());
							int i = 0;
							for (TipoUsers c : listTipoUsers) {
								i++;
								if (c.getId().equals(tbl.getUsuarios(table.getSelectedRow()).getTipoUser().getId())) {
									cmbTipoUsuario.setSelectedIndex(i);
									break;
								}
							}
							if (user.getStatus() == '1') {
								if (JOptionPane
										.showConfirmDialog(null,
												"Você tem certeza que deseja bloquear o usuário "
														+ user.getNomeUsuario() + " ?",
												null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
									daoUser.setStatus(tbl.getUsuarios(table.getSelectedRow()).getId(), 0);
									tbl = new UsuariosTblModel(daoUser.listAllusuarios());
									refreshTbl(tbl);
								}
							} else {
								if (JOptionPane
										.showConfirmDialog(null,
												"Você tem certeza que deseja habilitar o usuário "
														+ user.getNomeUsuario() + " ?",
												null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
									daoUser.setStatus(tbl.getUsuarios(table.getSelectedRow()).getId(), 1);
									tbl = new UsuariosTblModel(daoUser.listAllusuarios());
									refreshTbl(tbl);
								}
							}
						} else {
							JOptionPane.showMessageDialog(null, "Selecione um registro na tabela");
						}
					} else {
						btnNovo.setText("NOVO");
						btnEditar.setText("HAB./BLOQUEAR");
						btnNovo.setIcon(new ImageIcon(Main.class.getResource("/images/Add.png")));
						btnEditar.setIcon(new ImageIcon(Main.class.getResource("/images/Edit.png")));
						txtUsuairo.setText("");
						txtUsuairo.setEnabled(false);
						txtNomeCompleto.setText("");
						txtNomeCompleto.setEnabled(false);
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Você não tem autorização para esta ação \n Procure o Administrador do Sistema!",
							"Permissão Negada", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnEditar.setMaximumSize(new Dimension(110, 35));
		btnEditar.setMinimumSize(new Dimension(110, 35));
		btnEditar.setIcon(new ImageIcon(Configuracoes.class.getResource("/images/Edit.png")));
		toolBar_1.add(btnEditar);

		JLabel lblNewLabel = new JLabel("Usu\u00E1rio");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlManutencao.add(lblNewLabel, "cell 1 1");

		JLabel lblTipoDoUsurio = new JLabel("N\u00EDvel de usu\u00E1rio");
		lblTipoDoUsurio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlManutencao.add(lblTipoDoUsurio, "cell 2 1 3 1");

		txtUsuairo = new JTextField();
		txtUsuairo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlManutencao.add(txtUsuairo, "cell 1 2,growx");
		txtUsuairo.setColumns(10);

		cmbTipoUsuario = new JComboBox();
		cmbTipoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlManutencao.add(cmbTipoUsuario, "cell 2 2 3 1,growx");

		JLabel lblNomeCompleto = new JLabel("Nome completo");
		lblNomeCompleto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlManutencao.add(lblNomeCompleto, "cell 1 3");

		txtNomeCompleto = new JTextField();
		txtNomeCompleto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNomeCompleto.setColumns(10);
		pnlManutencao.add(txtNomeCompleto, "cell 1 4,growx");

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportView(table);
		pnlManutencao.add(scrollPane_1, "cell 1 7 4 1,growx");

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);
		scrollPane_1.setColumnHeaderView(table);
		scrollPane.setViewportView(tabbedPane);

		preencheCmbTipoUsers();
		txtNomeCompleto.setEnabled(false);
		txtUsuairo.setEnabled(false);
		tbl = new UsuariosTblModel(daoUser.listAllusuarios());
		refreshTbl(tbl);
		if (Main.getUser().getId() > 0) {
			lblInfoUser.setText("Usuário Conectado: " + Main.getUser().getNomeUsuario());
		} else {
			dispose();
			System.exit(0);
		}
	}

	@SuppressWarnings("unchecked")
	public static void preencheCmbTipoUsers() {
		listTipoUsers = daoTipoUser.listAllTipoUsers();
		cmbTipoUsuario.removeAllItems();
		cmbTipoUsuario.addItem("Selecione o nível do usuário");
		for (TipoUsers c : listTipoUsers) {
			cmbTipoUsuario.addItem(c.getTipo());
		}
	}

	public static void refreshTbl(UsuariosTblModel t) {

		if (t.getRowCount() > 0) {
			table.setModel(t);
			table.setFillsViewportHeight(true);
			table.getColumnModel().getColumn(0).setPreferredWidth(150);
			table.getColumnModel().getColumn(1).setPreferredWidth(100);
			table.getColumnModel().getColumn(2).setPreferredWidth(50);
			table.setRowSelectionInterval(0, 0);
		}
	}

	private void limpaCheckBoxies() {
		cmbTipoUsuario.setSelectedIndex(0);
	}
}
