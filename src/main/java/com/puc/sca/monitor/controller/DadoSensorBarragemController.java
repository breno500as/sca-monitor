package com.puc.sca.monitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puc.sca.integration.util.Alerta;
import com.puc.sca.integration.util.NivelAlerta;
import com.puc.sca.monitor.model.DadoSensorBarragem;
import com.puc.sca.monitor.repository.DadoSensorBarragemRepository;
import com.puc.sca.monitor.service.ModuloAlertaService;

@RestController
@RequestMapping("dados-sensor")
public class DadoSensorBarragemController {

	@Autowired
	private DadoSensorBarragemRepository dadoSensorBarragemRepository;
	
	@Autowired 
	private ModuloAlertaService moduloAlertaService;

	@PostMapping
	public String incluiDadosSensor(@RequestBody DadoSensorBarragem dadoSensorBarragem) {
		
		if (NivelAlerta.NIVEL_4_ROMPIMENTO_IMINENTE.equals(dadoSensorBarragem.getNivelAlerta())) {
			this.moduloAlertaService.acionaModuloSegurancaComunicacaoEvacuacao(new Alerta(dadoSensorBarragem.getNivelAlerta()));
			this.moduloAlertaService.acionaModuloSegurancaComunicacaoSitemaCorpoDeBombeiros(new Alerta(dadoSensorBarragem.getNivelAlerta()));
			this.moduloAlertaService.acionaModuloSegurancaIntegracaoSistemaDefesaCivil(new Alerta(dadoSensorBarragem.getNivelAlerta()));
		}
		
		this.dadoSensorBarragemRepository.save(dadoSensorBarragem);
		
		return "Ok";
	}
	
	@GetMapping
	public Iterable<DadoSensorBarragem> getDadosSensor() {
		return this.dadoSensorBarragemRepository.findAll();
	}

}
