package com.puc.sca.monitor.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import com.puc.sca.monitor.enums.NivelAlerta;
import com.puc.sca.monitor.enums.TipoSensor;

public class DadoSensorBarragem {

	@Id
	private String id;

	private String identificadorSensor;

	private NivelAlerta nivelAlerta;

	private TipoSensor tipoSensor;

	private LocalDateTime dataAlerta;

	private LocalDateTime dataCriacao = LocalDateTime.now();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdentificadorSensor() {
		return identificadorSensor;
	}

	public void setIdentificadorSensor(String identificadorSensor) {
		this.identificadorSensor = identificadorSensor;
	}

	public NivelAlerta getNivelAlerta() {
		return nivelAlerta;
	}

	public void setNivelAlerta(NivelAlerta nivelAlerta) {
		this.nivelAlerta = nivelAlerta;
	}

	public TipoSensor getTipoSensor() {
		return tipoSensor;
	}

	public void setTipoSensor(TipoSensor tipoSensor) {
		this.tipoSensor = tipoSensor;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataAlerta() {
		return dataAlerta;
	}

	public void setDataAlerta(LocalDateTime dataAlerta) {
		this.dataAlerta = dataAlerta;
	}

}
