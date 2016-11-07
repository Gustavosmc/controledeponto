package Model;

import java.sql.Time;
import java.time.LocalDate;

public class CartaoDePonto {

	private Integer id;
	private Funcionario funcionario;
	private LocalDate data;
	private Time inicioDiario;
	private Time inicioIntervalo;
	private Time fimIntervalo;
	private Time fimDiario;

	public Integer getId() {
		return id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Time getInicioDiario() {
		return inicioDiario;
	}

	public void setInicioDiario(Time inicioDiario) {
		this.inicioDiario = inicioDiario;
	}

	public Time getInicioIntervalo() {
		return inicioIntervalo;
	}

	public void setInicioIntervalo(Time inicioIntervalo) {
		this.inicioIntervalo = inicioIntervalo;
	}

	public Time getFimIntervalo() {
		return fimIntervalo;
	}

	public void setFimIntervalo(Time fimIntervalo) {
		this.fimIntervalo = fimIntervalo;
	}

	public Time getFimDiario() {
		return fimDiario;
	}

	public void setFimDiario(Time fimDiario) {
		this.fimDiario = fimDiario;
	}

}
