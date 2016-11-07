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
import Model.Usuarios;

public class UsuariosDao {

	public void salvar(Usuarios usuario) {
		String sql = "";
		Connection cn = null;
		PreparedStatement stm = null;
		sql = "INSERT INTO USUARIO values (null,?,?,?,?,?)";
		try {
			cn = Conexao.Connect.getConexao();
			stm = cn.prepareStatement(sql);
			stm.setString(1, usuario.getUsername());
			stm.setString(2, usuario.getPassword());
			stm.setString(3, usuario.getNomeUsuario());
			stm.setString(4, String.valueOf(usuario.getStatus()));
			stm.setInt(5, usuario.getTipoUser().getId());
			stm.execute();
			stm.close();
			cn.close();
			JOptionPane.showMessageDialog(null, "Usu�rio de " + usuario.getNomeUsuario()
					+ " cadastrado com sucesso...\nSua senha � 123456, altere sua senha o mais r�pido possivel");
		} catch (SQLException e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(null, "Erro ao gravar usuario no banco:\n " + e.getMessage());
		}

	}

	public void setStatus(Integer id, Integer status) {
		String sql = "";
		Connection cn = null;
		PreparedStatement stm = null;
		sql = "update USUARIO set STATUS = ? where ID = ?";
		try {
			cn = Connect.getConexao();
			stm = cn.prepareStatement(sql);
			if (id > 0) {
				stm.setString(1, String.valueOf(status));
				stm.setInt(2, id);
				stm.execute();
				stm.close();
				cn.close();
				JOptionPane.showMessageDialog(null, "Usu�rio atualizado com sucesso...");
			}
		} catch (SQLException e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(null, "Erro ao gravar usuario no banco:\n " + e.getMessage());
		}
	}

	public void atualizarSenha(Usuarios usuario) {
		String sql = "";
		Connection cn = null;
		PreparedStatement stm = null;
		sql = "update USUARIO set SENHA = ? where ID = ?";
		try {
			cn = Connect.getConexao();
			stm = cn.prepareStatement(sql);
			if (usuario.getId() > 0) {
				stm.setString(1, usuario.getPassword());
				stm.setInt(2, usuario.getId());
				stm.execute();
				stm.close();
				cn.close();
				JOptionPane.showMessageDialog(null,
						"Senha do usu�rio " + usuario.getNomeUsuario() + " atualizada com sucesso!");
			}
		} catch (SQLException e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(null, "Erro ao gravar usuario no banco:\n " + e.getMessage());
		}
	}

	public List<Usuarios> listAllusuarios() {
		List<Usuarios> h = new ArrayList<>();
		Connection cn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		cn = Connect.getConexao();
		String sql = "SELECT TIPO_USERS.TIPO,TIPO_USERS.ID AS FKTIPOUSER,USUARIO.ID,USUARIO.USERNAME,USUARIO.NOME,"
				+ " USUARIO.SENHA,USUARIO.STATUS FROM USUARIO INNER JOIN"
				+ " TIPO_USERS ON USUARIO.FKTIPOUSER = TIPO_USERS.ID ORDER BY NOME";

		try {
			stm = cn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				Usuarios usuario = new Usuarios();
				TipoUsers tipoUser = new TipoUsers();
				try {
					usuario.setId(rs.getInt("ID"));
					usuario.setUsername(rs.getString("USERNAME"));
					usuario.setNomeUsuario(rs.getString("NOME"));
					usuario.setPassword(rs.getString("SENHA"));
					usuario.setStatus(rs.getString("STATUS").charAt(0));
					tipoUser.setId(rs.getInt("FKTIPOUSER"));
					tipoUser.setTipo(rs.getString("TIPO"));
					usuario.setTipoUser(tipoUser);
				} catch (Exception e) {
				}
				h.add(usuario);
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

	public List<Usuarios> findUsuario(String user, String pass) {
		List<Usuarios> h = new ArrayList<>();
		Connection cn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		cn = Connect.getConexao();
		String sql = "SELECT TIPO_USERS.TIPO,TIPO_USERS.ID AS FKTIPOUSER,USUARIO.ID,USUARIO.USERNAME,USUARIO.NOME,"
				+ " USUARIO.SENHA,USUARIO.STATUS FROM USUARIO INNER JOIN"
				+ " TIPO_USERS ON USUARIO.FKTIPOUSER = TIPO_USERS.ID where USUARIO.USERNAME = ? AND USUARIO.SENHA = ?";

		try {
			stm = cn.prepareStatement(sql);
			stm.setString(1, user);
			stm.setString(2, pass);
			rs = stm.executeQuery();
			while (rs.next()) {
				Usuarios usuario = new Usuarios();
				TipoUsers tipoUser = new TipoUsers();
				try {
					usuario.setId(rs.getInt("ID"));
					usuario.setUsername(rs.getString("USERNAME"));
					usuario.setNomeUsuario(rs.getString("NOME"));
					usuario.setPassword(rs.getString("SENHA"));
					usuario.setStatus(rs.getString("STATUS").charAt(0));
					tipoUser.setId(rs.getInt("FKTIPOUSER"));
					tipoUser.setTipo(rs.getString("TIPO"));
					usuario.setTipoUser(tipoUser);
				} catch (Exception e) {
				}
				h.add(usuario);
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

	public List<Usuarios> findUsuarioByUserName(String user) {
		List<Usuarios> h = new ArrayList<>();
		Connection cn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		cn = Connect.getConexao();
		String sql = "SELECT * FROM USUARIO where USERNAME = ?";

		try {
			stm = cn.prepareStatement(sql);
			stm.setString(1, user);
			rs = stm.executeQuery();
			while (rs.next()) {
				Usuarios usuario = new Usuarios();
				try {
					usuario.setId(rs.getInt("ID"));
					usuario.setUsername(rs.getString("USERNAME"));
					usuario.setNomeUsuario(rs.getString("NOME"));
					usuario.setPassword(rs.getString("SENHA"));
					usuario.setStatus(rs.getString("STATUS").charAt(0));
				} catch (Exception e) {
				}
				h.add(usuario);
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
