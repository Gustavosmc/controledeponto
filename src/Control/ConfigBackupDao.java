package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.ConfigBackup;
import Conexao.Connect;

public class ConfigBackupDao {

	public void salvar(ConfigBackup backup) {
		String sql = "";
		Connection cn = null;
		PreparedStatement stm = null;
		sql = "update configbackup set localsgdb = ?, localbkp = ?, localrest = ? where idconfigbackup = 1";
		try {
			cn = Connect.getConexao();
			stm = cn.prepareStatement(sql);
			stm.setString(1, backup.getLocalSgdb());
			stm.setString(2, backup.getLocalBkp());
			stm.setString(3, backup.getLocalRest());
			stm.execute();
			stm.close();
			cn.close();
		} catch (SQLException e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(null, "Erro ao gravar no banco " + e);
		}
		
	}

	public List<ConfigBackup> listConfigBackup() {
		List<ConfigBackup> h = new ArrayList<>();
		Connection cn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		cn = Connect.getConexao();
		String sql = "select * from configbackup where idconfigbackup = 1";

		try {
			stm = cn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				ConfigBackup u = new ConfigBackup();
				try {
					u.setLocalBkp(rs.getString("localbkp"));
					u.setLocalRest(rs.getString("localrest"));
					u.setLocalSgdb(rs.getString("localsgdb"));
				} catch (Exception e) {
				}
				h.add(u);
			}
			rs.close();
			stm.close();
		} catch (SQLException ex) {

		}
		try {
			cn.close();
		} catch (SQLException ex) {

		}
		return h;
	}

}
