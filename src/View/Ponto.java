package View;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import Control.CartaoDePontoDAO;
import Control.FormIsVisible;
import Control.FuncionarioDAO;
import net.miginfocom.swing.MigLayout;

public class Ponto extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void dispose() {
		if (FormIsVisible.isVisible("View.Ponto")) {
			try {
				Main.frmPonto.dispose();

				super.dispose();
			} catch (Exception e) {
			}

		}

	}

	@SuppressWarnings("unused")
	private static FuncionarioDAO dao = new FuncionarioDAO();
	@SuppressWarnings("unused")
	private static CartaoDePontoDAO daoPonto = new CartaoDePontoDAO();
	@SuppressWarnings("unused")
	private Integer t = 0;
	static Dimension dimension;
	private JTextField tfFunc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ponto frame = new Ponto();
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
	@SuppressWarnings({})
	public Ponto() {
		setFrameIcon(new ImageIcon(Main.class.getResource("/images/ciaps24x24.png")));
		setTitle("CONTROLE DE PONTO");
		setClosable(true);
		setBounds(100, 100, 811, 445);
		getContentPane().setLayout(new MigLayout("", "[][][][][][][grow][][grow]", "[][][][][][][][][][][][][][]"));

		JLabel lblNewLabel = new JLabel("");
		getContentPane().add(lblNewLabel, "cell 6 0");

		JLabel label = new JLabel("");
		getContentPane().add(label, "cell 6 1");

		JLabel label_1 = new JLabel("");
		getContentPane().add(label_1, "cell 6 2");

		JLabel label_2 = new JLabel("");
		getContentPane().add(label_2, "cell 6 3");

		JLabel label_3 = new JLabel("");
		getContentPane().add(label_3, "cell 6 4");

		JLabel label_4 = new JLabel("");
		getContentPane().add(label_4, "cell 6 5");

		JLabel lblNewLabel_1 = new JLabel("                                          ");
		getContentPane().add(lblNewLabel_1, "cell 1 6");

		JLabel label_5 = new JLabel("");
		getContentPane().add(label_5, "cell 6 6");

		JLabel lblDigiteOCdigo = new JLabel("Digite o código do Funcionário:");
		lblDigiteOCdigo.setFont(new Font("Dialog", Font.BOLD, 46));
		getContentPane().add(lblDigiteOCdigo, "cell 6 7");

		tfFunc = new JTextField();
		tfFunc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
////					Funcionario f = dao.getFuncionarioByCodigo(Integer.parseInt(tfFunc.getText()));
//					CartaoDePonto cp = new CartaoDePonto();
//					if (f != null) {
//						cp.setData(LocalDate.now());
//						cp.setFkFuncionario(f.getId());
//						daoPonto.salvar(cp);
//						tfFunc.setText("");
//					} else {
//						JOptionPane.showMessageDialog(null, "Funcionário não cadastrado!!!");
//						tfFunc.setText("");
//					}

				}
			}
		});
		tfFunc.setFont(new Font("Dialog", Font.PLAIN, 46));
		getContentPane().add(tfFunc, "cell 6 10 1 4,grow");
		tfFunc.setColumns(10);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { getContentPane() }));
	}

}
