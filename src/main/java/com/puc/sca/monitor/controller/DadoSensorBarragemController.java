package com.puc.sca.monitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.puc.sca.integration.util.Alerta;
import com.puc.sca.integration.util.NivelAlerta;
import com.puc.sca.monitor.model.DadoSensorBarragem;
import com.puc.sca.monitor.repository.DadoSensorBarragemRepository;
import com.puc.sca.monitor.service.ModuloAlertaService;

/**
 * Controller integrado a sensores IOT instalados na barragem informando os níveis de alerta.
 * @author breno
 *
 */
@RestController
@RequestMapping("dados-sensor")
public class DadoSensorBarragemController {

	@Autowired
	private DadoSensorBarragemRepository dadoSensorBarragemRepository;
	
	@Autowired 
	private ModuloAlertaService moduloAlertaService;

	@PostMapping
	public void incluiDadosSensor(@RequestBody DadoSensorBarragem dadoSensorBarragem) {
		
		if (NivelAlerta.NIVEL_4_ROMPIMENTO_IMINENTE.equals(dadoSensorBarragem.getNivelAlerta())) {
			// Tópico exclusivo para acionar sirenes, alarmes e emails.
			this.moduloAlertaService.acionaModuloSegurancaComunicacaoEvacuacao(new Alerta(dadoSensorBarragem.getNivelAlerta()));
			this.moduloAlertaService.acionaModuloSegurancaComunicacaoSitemaCorpoDeBombeiros(new Alerta(dadoSensorBarragem.getNivelAlerta()));
			this.moduloAlertaService.acionaModuloSegurancaIntegracaoSistemaDefesaCivil(new Alerta(dadoSensorBarragem.getNivelAlerta()));
		}
		
		this.dadoSensorBarragemRepository.save(dadoSensorBarragem);
	}
	
	@GetMapping
	public Iterable<DadoSensorBarragem> getDadosSensor(@RequestParam("page") Integer page, 
            @RequestParam("size") Integer size,
            @RequestParam(name = "sort", defaultValue = "id") String sort) {
		Pageable pageable = PageRequest.of(page -1, size, Sort.by(sort));
		return this.dadoSensorBarragemRepository.findAll(pageable);
	}

}
