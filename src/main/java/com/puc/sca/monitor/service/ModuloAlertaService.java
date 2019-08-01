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

	public void acionaModuloSegurancaComunicacaoEvacuacao(Alerta alerta) {
		// regras de negócio, log, etc.
		this.jmsTemplateTopic.convertAndSend(Constants.TOPICO_MODULO_SEGURACA_COMUNICACAO_EVACUACAO, alerta);
	}
	
	public void acionaModuloSegurancaIntegracaoSistemaDefesaCivil(Alerta alerta) {
		// regras de negócio, log, etc.
		this.jmsTemplateTopic.convertAndSend(Constants.TOPICO_MODULO_SEGURACA_INTEGRACAO_SISTEMA_DEFESA_CIVIL, alerta);
	}
	
	
	public void acionaModuloSegurancaComunicacaoSitemaCorpoDeBombeiros(Alerta alerta) {
		// regras de negócio, log, etc.
		this.jmsTemplateTopic.convertAndSend(Constants.TOPICO_MODULO_SEGURACA_INTEGRACAO_SISTEMA_CORPO_DE_BOMBEIROS, alerta);
	}

}
