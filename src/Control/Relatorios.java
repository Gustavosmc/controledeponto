package Control;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JOptionPane;

import View.Main;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JasperViewer;

public class Relatorios {

	private static InputStream path;

	public static void callRelatorio(String nameRel, String title, HashMap<String, Object> filtro,
			Boolean printNoView) {

		try {
			// if(FormMain.getUser().getEmitirRelatorio() == 0){
			// JOptionPane.showMessageDialog(null, "Voc� n�o tem autoriza��o
			// para esta a��o \n Procure o Administrador(a)","Permiss�o
			// Negada",JOptionPane.WARNING_MESSAGE);
			// return;
			// }
			Connection cn = Conexao.Connect.getConexao();
			path = Main.class.getClassLoader().getResourceAsStream("reports/" + nameRel + ".jasper");
			JasperPrint print = JasperFillManager.fillReport(path, filtro, cn);
			if (printNoView == true && (nameRel == "rptProtocolodeAtendimento2" || nameRel == "rptProtocolodeAtendimento")) {
				JasperPrintManager.printPage(print, 0, false);
			} else {
				JasperViewer viewr = new JasperViewer(print, false);
				viewr.setVisible(true);
				viewr.setTitle(title);
				// viewr.setExtendedState(Frame.MAXIMIZED_BOTH);
			}
			try {
				cn.close();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, ex.getCause());
			}

		} catch (JRException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

}
