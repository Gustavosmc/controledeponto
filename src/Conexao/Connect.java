package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

public class Connect {
	public static Connection getConexao() {

		try {
			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		try {
			Properties props = new Properties();
			props.put("user", "postgres");
			props.put("password", "115798");
			props.put("ENCODING", "UTF8");
			props.put("LC_COLLATE","pt_BR.UTF-8");
			props.put("LC_CTYPE","pt_BR.UTF-8");
			// System.err.println("Conectado com sucesso...");
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/controledeponto", props);
			// return
			// DriverManager.getConnection("jdbc:firebirdsql://"+CULogin.pathDataBase,
			// props);
		} catch (SQLException ex) {
			System.err.println("erro ao conectar ao banco...\n" + ex.getCause());
			System.exit(0);
		}
		System.err.println("Conectado com sucesso...");
		return null;
	}

}
