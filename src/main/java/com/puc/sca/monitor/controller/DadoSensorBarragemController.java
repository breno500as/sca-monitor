package com.puc.sca.monitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puc.sca.monitor.feign.clients.DadoMonitoramentoFeignClient;
import com.puc.sca.monitor.model.DadoSensorBarragem;
import com.puc.sca.monitor.service.ModuloAlertaService;
import com.puc.sca.util.pojo.Alerta;
import com.puc.sca.util.pojo.NivelAlerta;

/**
 * Controller integrado a sensores IOT instalados na barragem informando os
 * níveis de alerta.
 * 
 * @author breno
 *
 */
@RestController
@RequestMapping("dados-sensor")
public class DadoSensorBarragemController {

	@Autowired
	private ModuloAlertaService moduloAlertaService;
	
	@Autowired
	private DadoMonitoramentoFeignClient dadoMonitoramentoFeignClient;
	
	

	@PostMapping
	public void recebeDadosSensor(@RequestBody DadoSensorBarragem dadoSensorBarragem) {

		if (NivelAlerta.ROMPIMENTO_IMINENTE.equals(dadoSensorBarragem.getNivelAlerta())) {
			// Tópico exclusivo para acionar sirenes, alarmes e emails.
			this.moduloAlertaService.acionaModuloSegurancaComunicacaoEvacuacao(new Alerta(dadoSensorBarragem.getNivelAlerta()));
			this.moduloAlertaService.acionaModuloSegurancaComunicacaoSitemaCorpoDeBombeiros(new Alerta(dadoSensorBarragem.getNivelAlerta()));
			this.moduloAlertaService.acionaModuloSegurancaIntegracaoSistemaDefesaCivil(new Alerta(dadoSensorBarragem.getNivelAlerta()));
		}
		
		this.dadoMonitoramentoFeignClient.post(dadoSensorBarragem);
	}

	 

}
