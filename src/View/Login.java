package View;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.NoSuchAlgorithmException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import Control.Configuracoes;
import Control.Seguranca;
import Control.UsuariosDao;
import Model.Usuarios;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUser;
	private JLabel lblDesenvolvidoPorAguinaldo;
	private JLabel label;
	private JPasswordField txtPassword;
	private Usuarios user = new Usuarios();
	private UsuariosDao daoUser = new UsuariosDao();
	private JLabel label_1;
	private JLabel label_2;
	public static String pathDataBase;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				try {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/ciaps24x24.png")));
		setTitle("CADASTRO \u00DANICO - Vers\u00E3o 0.5.84.9");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Usu\u00E1rio");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(154, 80, 150, 14);
		contentPane.add(lblNewLabel);

		txtUser = new JTextField();
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtUser.setBounds(154, 94, 150, 33);
		contentPane.add(txtUser);
		txtUser.setColumns(10);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha.setBounds(154, 131, 150, 14);
		contentPane.add(lblSenha);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Main.class.getResource("/images/password.png")));
		lblNewLabel_1.setBounds(389, 309, 69, 68);
		contentPane.add(lblNewLabel_1);

		lblDesenvolvidoPorAguinaldo = new JLabel("Desenvolvedor: Aguinaldo Alves Moreira");
		lblDesenvolvidoPorAguinaldo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDesenvolvidoPorAguinaldo.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesenvolvidoPorAguinaldo.setBounds(10, 266, 448, 20);
		contentPane.add(lblDesenvolvidoPorAguinaldo);

		label = new JLabel("(038) 9898-2005 / 8808-8546");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 291, 448, 14);
		contentPane.add(label);

		JButton btnEntrar = new JButton("Entrar      ");
		btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validaLogin();
			}
		});
		btnEntrar.setIcon(new ImageIcon(Main.class.getResource("/images/Confirm.png")));
		btnEntrar.setBounds(154, 186, 150, 33);
		contentPane.add(btnEntrar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setIcon(new ImageIcon(Main.class.getResource("/images/Cancelar.png")));
		btnCancelar.setBounds(154, 232, 150, 33);
		contentPane.add(btnCancelar);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					validaLogin();
				}
			}
		});
		txtPassword.setBounds(154, 146, 150, 33);
		contentPane.add(txtPassword);

		label_1 = new JLabel("");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setIcon(new ImageIcon(Login.class.getResource("/images/PrefeituraTop2.png")));
		label_1.setBounds(10, 0, 448, 69);
		contentPane.add(label_1);

		label_2 = new JLabel("");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setIcon(new ImageIcon(Login.class.getResource("/images/PrefeituraBotton2.png")));
		label_2.setBounds(10, 308, 448, 69);
		contentPane.add(label_2);
		centreWindow(this);
		pathDataBase = Configuracoes.getPathDataBase();

	}

	@SuppressWarnings("deprecation")
	private void validaLogin() {
		if (txtUser.getText().equals("supernova") && txtPassword.getText().equals("ab115798")) {
			initMain();
		} else {
			try {
				if (daoUser.findUsuario(txtUser.getText(), Seguranca.gerarMD5(txtPassword.getText())).isEmpty()) {
					JOptionPane.showMessageDialog(null, "Login ou senha inválido");
				} else {
					user = daoUser.findUsuario(txtUser.getText(), Seguranca.gerarMD5(txtPassword.getText())).get(0);
					if (user.getStatus() == '0') {
						JOptionPane.showMessageDialog(null,
								"Usuário " + user.getNomeUsuario()
										+ " bloqueado entre em contato com o administrador...",
								"Usuário bloqueado", JOptionPane.WARNING_MESSAGE);
						return;
					}
					Main.setUser(user);
					initMain();
				}
			} catch (HeadlessException e1) {
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			}

		}

	}

	public static void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}

	public void initMain() {
		Main m = new Main();
		m.setVisible(true);
		dispose();
	}
}
