package com.puc.sca.monitor.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class InformacaoMonitoramentoRisco implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5493003165113301173L;
	

	private String parecerMelhoriasSeguranca;

	private String observacoesTecnicasMonitoramento;

	private byte[] fotoBarragem;

	private Boolean solicitaRenovacaoDeLicenciamento;

	private LocalDateTime dataInformacao = LocalDateTime.now();

	public String getParecerMelhoriasSeguranca() {
		return parecerMelhoriasSeguranca;
	}

	public void setParecerMelhoriasSeguranca(String parecerMelhoriasSeguranca) {
		this.parecerMelhoriasSeguranca = parecerMelhoriasSeguranca;
	}

	public String getObservacoesTecnicasMonitoramento() {
		return observacoesTecnicasMonitoramento;
	}

	public void setObservacoesTecnicasMonitoramento(String observacoesTecnicasMonitoramento) {
		this.observacoesTecnicasMonitoramento = observacoesTecnicasMonitoramento;
	}

	public byte[] getFotoBarragem() {
		return fotoBarragem;
	}

	public void setFotoBarragem(byte[] fotoBarragem) {
		this.fotoBarragem = fotoBarragem;
	}

	public Boolean getSolicitaRenovacaoDeLicenciamento() {
		return solicitaRenovacaoDeLicenciamento;
	}

	public void setSolicitaRenovacaoDeLicenciamento(Boolean solicitaRenovacaoDeLicenciamento) {
		this.solicitaRenovacaoDeLicenciamento = solicitaRenovacaoDeLicenciamento;
	}

	public LocalDateTime getDataInformacao() {
		return dataInformacao;
	}

	public void setDataInformacao(LocalDateTime dataInformacao) {
		this.dataInformacao = dataInformacao;
	}

}
