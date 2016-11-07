package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Conexao.Connect;
import Model.TipoUsers;

public class TipoUserDao {

	public void salvar(TipoUsers tipoUser) {
		String sql = "";
		Connection cn = null;
		PreparedStatement stm = null;
		if (tipoUser.getId() > 0) {
			sql = "update TIPO_USERS set TIPO = ? where ID = ?";
		} else {
			sql = "insert into TIPO_USERS values (null,?)";
		}
		try {
			cn = Connect.getConexao();
			stm = cn.prepareStatement(sql);
			stm.setString(1, tipoUser.getTipo());
			if (tipoUser.getId() > 0) {
				stm.setInt(2, tipoUser.getId());
			}
			stm.execute();
			stm.close();
			cn.close();
		} catch (SQLException e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(null, "Erro ao gravar tipo de usuario no banco:\n " + e);
		}
		if (tipoUser.getId() > 0) {
			JOptionPane.showMessageDialog(null, "Tipo de usuario " + tipoUser.getTipo() + " atualizado com sucesso...");
		} else {
			JOptionPane.showMessageDialog(null, "Tipo de usuario " + tipoUser.getTipo() + " cadastrado com sucesso...");
		}

	}

	public List<TipoUsers> listAllTipoUsers() {
		List<TipoUsers> h = new ArrayList<>();
		Connection cn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		cn = Connect.getConexao();
		String sql = "SELECT TIPO_USERS.ID,TIPO_USERS.TIPO FROM TIPO_USERS";

		try {
			stm = cn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				TipoUsers tipoUser = new TipoUsers();
				try {
					tipoUser.setId(rs.getInt("ID"));
					tipoUser.setTipo(rs.getString("TIPO"));
				} catch (Exception e) {
				}
				h.add(tipoUser);
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
