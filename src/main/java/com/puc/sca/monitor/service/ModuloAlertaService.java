package com.puc.sca.monitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.puc.sca.integration.util.Alerta;
import com.puc.sca.integration.util.Constants;

@Service
public class ModuloAlertaService {

	@Autowired
	private JmsTemplate jmsTemplateTopic;

	public void acionaModuloSeguranca(Alerta alerta) {
		// regras de negócio, log, etc.
		this.jmsTemplateTopic.convertAndSend(Constants.TOPICO_MODULO_SEGURACA, alerta);
	}

}
