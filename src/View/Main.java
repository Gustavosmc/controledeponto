package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import Control.FormIsVisible;
import Model.Usuarios;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private JPanel contentPane;
	public static JDesktopPane desktopPane;
	JInternalFrame frmFuncionarios = null;
	public static JInternalFrame frmPonto = null;
	public static JInternalFrame frmAtendimento = null;
	JInternalFrame frmBackup = null;
	JInternalFrame frmConfig = null;
	public static JInternalFrame frmCadFuncionario;
	private static Usuarios user = new Usuarios();
	private JLabel lblStatusBar;

	public static Usuarios getUser() {
		return user;
	}

	public static void setUser(Usuarios user) {
		Main.user = user;
	}

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
					Main frame = new Main();
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
	public Main() {

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "Tem certeza que deseja fechar o sistema ?", "Fechar",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/ciaps24x24.png")));
		try {
			setTitle("CONTROLE DE PONTO");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		desktopPane = new JDesktopPane() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Color rs = new Color(240, 240, 240);
				g.setColor(rs);
				g.fillRect(0, 0, getWidth(), getHeight());
			}
		};
		desktopPane.setBackground(new Color(240, 240, 240));
		contentPane.add(desktopPane, BorderLayout.CENTER);
		desktopPane.setLayout(null);
		int width = 389;
		int height = 342;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = ((screen.height - 200) - height) / 2;

		JLabel lblPropaganda = new JLabel("");

		lblPropaganda.setBounds(x, y, width, height);
		desktopPane.add(lblPropaganda);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		contentPane.add(toolBar, BorderLayout.NORTH);

		JButton btnNewButton = new JButton("PONTO");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton.setMnemonic('P');
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (FormIsVisible.isVisible("View.Ponto")) {

				}
				if (!FormIsVisible.isVisible("View.Ponto")) {
					frmPonto = new Ponto();
					frmPonto.setVisible(true);
					frmPonto.show();
					desktopPane.add(frmPonto);

					try {
						frmPonto.setSelected(true);
						frmPonto.setMaximum(true);
					} catch (PropertyVetoException ex) {

					}
				} else {
					try {
						frmPonto.setSelected(true);
					} catch (PropertyVetoException ex) {

					}
				}
			}
		});

		JButton btnBeneficirios = new JButton("CADASTRO DE FUNCIONÁRIOS");
		btnBeneficirios.setFont(new Font("Dialog", Font.BOLD, 14));
		btnBeneficirios.setMnemonic('F');
		btnBeneficirios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!FormIsVisible.isVisible("View.Funcionarios")) {
					frmFuncionarios = new Funcionarios();
					frmFuncionarios.setVisible(true);
					frmFuncionarios.show();
					desktopPane.add(frmFuncionarios);
					try {
						frmFuncionarios.setSelected(true);
						frmFuncionarios.setMaximum(true);
					} catch (PropertyVetoException ex) {

					}
				} else {
					try {
						frmFuncionarios.setSelected(true);
					} catch (PropertyVetoException ex) {

					}
				}
			}
		});
		btnBeneficirios.setIcon(new ImageIcon(Main.class.getResource("/images/Group4_Meeting_Light.png")));
		btnBeneficirios.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnBeneficirios.setVerticalAlignment(SwingConstants.BOTTOM);
		btnBeneficirios.setHorizontalTextPosition(SwingConstants.CENTER);
		toolBar.add(btnBeneficirios);
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton.setIcon(new ImageIcon(Main.class.getResource("/images/Abordagens.png")));
		toolBar.add(btnNewButton);

		JButton btnConfiguraes = new JButton("CONFIGURAÇÕES DE USUÁRIOS");
		btnConfiguraes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!FormIsVisible.isVisible("View.Config")) {
					frmConfig = new Configuracoes();
					frmConfig.setVisible(true);
					frmConfig.show();
					Main.desktopPane.add(frmConfig);
					Main.centralizar(frmConfig);
					try {
						frmConfig.setSelected(true);

					} catch (PropertyVetoException ex) {
						JOptionPane.showMessageDialog(null, "Erro ao criar form Edit Usuarios");
					}
				} else {
					frmConfig.dispose();
					frmConfig = new Configuracoes();
					frmConfig.setVisible(true);
					frmConfig.show();
					Main.desktopPane.add(frmConfig);
					Main.centralizar(frmConfig);
					try {
						frmConfig.setSelected(true);
					} catch (PropertyVetoException ex) {
						JOptionPane.showMessageDialog(null, "Erro ao criar form Edit Usuarios");
					}
				}
			}
		});
		btnConfiguraes.setIcon(new ImageIcon(Main.class.getResource("/images/configuracoes.png")));
		btnConfiguraes.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnConfiguraes.setVerticalAlignment(SwingConstants.BOTTOM);
		btnConfiguraes.setMnemonic('P');
		btnConfiguraes.setHorizontalTextPosition(SwingConstants.CENTER);
		btnConfiguraes.setFont(new Font("Dialog", Font.BOLD, 14));
		toolBar.add(btnConfiguraes);

		JButton btnGerenciadorDeBackup = new JButton("GERENCIADOR DE BACKUP");
		btnGerenciadorDeBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!FormIsVisible.isVisible("View.Backup")) {
					frmBackup = new Backup();
					frmBackup.setVisible(true);
					frmBackup.show();
					Main.desktopPane.add(frmBackup);
					Main.centralizar(frmBackup);
					try {
						frmBackup.setSelected(true);

					} catch (PropertyVetoException ex) {

					}
				} else {
					frmBackup.dispose();
					frmBackup = new Backup();
					frmBackup.setVisible(true);
					frmBackup.show();
					Main.desktopPane.add(frmBackup);
					Main.centralizar(frmBackup);
					try {
						frmBackup.setSelected(true);
					} catch (PropertyVetoException ex) {

					}
				}
			}
		});
		btnGerenciadorDeBackup.setIcon(new ImageIcon(Main.class.getResource("/images/backup.png")));
		btnGerenciadorDeBackup.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnGerenciadorDeBackup.setVerticalAlignment(SwingConstants.BOTTOM);
		btnGerenciadorDeBackup.setMnemonic('P');
		btnGerenciadorDeBackup.setHorizontalTextPosition(SwingConstants.CENTER);
		btnGerenciadorDeBackup.setFont(new Font("Dialog", Font.BOLD, 14));
		toolBar.add(btnGerenciadorDeBackup);

		JLabel lblUsuarioLogado = new JLabel("");
		lblUsuarioLogado.setFont(new Font("Tahoma", Font.PLAIN, 20));
		toolBar.add(lblUsuarioLogado);

		JMenuItem menuItem = new JMenuItem("");
		menuItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				btnBeneficirios.doClick();
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.ALT_MASK));
		menuItem.setPreferredSize(new Dimension(0, 0));
		menuItem.setMaximumSize(new Dimension(0, 0));
		toolBar.add(menuItem);

		JMenuItem menuItem_3 = new JMenuItem("");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		menuItem_3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.ALT_MASK));
		menuItem_3.setPreferredSize(new Dimension(0, 0));
		menuItem_3.setMaximumSize(new Dimension(0, 0));
		toolBar.add(menuItem_3);

		JMenuItem menuItem_2 = new JMenuItem("");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuItem_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.ALT_MASK));
		menuItem_2.setPreferredSize(new Dimension(0, 0));
		menuItem_2.setMaximumSize(new Dimension(0, 0));
		toolBar.add(menuItem_2);

		JMenuItem menuItem_1 = new JMenuItem("");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.doClick();
			}
		});
		menuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.ALT_MASK));
		menuItem_1.setPreferredSize(new Dimension(0, 0));
		menuItem_1.setMaximumSize(new Dimension(0, 0));
		toolBar.add(menuItem_1);
		setLocationRelativeTo(null);
		setExtendedState(MAXIMIZED_BOTH);
		user.setId(1);
		user.setNomeUsuario("Aguinaldo");
		if (user.getId() > 0) {
			// lblUsuarioLogado.setText(" Seja bem vindo(a), " +
			// user.getNomeUsuario());
		}

		Color color = new Color(240, 240, 240);
		desktopPane.setBackground(color);

		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		contentPane.add(statusPanel, BorderLayout.SOUTH);
		statusPanel.setPreferredSize(new Dimension(contentPane.getWidth(), 22));
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		JLabel statusLabel = new JLabel("Usuário logado: Aguinaldo");
		statusLabel.setFont(new Font("DialogInput", Font.PLAIN, 14));
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		statusPanel.add(statusLabel);

		JSeparator separator = new JSeparator();
		statusPanel.add(separator);

		lblStatusBar = new JLabel("");

		lblStatusBar.setFont(new Font("DialogInput", Font.PLAIN, 14));
		lblStatusBar.setHorizontalAlignment(SwingConstants.RIGHT);
		// lblMainDate.setText(DataConverter.dateToString(DataConverter.getDataSistema()));
		statusPanel.add(lblStatusBar);
		controlaTempo();

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}

	public static void centralizar(JInternalFrame fr) {
		Dimension d = fr.getDesktopPane().getSize();
		fr.setLocation((d.width - fr.getSize().width) / 2, (d.height - fr.getSize().height) / 2);
	}

	public void controlaTempo() {
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						String data = sdf.format(new Date());
						lblStatusBar.setText(data);
						sleep(1000);
					} catch (InterruptedException ex) {
						Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		}.start();
	}
}
