package Model;

import java.time.LocalDate;

public class Funcionario {

	private Integer id;
	private String nome;
	// Chave Estrangeira ralacionamento (1,1)
	private Endereco endereco;
	private char sexo;
	private String cpf;
	private String rg;
	private LocalDate dataNasc;
	private String nomePai;
	private String nomeMae;
	private String ctps;
	private String telefoneCelular;
	private String telefoneFixo;
	private String email;
	// Chave Estrangeira ralacionamento (1,1)
	private CargaHoraria cargaHoraria;

	public Funcionario() {
		this.id = 0;
		this.nome = "";
		this.sexo = '0';
		this.cpf = "";
		this.rg = "";
		this.dataNasc = null;
		this.nomePai = "";
		this.nomeMae = "";
		this.ctps = "";
		this.telefoneCelular = "";
		this.telefoneFixo = "";
		this.email = "";
	}

	public CargaHoraria getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(CargaHoraria cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.toLowerCase();
	}

	public String getCtps() {
		return ctps;
	}

	public void setCtps(String ctps) {
		this.ctps = ctps.toUpperCase();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco enderecos) {
		this.endereco = enderecos;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg.toUpperCase();
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai.toUpperCase();
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae.toUpperCase();
	}

}
