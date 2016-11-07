package View;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;

import Control.ConfigBackupDao;
import Model.ConfigBackup;
import net.miginfocom.swing.MigLayout;

public class Backup extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	JButton btnEditar;
	JButton btnNovo;
	private ConfigBackupDao dao = new ConfigBackupDao();
	private List<ConfigBackup> backup;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JTextField txtLocalSgdb;
	private JTextField txtLocalBkp;
	private JLabel lblLocalDoBanco;
	private JLabel lblCaminhoDoBackup;
	private JTextField txtLocalRest;
	private JLabel lblInfo;
	private JButton button;
	private JButton button_1;
	private JButton button_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Backup frame = new Backup();
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
	public Backup() {
		setFrameIcon(new ImageIcon(Main.class.getResource("/images/ciaps24x24.png")));
		setTitle("GERENCIADOR DE BACKUP");
		setClosable(true);
		setBounds(100, 100, 479, 433);
		getContentPane().setLayout(new MigLayout("", "[400px:400px,grow]", "[33px][20px][][][][][][330px,grow][]"));

		txtLocalSgdb = new JTextField();
		getContentPane().add(txtLocalSgdb, "flowx,cell 0 2,growx");
		txtLocalSgdb.setColumns(10);

		lblLocalDoBanco = new JLabel("Caminho do Banco de Dados");
		getContentPane().add(lblLocalDoBanco, "cell 0 3");

		txtLocalBkp = new JTextField();
		getContentPane().add(txtLocalBkp, "flowx,cell 0 4,growx");
		txtLocalBkp.setColumns(10);

		lblCaminhoDoBackup = new JLabel("Caminho do Backup");
		getContentPane().add(lblCaminhoDoBackup, "cell 0 5");

		txtLocalRest = new JTextField();
		txtLocalRest.setColumns(10);
		getContentPane().add(txtLocalRest, "flowx,cell 0 6,growx");

		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 0 7,grow");

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		getContentPane().add(toolBar, "cell 0 0,growx,aligny top");

		btnNovo = new JButton("EFETUAR");
		toolBar.add(btnNovo);
		btnNovo.setPreferredSize(new Dimension(110, 35));
		btnNovo.setMinimumSize(new Dimension(110, 35));
		btnNovo.setMaximumSize(new Dimension(110, 35));
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				makeBackup();
			}
		});
		btnNovo.setIcon(new ImageIcon(Main.class.getResource("/images/Confirm.png")));

		btnEditar = new JButton("RESTAURAR");
		toolBar.add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeRestaurar();
			}
		});
		btnEditar.setPreferredSize(new Dimension(110, 35));
		btnEditar.setMinimumSize(new Dimension(110, 35));
		btnEditar.setMaximumSize(new Dimension(110, 35));
		btnEditar.setIcon(new ImageIcon(Main.class.getResource("/images/Edit.png")));

		JLabel lblDescrio = new JLabel("Caminho do SGDB");
		getContentPane().add(lblDescrio, "cell 0 1,alignx left,aligny center");
		backup = dao.listConfigBackup();
		txtLocalSgdb.setText(backup.get(0).getLocalSgdb());
		txtLocalBkp.setText(backup.get(0).getLocalBkp());
		txtLocalRest.setText(backup.get(0).getLocalRest());

		lblInfo = new JLabel("");
		getContentPane().add(lblInfo, "cell 0 8,alignx left");

		button = new JButton("...");
		button.setMaximumSize(new Dimension(30, 30));
		button.setMinimumSize(new Dimension(20, 20));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Banco de Dados (*.fdb)", "fdb");
				fileChooser.setDialogTitle("Local do Banco de Dados");
				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser.setFileFilter(filter);
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					txtLocalBkp.setText(selectedFile.getPath());
				}
			}
		});
		getContentPane().add(button, "cell 0 4");

		button_1 = new JButton("...");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("Arquivos de Restaura��o (*.fbk)",
						"fbk");
				chooser.setFileFilter(xmlFilter);
				chooser.setDialogTitle("Local do Backup");
				chooser.setAcceptAllFileFilterUsed(false);
				int result = chooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = chooser.getSelectedFile();
					txtLocalRest.setText(selectedFile.getPath());
					if (!txtLocalRest.getText()
							.substring(txtLocalRest.getText().length() - 4, txtLocalRest.getText().length())
							.toUpperCase().equals(".FBK"))
						txtLocalRest.setText(txtLocalRest.getText() + ".fbk");
				}
			}
		});
		button_1.setMinimumSize(new Dimension(20, 20));
		button_1.setMaximumSize(new Dimension(30, 30));
		getContentPane().add(button_1, "cell 0 6");

		button_2 = new JButton("...");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				//FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("Executaveis (*)","");
				//chooser.setFileFilter(xmlFilter);
				chooser.setDialogTitle("Local do SGDB");
				chooser.setAcceptAllFileFilterUsed(false);
				int result = chooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = chooser.getSelectedFile();
					txtLocalSgdb.setText(selectedFile.getPath());
				}
			}
		});
		button_2.setMinimumSize(new Dimension(20, 20));
		button_2.setMaximumSize(new Dimension(30, 30));
		getContentPane().add(button_2, "cell 0 2");

	}

	protected void makeBackup() {
		if (txtLocalBkp.getText().isEmpty() || txtLocalRest.getText().isEmpty() || txtLocalSgdb.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Todos os campos são de preenchimento obrigatório!");
			return;
		}
		textArea.removeAll();
		Scanner s = null;
		ConfigBackup b = new ConfigBackup();
		b.setLocalSgdb(txtLocalSgdb.getText());
		b.setLocalBkp(txtLocalBkp.getText());
		b.setLocalRest(txtLocalRest.getText());
		backup.set(0, b);
		try {
			s = new Scanner(
					Runtime.getRuntime()
							.exec(backup.get(0).getLocalSgdb() + " -v -t -user sysdba -pas masterkey "
									+ backup.get(0).getLocalBkp() + " " + backup.get(0).getLocalRest())
							.getInputStream());
			dao.salvar(b);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		lblInfo.setText(null);
		textArea.removeAll();
		while (s.hasNext()) {
			lblInfo.setText(s.nextLine());
			textArea.setText(textArea.getText() + "\n" + lblInfo.getText());
		}
	}

	protected void makeRestaurar() {
		if (txtLocalBkp.getText().isEmpty() || txtLocalRest.getText().isEmpty() || txtLocalSgdb.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Todos os campos são de preenchimento obrigatório!");
			return;
		}
		textArea.removeAll();
		Scanner s = null;
		ConfigBackup b = new ConfigBackup();
		b.setLocalSgdb(txtLocalSgdb.getText());
		b.setLocalBkp(txtLocalBkp.getText());
		b.setLocalRest(txtLocalRest.getText());
		backup.set(0, b);
		try {
//			s = new Scanner(Runtime.getRuntime()
//					.exec(backup.get(0).getLocalSgdb() + " -v -t -user sysdba -pas masterkey -r -p 4096 -o -rep "
//							+ backup.get(0).getLocalRest() + " " + backup.get(0).getLocalBkp())
//					.getInputStream());
//			dao.salvar(b);
			s = new Scanner(Runtime.getRuntime().exec("uname -a").getInputStream());
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		lblInfo.setText(null);
		textArea.removeAll();
		while (s.hasNext()) {
//			lblInfo.setText(s.nextLine());
//			textArea.setText(textArea.getText() + "\n" + lblInfo.getText());
			System.err.println(s);
		}
	}

}
