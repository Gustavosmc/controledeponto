package Control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Conexao.Connect;
import Model.CargaHoraria;
import Model.Endereco;
import Model.Funcionario;

public class FuncionarioDAO {

	public void salvar(Funcionario func) {
		String sqlFunc, sqlEnd, sqlCh = "";
		Funcionario f = new Funcionario();
		Connection cn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		if(func.getId() > 0){
			sqlFunc = "UPDATE funcionarios SET nome=?, sexo=?, cpf=?, rg=?, datanascimento=?, "+ 
					"nomepai=?, nomemae=?, cpts=?, telefonecelular=?, telefonefixo=?, "+ 
					"email=? WHERE idfuncionario = ?";
			sqlEnd = "UPDATE enderecos SET logradouro=?, numero=?, bairro=?, cidade=?, cep=?,"+
					"uf=? WHERE fkfuncionario = ?";
			sqlCh = "UPDATE cargahoraria SET horainicio=?, horafim=?, intervalo=?"+
					"WHERE fkfuncionario = ?";
		}else {
			sqlFunc = "INSERT INTO funcionarios(nome, sexo, cpf, rg, datanascimento, "
					+ "nomepai,nomemae, cpts, telefonecelular, telefonefixo, email)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) returning idfuncionario";
			sqlEnd = "INSERT INTO enderecos(logradouro, numero, bairro,"
					+ " cidade, cep, uf, fkfuncionario)VALUES (?, ?, ?, ?, ?, ?, ?)";
			sqlCh = "INSERT INTO cargahoraria(horainicio, horafim, intervalo, fkfuncionario) VALUES (?, ?, ?, ?)";
		}
		try {
			
			cn = Connect.getConexao();
			stm = cn.prepareStatement(sqlFunc);
			stm.setString(1, func.getNome());
			stm.setString(2, String.valueOf(func.getSexo()));
			stm.setString(3, func.getCpf());
			stm.setString(4, func.getRg());
			stm.setDate(5, Date.valueOf(func.getDataNasc()));
			stm.setString(6, func.getNomePai());
			stm.setString(7, func.getNomeMae());
			stm.setString(8, func.getCtps());
			stm.setString(9, func.getTelefoneCelular());
			stm.setString(10, func.getTelefoneFixo());
			stm.setString(11, func.getEmail());
			if(func.getId() > 0){
				stm.setInt(12, func.getId());
				stm.executeUpdate();
			}else{
				rs = stm.executeQuery();
				rs.next();
				f.setId(rs.getInt("idfuncionario"));
				rs.close();
			}
			stm = cn.prepareStatement(sqlEnd);
			stm.setString(1, func.getEndereco().getLogradouro());
			stm.setString(2, func.getEndereco().getNumero());
			stm.setString(3, func.getEndereco().getBairro());
			stm.setString(4, func.getEndereco().getCidade());
			stm.setString(5, func.getEndereco().getCep());
			stm.setString(6, func.getEndereco().getUf());
			if(func.getId() > 0){
				stm.setInt(7, func.getId());
				stm.executeUpdate();
			}else{
				stm.setInt(7, f.getId());
				stm.execute();
			}
			stm = cn.prepareStatement(sqlCh);
			stm.setTime(1, func.getCargaHoraria().getHoraInicio());
			stm.setTime(2, func.getCargaHoraria().getHoraFim());
			stm.setTime(3, func.getCargaHoraria().getIntervalo());
			if(func.getId() > 0){
				stm.setInt(4, func.getId());
				stm.executeUpdate();
			}else{
				stm.setInt(4, f.getId());
				stm.execute();
			}
			stm.close();
			cn.close();
			if(func.getId() > 0){
				JOptionPane.showMessageDialog(null, "Funcionário " + func.getNome() + ", atualizado com sucesso");
			}else{
				JOptionPane.showMessageDialog(null, "Funcionário " + func.getNome() + ", salvo com sucesso");
			}
		} catch (SQLException e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(null, "Erro ao gravar funcionário no banco:\n " + e);
		}

	}

