package com.puc.sca.monitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puc.sca.integration.util.Alerta;
import com.puc.sca.integration.util.NivelAlerta;
import com.puc.sca.monitor.model.InformacaoMonitoramentoRisco;
import com.puc.sca.monitor.service.ModuloAlertaService;

@RestController
@RequestMapping("informacoes-monitoramento-risco")
public class InformacaoMonitoramentoRiscoController {
	
	@Autowired
	private ModuloAlertaService moduloAlertaService;


	@PostMapping("aciona-modulo-alerta")
	public String acionaModuloAlerta() {
		this.moduloAlertaService.acionaModuloSeguranca(new Alerta(NivelAlerta.NIVEL_4_ROMPIMENTO_IMINENTE));
		return "ok";
	}

	@PostMapping
	public String informaSistemasIntegrados(@RequestBody InformacaoMonitoramentoRisco informacaoMonitoramentoRisco) {
		return "ok";
	}

}
