package Model;

import java.sql.Time;

public class CargaHoraria {

	private Integer id;
	private Time horaInicio;
	private Time horaFim;
	private Time intervalo;
//	Chave Estrangeira ralacionamento (1,1)
	private Funcionario funcionario;

	public CargaHoraria() {
		this.id = 0;
		this.horaInicio = null;
		this.horaFim = null;
		this.intervalo = null;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Time getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Time getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Time horaFim) {
		this.horaFim = horaFim;
	}

	public Time getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(Time intervalo) {
		this.intervalo = intervalo;
	}

}
