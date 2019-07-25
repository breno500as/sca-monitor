package com.puc.sca.monitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puc.sca.monitor.model.DadoSensorBarragem;
import com.puc.sca.monitor.repository.DadoSensorBarragemRepository;

@RestController
@RequestMapping("dados-sensor")
public class DadoSensorBarragemController {

	@Autowired
	private DadoSensorBarragemRepository dadoSensorBarragem;

	@PostMapping
	public String incluiDadosSensor(@RequestBody DadoSensorBarragem dadoSensorBarragem) {
		this.dadoSensorBarragem.save(dadoSensorBarragem);
		return "Ok";
	}

}
