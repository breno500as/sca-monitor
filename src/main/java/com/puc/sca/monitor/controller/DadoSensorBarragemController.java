package com.puc.sca.monitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puc.sca.integration.pojo.Alerta;
import com.puc.sca.monitor.model.DadoSensorBarragem;
import com.puc.sca.monitor.repository.DadoSensorBarragemRepository;

@RestController
@RequestMapping("dados-sensor")
public class DadoSensorBarragemController {

	@Autowired
	private DadoSensorBarragemRepository dadoSensorBarragemRepository;
	
	@Autowired 
	private JmsTemplate jmsTemplateTopic;

	@PostMapping
	public String incluiDadosSensor(@RequestBody DadoSensorBarragem dadoSensorBarragem) {
		

		this.jmsTemplateTopic.convertAndSend("topic.alerta-box",new Alerta(dadoSensorBarragem.getNivelAlerta().toString()));
		
		
		this.dadoSensorBarragemRepository.save(dadoSensorBarragem);
		
		return "Ok";
	}
	
	@GetMapping
	public Iterable<DadoSensorBarragem> getDadosSensor() {
		return this.dadoSensorBarragemRepository.findAll();
	}

}