	public List<Funcionario> listAllFuncionarios() {
		List<Funcionario> funcionarios = new ArrayList<>();
		Connection cn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		cn = Connect.getConexao();
		String sql = ""
			+ "SELECT "+
			"funcionarios.idfuncionario, "+
			"funcionarios.nome, "+
			"funcionarios.sexo, "+
			"funcionarios.cpf, "+
			"funcionarios.rg, "+
			"funcionarios.datanascimento, "+
			"funcionarios.nomepai, "+
			"funcionarios.nomemae, "+
			"funcionarios.cpts, "+
			"funcionarios.telefonecelular, "+
			"funcionarios.telefonefixo, "+
			"funcionarios.email, "+
			"enderecos.idendereco, "+
			"enderecos.logradouro, "+
			"enderecos.numero, "+
			"enderecos.bairro, "+
			"enderecos.cidade, "+
			"enderecos.cep, "+
			"enderecos.uf, "+
			"cargahoraria.idcargahoraria, "+
			"cargahoraria.horainicio, "+
			"cargahoraria.horafim, "+
			"cargahoraria.intervalo "+
		"FROM "+
			"public.funcionarios, "+
			"public.enderecos, "+
			"public.cargahoraria "+
		"WHERE "+
			"funcionarios.idfuncionario = enderecos.fkfuncionario AND "+
			"funcionarios.idfuncionario = cargahoraria.fkfuncionario "+
		"ORDER BY "+
			"funcionarios.nome ASC";


		try {
			stm = cn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				Funcionario funcionario = new Funcionario();
				Endereco endereco = new Endereco();
				CargaHoraria cargaHoraria = new CargaHoraria();
				try {
					funcionario.setId(rs.getInt("idfuncionario"));
					funcionario.setNome(rs.getString("nome"));
					funcionario.setSexo(rs.getString("sexo").charAt(0));
					funcionario.setCpf(rs.getString("cpf"));
					funcionario.setRg(rs.getString("rg"));
					funcionario.setDataNasc(rs.getDate("datanascimento").toLocalDate());
					funcionario.setNomePai(rs.getString("nomepai"));
					funcionario.setNomeMae(rs.getString("nomemae"));
					funcionario.setCtps(rs.getString(9));
					funcionario.setTelefoneCelular(rs.getString(11));
					funcionario.setTelefoneFixo(rs.getString("telefonefixo"));
					funcionario.setEmail(rs.getString("email"));
					endereco.setId(rs.getInt("idendereco"));
					endereco.setLogradouro(rs.getString("logradouro"));
					endereco.setNumero(rs.getString("numero"));
					endereco.setBairro(rs.getString("bairro"));
					endereco.setCidade(rs.getString("cidade"));
					endereco.setCep(rs.getString("cep"));
					endereco.setUf(rs.getString("uf"));
					cargaHoraria.setId(rs.getInt("idcargahoraria"));
					cargaHoraria.setHoraInicio(rs.getTime("horainicio"));
					cargaHoraria.setHoraFim(rs.getTime("horafim"));
					cargaHoraria.setIntervalo(rs.getTime("intervalo"));
					endereco.setFuncionario(funcionario);
					cargaHoraria.setFuncionario(funcionario);
					funcionario.setEndereco(endereco);
					funcionario.setCargaHoraria(cargaHoraria);
				} catch (Exception e) {
				}
				funcionarios.add(funcionario);
			}
			rs.close();
			stm.close();
		} catch (SQLException ex) {

		}
		try {
			cn.close();
		} catch (SQLException ex) {

		}
		return funcionarios;
	}
	public List<Funcionario> getListOfFuncionariosBy(String campo, String parametro, String metodoDePesquisa) {
		
		List<Funcionario> funcionarios = new ArrayList<>();
		String orderBy = " ORDER BY ";
//		String groupBy = "GROUP BY ";
		String condition = " ";
		if(metodoDePesquisa == "inicio"){
			parametro = " LIKE '"+parametro.toUpperCase()+"%'";
		}else if(metodoDePesquisa == "contenha"){
			parametro = " LIKE '%"+parametro.toUpperCase()+"%'";
		}else if(metodoDePesquisa == "fim"){
			parametro = " LIKE '%"+parametro.toUpperCase()+"'";
		}else if(metodoDePesquisa == "igual" && campo == "Código"){
			parametro = " = "+parametro;
		}else if(metodoDePesquisa == "igual" && campo != "Código"){
			parametro = " LIKE '"+parametro.toUpperCase()+"'";
		}
		if(campo == "Código"){
			orderBy += "funcionarios.idfuncionario";
			condition += "funcionarios.idfuncionario" + parametro;
		}else if(campo == "Nome"){
			orderBy += "funcionarios.nome";
			condition += "funcionarios.nome" + parametro;
		}else if(campo == "Endereço"){
			orderBy += "enderecos.logradouro";
			condition += "concat(enderecos.logradouro::text,' ',enderecos.numero::text,' ',enderecos.bairro)" + parametro;
		}else if(campo == "C.P.F."){
			orderBy += "funcionarios.cpf";
			condition += "funcionarios.cpf" + parametro;
		}else if(campo == "R.G."){
			orderBy += "funcionarios.rg";
			condition += "funcionarios.rg" + parametro;
		}else{
			JOptionPane.showMessageDialog(null, "Opção inválida...","Atenção",JOptionPane.WARNING_MESSAGE);
			return null;
		}
		Connection cn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		cn = Connect.getConexao();
		String sql = ""
			+ "SELECT "+
			"funcionarios.idfuncionario, "+
			"funcionarios.nome, "+
			"funcionarios.sexo, "+
			"funcionarios.cpf, "+
			"funcionarios.rg, "+
			"funcionarios.datanascimento, "+
			"funcionarios.nomepai, "+
			"funcionarios.nomemae, "+
			"funcionarios.cpts, "+
			"funcionarios.telefonecelular, "+
			"funcionarios.telefonefixo, "+
			"funcionarios.email, "+
			"enderecos.idendereco, "+
			"enderecos.logradouro, "+
			"enderecos.numero, "+
			"enderecos.bairro, "+
			"enderecos.cidade, "+
			"enderecos.cep, "+
			"enderecos.uf, "+
			"cargahoraria.idcargahoraria, "+
			"cargahoraria.horainicio, "+
			"cargahoraria.horafim, "+
			"cargahoraria.intervalo "+
		"FROM "+
			"public.funcionarios, "+
			"public.enderecos, "+
			"public.cargahoraria "+
		"WHERE "+
			"funcionarios.idfuncionario = enderecos.fkfuncionario AND "+
			"funcionarios.idfuncionario = cargahoraria.fkfuncionario "+
			"AND "+condition+orderBy;

		try {
			stm = cn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				Funcionario funcionario = new Funcionario();
				Endereco endereco = new Endereco();
				CargaHoraria cargaHoraria = new CargaHoraria();
				try {
					funcionario.setId(rs.getInt("idfuncionario"));
					funcionario.setNome(rs.getString("nome"));
					funcionario.setSexo(rs.getString("sexo").charAt(0));
					funcionario.setCpf(rs.getString("cpf"));
					funcionario.setRg(rs.getString("rg"));
					funcionario.setDataNasc(rs.getDate("datanascimento").toLocalDate());
					funcionario.setNomePai(rs.getString("nomepai"));
					funcionario.setNomeMae(rs.getString("nomemae"));
					funcionario.setCtps(rs.getString(9));
					funcionario.setTelefoneCelular(rs.getString(11));
					funcionario.setTelefoneFixo(rs.getString("telefonefixo"));
					funcionario.setEmail(rs.getString("email"));
					endereco.setId(rs.getInt("idendereco"));
					endereco.setLogradouro(rs.getString("logradouro"));
					endereco.setNumero(rs.getString("numero"));
					endereco.setBairro(rs.getString("bairro"));
					endereco.setCidade(rs.getString("cidade"));
					endereco.setCep(rs.getString("cep"));
					endereco.setUf(rs.getString("uf"));
					cargaHoraria.setId(rs.getInt("idcargahoraria"));
					cargaHoraria.setHoraInicio(rs.getTime("horainicio"));
					cargaHoraria.setHoraFim(rs.getTime("horafim"));
					cargaHoraria.setIntervalo(rs.getTime("intervalo"));
					endereco.setFuncionario(funcionario);
					cargaHoraria.setFuncionario(funcionario);
					funcionario.setEndereco(endereco);
					funcionario.setCargaHoraria(cargaHoraria);
				} catch (Exception e) {
				}
				funcionarios.add(funcionario);
			}
			rs.close();
			stm.close();
		} catch (SQLException ex) {

		}
		try {
			cn.close();
		} catch (SQLException ex) {

		}
		return funcionarios;
	}
}
