package com.puc.sca.monitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puc.sca.integration.util.Alerta;
import com.puc.sca.monitor.service.ModuloAlertaService;

@RestController
@RequestMapping("comunicacoes-seguranca")
public class ComunicaoSegurancaBarragemController {
	
	@Autowired
	private ModuloAlertaService moduloAlertaService;


	@PostMapping
	public String informaSistemasIntegrados(@RequestBody Alerta alerta) {
		this.moduloAlertaService.acionaModuloSegurancaComunicacaoEvacuacao(alerta);
		this.moduloAlertaService.acionaModuloSegurancaComunicacaoSitemaCorpoDeBombeiros(alerta);
		this.moduloAlertaService.acionaModuloSegurancaIntegracaoSistemaDefesaCivil(alerta);
		return "ok";
	}

 

}
